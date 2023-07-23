package com.eventmanagement.bookingservice.mapper;

import com.eventmanagement.bookingservice.model.Booking;
import com.eventmanagement.bookingservice.model.Event;
import com.eventmanagement.bookingservice.service.EventService;
import com.eventmanagement.shared.dto.request.BookingDTO;
import com.eventmanagement.shared.dto.response.BookingResponseDTO;
import com.eventmanagement.shared.dto.response.PageBookingsResponseDTO;
import lombok.Setter;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class BookingMapper {
    @Setter(onMethod_={@Autowired})
    private EventService eventService;
    public abstract Booking toBooking(BookingDTO bookingDTO);
    @Mapping(target = "eventId", expression = "java(booking.getEvent().getId())")
    public abstract BookingResponseDTO toBookingResponseDTO(Booking booking);

    @Mapping(target = "bookings", source = "content")
    @Mapping(target = "currentPage", source = "number")
    @Mapping(target = "totalItems", source = "totalElements")
    public abstract PageBookingsResponseDTO toPageBookingsResponseDTO(Page<Booking> bookingPage);

    @BeforeMapping
    protected void enrichBooking(BookingDTO bookingDTO, @MappingTarget Booking booking) {
        booking.setUserId("devUserId"); // Temporary ID. Will be extracted from Security Context later
        Event event = eventService.getEvent(bookingDTO.getEventId());
        booking.setEvent(event);
    }
}
