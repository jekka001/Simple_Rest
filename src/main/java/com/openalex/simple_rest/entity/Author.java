package com.openalex.simple_rest.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class Author {
    private String id;
    @JsonAlias("display_name")
    private String displayName;
    private String orcid;
}
