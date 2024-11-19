package com.hoaiphong.carrental.dtos.car;

import java.util.Set;
import java.util.UUID;


import org.hibernate.validator.constraints.Length;

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
public class CarUpdateDetailDTO {
    private UUID id;

    //page2
    @NotNull(message = "Mileage is required")
    private int mileage;

    @NotNull(message = "Fuel consumption is required")
    private int fuelConsumption;

    //address
    @NotNull(message = "Address is required")
    @Length(min = 5, max = 100, message = "Address must be between 5 and 100 characters")
    private String address;

    @Length(max = 500, message = "Description must be less than 500 characters")
    private String description;

    //addditional function
    private boolean functionsBluetooth;

    private boolean functionsGPS;

    private boolean functionsCamera;

    private boolean functionsSunRoof;

    private boolean functionsChildLock;

    private boolean functionsChildSeat;

    private boolean functionsDvd;

    private boolean functionsUSB;

    //image
    private String imageFront;

    private String imageBack;

    private String imageLeft;

    private String imageRight;

    private User user;

    private Set<CarBooking> carBookings;

}
