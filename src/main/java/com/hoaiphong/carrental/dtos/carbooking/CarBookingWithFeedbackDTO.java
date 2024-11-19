package com.hoaiphong.carrental.dtos.carbooking;

import com.hoaiphong.carrental.entities.CarBooking;
import com.hoaiphong.carrental.entities.FeedBack;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarBookingWithFeedbackDTO {
    private CarBooking carBooking;
    private FeedBack feedback;
}
