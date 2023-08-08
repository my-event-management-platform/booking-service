package com.eventmanagement.bookingservice.controller;

import com.eventmanagement.bookingservice.mapper.EventMapper;
import com.eventmanagement.bookingservice.model.Event;
import com.eventmanagement.bookingservice.service.EventService;
import com.eventmanagement.shared.dto.response.EventBookingsInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventController {
    private final EventService eventService;
    private final EventMapper eventMapper;

    @GetMapping("/{event_id}/bookings")
    public ResponseEntity<EventBookingsInfoDTO> getEventBookingsInfo(@PathVariable("event_id") String eventId) {
        Event event = eventService.getEvent(eventId);
        EventBookingsInfoDTO eventBookingsInfoDTO = eventMapper.toEventBookingsInfoDTO(event);
        return new ResponseEntity<>(eventBookingsInfoDTO, HttpStatus.OK);
    }

}
