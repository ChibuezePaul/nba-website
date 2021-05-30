package com.isoft.nbawebsite.commons.data;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    private String id;
    private LocalDateTime dateCreated = LocalDateTime.now();
}
