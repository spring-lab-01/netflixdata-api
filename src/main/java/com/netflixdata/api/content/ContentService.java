package com.netflixdata.api.content;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ContentService {
    private final ContentRepo contentRepo;

    public ContentService(ContentRepo contentRepo) {
        this.contentRepo = contentRepo;
    }

    public Page<Content> getContent(PageRequest pageRequest, String filter) {
        Specification<Content> spec = Specification.where(null);

        if (filter != null) {
            for (String part : filter.split(",")) {
                String[] keyValue = part.split(":");
                spec = spec.and(buildSpecification(keyValue[0], keyValue[1]));
            }
        }

        return contentRepo.findAll(spec, pageRequest);
    }

    private Specification<Content> buildSpecification(String key, String value) {
        if (key.equalsIgnoreCase("year") && value.length() == 4) {
            int year = Integer.parseInt(value);
            if (year > 1900 && year <= LocalDate.now().getYear()) {
                return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("releaseYear"), String.valueOf(year));
            }
        }
        if (key.equalsIgnoreCase("type")) {
            if (value.equalsIgnoreCase("tv") || value.equalsIgnoreCase("movie")) {
                return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("type"), value);
            }
        }
        return null;
    }
}
