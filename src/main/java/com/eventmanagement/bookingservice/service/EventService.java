package com.eventmanagement.bookingservice.service;

import com.eventmanagement.bookingservice.model.Event;
import com.eventmanagement.bookingservice.repository.EventRepository;
import com.eventmanagement.shared.exceptions.EventNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventService {
    private final EventRepository eventRepository;
    @Transactional
    public void addEvent(Event event) {
        eventRepository.insert(event);
    }

    @Transactional(readOnly = true)
    public Event getEvent(String eventId) {
        Event event = eventRepository
                .findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event with id " + eventId + " is not found"));
        return event;
    }

    @Transactional
    public void deleteEvent(String eventId, boolean checkExistence) {
        if (checkExistence) {
            getEvent(eventId);
        }
        eventRepository.deleteById(eventId);
    }

}
