package com.netflixdata.api.content;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/content-dataset")
@RestController
public class ContentEndpoint {

    private final ContentService contentService;

    public ContentEndpoint(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping
    public Page<Content> getContent(@RequestParam("offset") int offset,
                                    @RequestParam("page_size") int pageSize,
                                    @RequestParam("sort_by") String sortBy,
                                    @RequestParam(required = false) String filter) {
        return contentService.getContent(PageRequest.of(offset, pageSize, Sort.by(sortBy)), filter);
    }
}
