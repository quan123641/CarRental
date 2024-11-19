package com.hoaiphong.carrental.dtos.car;

import java.util.Set;
import java.util.UUID;


import com.hoaiphong.carrental.entities.CarBooking;
import com.hoaiphong.carrental.entities.User;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarUpdatePricingDTO {
    private UUID id;

//page3

    @NotNull(message = "Base price is required")
    private double basePrice;

    @NotNull(message = "Deposit is required")
    private double deposit;
    //term of use
    private String termOfUse;

    private boolean noSmoking;

    private boolean noPet;

    private boolean noFoodInCar;

    private User user;

    private Set<CarBooking> carBookings;

}
