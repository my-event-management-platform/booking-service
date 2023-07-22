package com.eventmanagement.bookingservice.service;

import com.eventmanagement.bookingservice.model.Booking;
import com.eventmanagement.bookingservice.repository.BookingRepository;
import com.eventmanagement.shared.exceptions.BookingNotFoundException;
import com.eventmanagement.shared.exceptions.EventNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookingService {
    public final BookingRepository bookingRepository;

    @Transactional
    public void addBooking(Booking booking) {
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
    public void deleteBooking(String bookingId, boolean checkExistence) {
        if (checkExistence) {
            getBooking(bookingId);
        }
        bookingRepository.deleteById(bookingId);
    }

    @Transactional(readOnly = true)
    public Page<Booking> getBookings(int page,
                                     int size,
                                     String userId,
                                     String eventId) {
        Pageable paging = PageRequest.of(page, size);
        Page<Booking> pageBookings = bookingRepository.findBookings(userId, eventId, paging);
        return pageBookings;
    }


}
