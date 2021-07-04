package com.isoft.nbawebsite.meeting;


import com.isoft.nbawebsite.commons.data.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Document
public class Meeting extends AbstractEntity {
    private String topic;
    private String meetingLink;
    private Set<String> inviteesId;
}
