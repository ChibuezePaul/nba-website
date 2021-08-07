package com.isoft.nbawebsite.content;

import com.isoft.nbawebsite.commons.util.ImageUploadUtil;
import com.isoft.nbawebsite.constants.ContentType;
import com.isoft.nbawebsite.content.command.NewContent;
import com.isoft.nbawebsite.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Supplier;

@Service @Slf4j
public class ContentService {

    private final ContentRepository contentRepository;
    private static final Supplier<CustomException> CONTENT_NOT_FOUND = () -> new CustomException("Content Not Found");

    public ContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @Value("${image-directory}")
    private String imageDirectory;

    @Transactional
    public Content createContent(NewContent newContent, MultipartFile attachment) {
        Content content = new Content();
        content.setContentType(newContent.getContentType());
        content.setDescription(newContent.getDescription());
        content.setTitle(newContent.getTitle());
        if(ContentType.EVENTS.equals(newContent.getContentType())) {
            content.setEventDate(LocalDateTime.parse(newContent.getEventDate()));
        }
        return saveContent(attachment, content);
    }

    public Content editContent(String id, NewContent newContent, MultipartFile attachment) {
        Content content = contentRepository.findById(id).orElseThrow(CONTENT_NOT_FOUND);
        content.setDescription(newContent.getDescription());
        content.setTitle(newContent.getTitle());
        if(ContentType.EVENTS.equals(newContent.getContentType())) {
            content.setEventDate(LocalDateTime.parse(newContent.getEventDate()));
        }
        return saveContent(attachment, content);
    }

    public Content findById(String id) {
        return contentRepository.findById(id).orElseThrow(CONTENT_NOT_FOUND);
    }

    public List<Content> findAllContents(){
        return contentRepository.findAll();
    }

    public List<Content> findAllContentTypes(ContentType contentType){
        return contentRepository.findAllByContentType(contentType);
    }

    public List<Content> findFeaturedEvent() {
        return contentRepository.findTop3ByContentTypeOrderByDateCreatedDesc(ContentType.EVENTS);
    }

    public List<Content> findRecentPosts() {
        return contentRepository.findTop3ByContentTypeOrderByDateCreatedDesc(ContentType.NEWS);
    }

    public void deleteContent(String id) {
        contentRepository.deleteById(id);
    }

    private Content saveContent(MultipartFile attachment, Content content) {
        String fileName = StringUtils.cleanPath(attachment.getOriginalFilename());
        content.setImage(fileName);
        Content savedContent = contentRepository.save(content);
        File currDir = new File ( "." );
        String path = currDir.getAbsolutePath ().replace(".","");
        String uploadDir = path + imageDirectory + savedContent.getId();
        ImageUploadUtil.uploadImage(uploadDir, fileName, attachment);
        return savedContent;
    }
}
