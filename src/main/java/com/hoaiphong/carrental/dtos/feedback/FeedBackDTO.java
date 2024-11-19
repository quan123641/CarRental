package com.hoaiphong.carrental.dtos.feedback;

import java.time.LocalDateTime;
import java.util.UUID;

import com.hoaiphong.carrental.entities.CarBooking;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedBackDTO {
       
    private UUID id;

    private int rating;

    private String content;
  
    private LocalDateTime dateTime;

    private UUID carBookingId;

    private UUID bookingId;
  
    private CarBooking carBooking;

}
