package com.isoft.nbawebsite.meeting;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MeetingRepository extends MongoRepository<Meeting, String> {
    List<Meeting> findAllByInviteesIdIn(String inviteeId);
}
