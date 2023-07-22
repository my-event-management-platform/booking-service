package com.eventmanagement.bookingservice.repository;

import com.eventmanagement.bookingservice.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface BookingRepository extends MongoRepository<Booking, String> {
    @Query("{$and: ["
            + "{'userId': {$regex: ?0, $options: 'i'}},"
            + "{'eventId': {$regex: ?1, $options: 'i'}}"
            + "]}")
    Page<Booking> findBookings(String userId, String eventId, Pageable pageable);
}
