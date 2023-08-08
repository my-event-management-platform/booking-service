package com.eventmanagement.bookingservice.repository;

import com.eventmanagement.bookingservice.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingRepository extends MongoRepository<Booking, String> {
}
