package com.eventmanagement.bookingservice.service;

import com.eventmanagement.bookingservice.model.Booking;
import com.eventmanagement.bookingservice.repository.BookingRepository;
import com.eventmanagement.shared.exceptions.BookingNotFoundException;
import com.eventmanagement.shared.exceptions.EventNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookingService {
    public final BookingRepository bookingRepository;
    public final EventService eventService;
    public final MongoTemplate mongoTemplate;

    @Transactional
    public void addBooking(Booking booking) {
        eventService.changeEventCapacityLeft(booking.getEvent().getId(), -booking.getPersons());
        bookingRepository.insert(booking);
    }

    @Transactional(readOnly = true)
    public Booking getBooking(String bookingId) {
        Booking booking = bookingRepository
                .findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking with id " + bookingId + " is not found"));
        return booking;
    }


    @Transactional
    public void deleteBooking(String bookingId) {
        Booking booking = getBooking(bookingId);
        eventService.changeEventCapacityLeft(booking.getEvent().getId(), booking.getPersons());
        bookingRepository.deleteById(bookingId);
    }

    @Transactional(readOnly = true)
    public Page<Booking> getBookings(int page,
                                     int size,
                                     String userId,
                                     String eventId) {
        Pageable paging = PageRequest.of(page, size);
        Query query = new Query();

        if (!userId.isEmpty()) {
            query.addCriteria(Criteria.where("userId").is(userId));
        }
        if (!eventId.isEmpty()) {
            query.addCriteria(Criteria.where("event").is(eventId));
        }

        query.with(paging);
        List<Booking> bookings = mongoTemplate.find(query, Booking.class);
        return new PageImpl<>(bookings, paging, bookings.size());
    }
}
