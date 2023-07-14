package com.eventmanagement.bookingservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Booking {
    @Id
    private String id;
    private String userId;
    @DBRef
    private Event event;
    private Integer persons;
}
