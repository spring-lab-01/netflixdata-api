package com.netflixdata.api.content;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ContentRepo extends JpaRepository<Content, Long>, JpaSpecificationExecutor<Content> {

    List<Content> findByReleaseYearAndType(int releaseYear, String type);
}
