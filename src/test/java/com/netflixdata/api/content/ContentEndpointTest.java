package com.netflixdata.api.content;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.io.IOException;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContentEndpointTest {

    @InjectMocks
    ContentEndpoint endpoint;

    @Mock
    ContentService service;

    ClassPathResource dataFile = new ClassPathResource("typeTvYear2025.json");
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testEndpointNotNull() {
        assert endpoint != null;
    }

    @Test
    void testGetContentEmptyPage() {
       when(service.getContent(any(PageRequest.class), any(String.class))).thenReturn(Page.empty());
       Page<Content> responsePage = endpoint.getContent(0, 10, "releaseYear", "filter=year:2025");
       assert responsePage.isEmpty();
    }

    @Test
    void testGetContentNonEmptyPage() throws IOException {
        TypeReference<List<Content>> typeReference = new TypeReference<>() {};
        List<Content> contents = objectMapper.readValue(dataFile.getInputStream(), typeReference);
        Page<Content> page = new PageImpl<>(contents);
        when(service.getContent(any(PageRequest.class), any(String.class))).thenReturn(page);
        Page<Content> responsePage = endpoint.getContent(0, 10, "releaseYear", "filter=year:2025");
        assert responsePage.hasContent();
    }
}
