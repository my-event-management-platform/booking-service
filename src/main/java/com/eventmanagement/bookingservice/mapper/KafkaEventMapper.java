package com.eventmanagement.bookingservice.mapper;

import com.eventmanagement.bookingservice.model.Event;
import com.eventmanagement.shared.kafkaEvents.event.EventChanged;
import com.eventmanagement.shared.kafkaEvents.event.EventPublished;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class KafkaEventMapper {
    @Mapping(target = "capacityLeft", source = "capacity")
    @Mapping(target = "initialCapacity", source = "capacity")
    public abstract Event toEvent(EventPublished eventPublished);

    @Mapping(target = "capacityLeft", source = "capacity")
    @Mapping(target = "initialCapacity", source = "capacity")
    public abstract Event toEvent(EventChanged eventChanged);
}
