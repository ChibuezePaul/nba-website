package com.isoft.nbawebsite.content;

import com.isoft.nbawebsite.constants.ContentType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ContentRepository extends MongoRepository<Content, String> {
    List<Content> findAllByContentType(ContentType contentType);
    List<Content> findTop3ByContentTypeOrderByDateCreatedDesc(ContentType contentType);
}
