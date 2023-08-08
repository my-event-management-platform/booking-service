package com.eventmanagement.bookingservice.mapper;

import com.eventmanagement.bookingservice.model.Event;
import com.eventmanagement.shared.dto.response.EventBookingsInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class EventMapper {
    public abstract EventBookingsInfoDTO toEventBookingsInfoDTO(Event event);
}
