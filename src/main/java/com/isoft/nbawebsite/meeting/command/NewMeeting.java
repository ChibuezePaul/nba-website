package com.isoft.nbawebsite.meeting.command;

import lombok.Data;

@Data
public class NewMeeting {
    private String title;
    private String meetingLink;
    private String scheduledDate;
}