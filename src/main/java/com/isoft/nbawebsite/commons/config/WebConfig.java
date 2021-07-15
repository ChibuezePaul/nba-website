package com.isoft.nbawebsite.commons.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${image-directory}")
    private String imageDirectory;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath().replace(".", "");
        imageDirectory = path+imageDirectory;
        Path uploadDir = Paths.get(imageDirectory);
        String uploadPath = uploadDir.toFile().getAbsolutePath();

        if (imageDirectory.startsWith("../")) {
            imageDirectory = imageDirectory.replace("../", "");
        }
        registry.addResourceHandler("/" + imageDirectory + "/**").addResourceLocations("file:/" + uploadPath + "/");
    }
}
