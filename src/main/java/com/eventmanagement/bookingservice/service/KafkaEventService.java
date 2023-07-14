package com.eventmanagement.bookingservice.service;

import com.eventmanagement.bookingservice.mapper.KafkaEventMapper;
import com.eventmanagement.bookingservice.model.Event;
import com.eventmanagement.shared.kafkaEvents.KafkaEvent;
import com.eventmanagement.shared.kafkaEvents.event.EventChanged;
import com.eventmanagement.shared.kafkaEvents.event.EventDeleted;
import com.eventmanagement.shared.kafkaEvents.event.EventPublished;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class KafkaEventService {
    private final KafkaTemplate<String, KafkaEvent> kafkaTemplate;
    private final EventService eventService;
    private final KafkaEventMapper kafkaEventMapper;

    @KafkaListener(topics = "event-published-kafka-events")
    private void consumeEventPublished(EventPublished eventPublished) {
        Event event = kafkaEventMapper.toEvent(eventPublished);
        eventService.addEvent(event);
    }

    @KafkaListener(topics = "event-deleted-kafka-events")
    private void consumeEventDeleted(EventDeleted eventDeleted) {
        String eventId = eventDeleted.getEventId();
        eventService.deleteEvent(eventId, true);
    }

    @KafkaListener(topics = "event-changed-kafka-events")
    private void consumeEventChanged(EventChanged eventChanged) {
        Event event = kafkaEventMapper.toEvent(eventChanged);
        eventService.updateEvent(event);
    }

}
