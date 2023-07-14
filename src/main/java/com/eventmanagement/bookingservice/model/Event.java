package com.eventmanagement.bookingservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Event {
    @Id
    private String id;
    private Integer initialCapacity;
    private Integer capacityLeft;
}
