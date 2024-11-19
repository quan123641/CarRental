package com.hoaiphong.carrental.entities;


import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
    @Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CarBookingId {
    @Column(name = "booking_id")
    private UUID bookingId;

    @Column(name = "car_id")
    private UUID carId;
}
