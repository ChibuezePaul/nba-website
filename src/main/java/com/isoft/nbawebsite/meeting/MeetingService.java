package com.isoft.nbawebsite.meeting;

import com.isoft.nbawebsite.meeting.command.NewMeeting;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MeetingService {
    private final MeetingRepository meetingRepository;

    public MeetingService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public Meeting createMeeting(NewMeeting newMeeting) {
        Meeting meeting = new Meeting();
        meeting.setMeetingLink(newMeeting.getMeetingLink());
        meeting.setTitle(newMeeting.getTitle());
        meeting.setScheduledDate(LocalDateTime.parse(newMeeting.getScheduledDate()));
        return meetingRepository.save(meeting);
    }

    public List<Meeting> findPendingMeetings() {
        return meetingRepository.findAllByScheduledDateIsAfter(LocalDateTime.now().minusDays(1));
    }
}