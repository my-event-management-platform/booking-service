package com.eventmanagement.bookingservice.controller;

import com.eventmanagement.bookingservice.mapper.BookingMapper;
import com.eventmanagement.bookingservice.model.Booking;
import com.eventmanagement.bookingservice.service.BookingService;
import com.eventmanagement.shared.dto.request.BookingDTO;
import com.eventmanagement.shared.dto.response.BookingResponseDTO;
import com.eventmanagement.shared.dto.response.PageBookingsResponseDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
public class BookingController {
    private final BookingService bookingService;
    private final BookingMapper bookingMapper;

    @PostMapping()
    public ResponseEntity<BookingResponseDTO> createBooking(@Valid @RequestBody BookingDTO bookingDTO) {
        Booking booking = bookingMapper.toBooking(bookingDTO);
        bookingService.addBooking(booking);
        BookingResponseDTO bookingResponseDTO = bookingMapper.toBookingResponseDTO(booking);
        return new ResponseEntity<>(bookingResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{booking_id}")
    public ResponseEntity<BookingResponseDTO> getBookingById(@PathVariable("booking_id") String id) {
        Booking booking = bookingService.getBooking(id);
        BookingResponseDTO bookingResponseDTO = bookingMapper.toBookingResponseDTO(booking);
        return new ResponseEntity<>(bookingResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{booking_id}")
    public ResponseEntity<Void> deleteBookingById(@PathVariable("booking_id") String id) {
        bookingService.deleteBooking(id, true);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping()
    public ResponseEntity<PageBookingsResponseDTO> getBookings(@RequestParam(required = false, defaultValue = "0") @Min(0) int page,
                                                               @RequestParam(required = false, defaultValue = "10") @Min(0) int size,
                                                               @RequestParam(required = false, defaultValue = "") String userId,
                                                               @RequestParam(required = false, defaultValue = "") String eventId) {
        Page<Booking> pageBookings = bookingService.getBookings(
                page,
                size,
                userId,
                eventId);
        PageBookingsResponseDTO pageBookingsResponseDTO = bookingMapper.toPageBookingsResponseDTO(pageBookings);
        return new ResponseEntity<>(pageBookingsResponseDTO, HttpStatus.OK);
    }
}
