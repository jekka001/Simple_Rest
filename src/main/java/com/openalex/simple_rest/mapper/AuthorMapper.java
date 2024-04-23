package com.openalex.simple_rest.mapper;

import com.openalex.simple_rest.entity.Author;
import com.openalex.simple_rest.entity.Authorship;
import com.openalex.simple_rest.entity.ResponseBody;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.openalex.simple_rest.Constants.COMPONENT_MODEL;

@Mapper(componentModel = COMPONENT_MODEL)
public interface AuthorMapper {

    default List<Author> toListEntity(ResponseBody responseBody) {
        if (responseBody == null || responseBody.getWorks() == null) {
            return new ArrayList<>();
        }

        return responseBody.getWorks().stream()
                .flatMap(info -> info.getAuthorships().stream())
                .map(Authorship::getAuthor).collect(Collectors.toList());
    }
}
