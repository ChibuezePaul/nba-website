package com.isoft.nbawebsite.content;

import com.isoft.nbawebsite.constants.ContentType;
import com.isoft.nbawebsite.exception.CustomException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {

    private final ContentRepository contentRepository;

    public ContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public Content createContent(Content content) {
        return contentRepository.save(content);
    }

    public Content findById(String id) {
        return contentRepository.findById(id).orElseThrow(() -> new CustomException("Content Not Found"));
    }

    public List<Content> findAllContents(){
        return contentRepository.findAll();
    }

    public List<Content> findAllContentTypes(ContentType contentType){
        return contentRepository.findAllByContentType(contentType);
    }


}
