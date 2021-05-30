package com.isoft.nbawebsite.content;

import com.isoft.nbawebsite.commons.data.AbstractEntity;
import com.isoft.nbawebsite.constants.ContentTypes;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data @Document
public class Content extends AbstractEntity {
    private String title;
    private String description;
    private String imageUrl;
    @Enumerated(EnumType.STRING)
    private ContentTypes contentType;
}
