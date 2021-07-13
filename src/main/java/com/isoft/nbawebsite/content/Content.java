package com.isoft.nbawebsite.content;

import com.isoft.nbawebsite.commons.data.AbstractEntity;
import com.isoft.nbawebsite.constants.ContentType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data @Document
public class Content extends AbstractEntity {
    private String title;
    private String description;
    private String imageUrl;
    @Enumerated(EnumType.STRING)
    private ContentType contentType;
    private LocalDateTime eventDate;
}