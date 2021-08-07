package com.isoft.nbawebsite.content.command;

import com.isoft.nbawebsite.constants.ContentType;
import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class NewContent {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String imageUrl;
    private String eventDate;
    private ContentType contentType;
}
