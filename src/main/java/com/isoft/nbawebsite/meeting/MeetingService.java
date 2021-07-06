package com.isoft.nbawebsite.meeting;

import com.isoft.nbawebsite.exception.CustomException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingService {
    private final MeetingRepository meetingRepositpry;
    private static final CustomException MEETING_NOT_FOUND = new CustomException("Meeting Not Found");

    public MeetingService(MeetingRepository meetingRepositpry) {
        this.meetingRepositpry = meetingRepositpry;
    }

    public Meeting createMeeting(Meeting meeting) {
        return meetingRepositpry.save(meeting);
    }

    public Meeting findById(String id) {
        return meetingRepositpry.findById(id).orElseThrow(() -> MEETING_NOT_FOUND);
    }

    public List<Meeting> findByInviteeId(String id) {
        return meetingRepositpry.findAllByInviteesIdIn(id);
    }

    List<Meeting> findAll() {
        return meetingRepositpry.findAll();
    }
}