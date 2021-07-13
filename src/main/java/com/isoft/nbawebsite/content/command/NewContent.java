package com.isoft.nbawebsite.content.command;

import com.isoft.nbawebsite.constants.ContentType;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class NewContent {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String imageUrl;
    private LocalDateTime eventDate;
    private ContentType contentType;
}
