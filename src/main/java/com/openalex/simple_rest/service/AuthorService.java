package com.openalex.simple_rest.service;

import com.openalex.simple_rest.entity.Author;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public interface AuthorService {
    List<Author> getAuthors(RestTemplate restTemplate, String searchQuery);
}
