package com.eventmanagement.bookingservice.repository;

import com.eventmanagement.bookingservice.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, String> {
}
