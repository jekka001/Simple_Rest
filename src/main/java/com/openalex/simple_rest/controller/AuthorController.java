package com.openalex.simple_rest.controller;

import com.openalex.simple_rest.entity.Author;
import com.openalex.simple_rest.service.AuthorService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.openalex.simple_rest.URLUtils.AUTHOR;
import static com.openalex.simple_rest.URLUtils.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping(AUTHOR)
public class AuthorController {
    private final Logger logger = LoggerFactory.getLogger(AuthorController.class);
    private AuthorService authorService;

    @GetMapping(path = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Author>> getAuthors(HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();

        List<Author> authors = authorService.getAuthors(restTemplate, request.getQueryString());

        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleException(RuntimeException e) {
        logger.error(e.getMessage(), this.getClass());

        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
