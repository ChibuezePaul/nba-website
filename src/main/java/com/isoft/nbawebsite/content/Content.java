package com.isoft.nbawebsite.content;

import com.isoft.nbawebsite.commons.data.AbstractEntity;
import com.isoft.nbawebsite.constants.ContentType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.*;
import java.io.File;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data @Document
public class Content extends AbstractEntity {
    private String title;
    private String description;
    private String image;
    @Enumerated(EnumType.STRING)
    private ContentType contentType;
    private LocalDateTime eventDate;

//    @Transient
    @Value("${image-directory}")
    private String imageDirectory;

    @Transient
    public String getImageUrl() {
        File currDir = new File ( "." );
        String path = currDir.getAbsolutePath ().replace(".","");
        String id = super.getId();
        if (image == null || id == null) return "";
        return path + "images/" + id + "/" + image;
    }
}