package com.eventmanagement.bookingservice.service;

import com.eventmanagement.bookingservice.model.Event;
import com.eventmanagement.bookingservice.repository.EventRepository;
import com.eventmanagement.shared.exceptions.CapacityExceedsException;
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

    @Transactional
    public void changeEventCapacityLeft(String eventId, int capacityChange) {
        Event event = getEvent(eventId);
        int newCapacityLeft = event.getCapacityLeft() + capacityChange;
        if (newCapacityLeft >= 0 && newCapacityLeft <= event.getInitialCapacity()) {
            event.setCapacityLeft(newCapacityLeft);
            eventRepository.save(event);
        } else {
            throw new CapacityExceedsException(String.format(
                    "Attempt to change event's capacity out of it's limits. Initial capacity: %s, New capacity: %d",
                    event.getInitialCapacity(),
                    newCapacityLeft));
        }
    }

}
