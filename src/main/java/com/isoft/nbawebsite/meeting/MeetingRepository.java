package com.isoft.nbawebsite.meeting;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MeetingRepository extends MongoRepository<Meeting, String> {
    List<Meeting> findAllByScheduledDateIsAfter(LocalDateTime yesterday);
}
