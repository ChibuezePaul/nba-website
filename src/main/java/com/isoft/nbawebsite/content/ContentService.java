package com.isoft.nbawebsite.content;

import com.isoft.nbawebsite.constants.ContentType;
import com.isoft.nbawebsite.content.command.NewContent;
import com.isoft.nbawebsite.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.function.Supplier;

@Service @Slf4j
public class ContentService {

    private final ContentRepository contentRepository;
    private static final Supplier<CustomException> CONTENT_NOT_FOUND = () -> new CustomException("Content Not Found");

    public ContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public Content createContent(NewContent newContent, MultipartFile attachment) {
        Content content = new Content();
        content.setContentType(newContent.getContentType());
        content.setDescription(newContent.getDescription());
        content.setImageUrl(newContent.getImageUrl());
        content.setTitle(newContent.getTitle());
        content.setEventDate(newContent.getEventDate());
        uploadContentImage(attachment, content);
        return contentRepository.save(content);
    }

    public Content editContent(String id, NewContent newContent, MultipartFile attachment) {
        Content content = contentRepository.findById(id).orElseThrow(CONTENT_NOT_FOUND);
        content.setContentType(newContent.getContentType());
        content.setDescription(newContent.getDescription());
        content.setImageUrl(newContent.getImageUrl());
        content.setTitle(newContent.getTitle());
        content.setEventDate(newContent.getEventDate());
        uploadContentImage(attachment, content);
        return contentRepository.save(content);
    }

    private void uploadContentImage(MultipartFile attachment, Content content) {
        File currDir = new File ( "." );
        String path = currDir.getAbsolutePath ();
        try(InputStream in = attachment.getInputStream (); FileOutputStream f = new FileOutputStream (path.substring ( 0 , path.length () - 1 ) + attachment.getOriginalFilename () )) {
            int ch = 0;
            while ( ( ch = in.read () ) != - 1 ) {
                f.write ( ch );
            }
            f.flush ();
            content.setImageUrl ( attachment.getResource ().getFilename () );
        }
        catch ( Exception e ) {
            log.error("Error uploading content image", e);
        }
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
}
