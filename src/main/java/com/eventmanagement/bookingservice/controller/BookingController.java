package com.eventmanagement.bookingservice.controller;

import com.eventmanagement.shared.dto.request.BookingDTO;
import com.eventmanagement.shared.dto.response.BookingResponseDTO;
import com.eventmanagement.shared.dto.response.PageBookingsResponseDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
public class BookingController {

    @PostMapping()
    public ResponseEntity<BookingResponseDTO> createBooking(@Valid @RequestBody BookingDTO bookingDTO) {
        return null; // TODO
    }

    @GetMapping("/{booking_id}")
    public ResponseEntity<BookingResponseDTO> getBookingById(@PathVariable("booking_id") String Id) {
        return null; // TODO
    }

    @DeleteMapping("/{booking_id}")
    public ResponseEntity<BookingResponseDTO> deleteBookingById(@PathVariable("booking_id") String id) {
        return null; // TODO
    }

    @GetMapping()
    public ResponseEntity<PageBookingsResponseDTO> getBookings(@RequestParam(required = false, defaultValue = "0") @Min(0) int page,
                                                               @RequestParam(required = false, defaultValue = "10") @Min(0) int size,
                                                               @RequestParam(required = false, defaultValue = "") String userId,
                                                               @RequestParam(required = false, defaultValue = "") String eventId) {
        return null; // TODO
    }



}
