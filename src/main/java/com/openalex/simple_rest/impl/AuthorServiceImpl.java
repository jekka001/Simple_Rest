package com.openalex.simple_rest.impl;

import com.openalex.simple_rest.entity.Author;
import com.openalex.simple_rest.entity.ResponseBody;
import com.openalex.simple_rest.mapper.AuthorMapper;
import com.openalex.simple_rest.service.AuthorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.openalex.simple_rest.URLUtils.WORKS;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorMapper authorMapper;
    @Value("${openAlex}")
    private String openAlexUrl;

    public AuthorServiceImpl(AuthorMapper authorMapper) {
        this.authorMapper = authorMapper;
    }

    @Override
    public List<Author> getAuthors(RestTemplate restTemplate, String searchQuery) {
        String requestUrl = openAlexUrl + WORKS;

        if (searchQuery != null) {
            requestUrl = requestUrl + "?" + searchQuery;
        }

        ResponseBody responseBody = restTemplate.getForObject(requestUrl, ResponseBody.class);

        return authorMapper.toListEntity(responseBody);
    }
}
