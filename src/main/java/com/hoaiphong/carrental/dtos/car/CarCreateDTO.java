package com.hoaiphong.carrental.dtos.car;

import com.hoaiphong.carrental.entities.CarBooking;
import com.hoaiphong.carrental.entities.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarCreateDTO {
    private UUID id;

    //page1
    @NotNull(message = "License plate is required")
    @Length(min = 2, max = 50, message = "License plate must be between 2 and 50 characters")
    private String licensePlate;

    @NotNull(message = "Color is required")
    @Length(min = 2, max = 50, message = "Color must be between 2 and 50 characters")
    private String color;

    @NotNull(message = "Brand is required")
    @Length(min = 2, max = 50, message = "Brand must be between 2 and 50 characters")
    private String brand;

    @NotNull(message = "Model is required")
    @Length(min = 2, max = 50, message = "Model must be between 2 and 50 characters")
    private String model;

    @NotNull(message = "Production year is required")
    private int productionYear;

    @NotNull(message = "Number of seats is required")
    private int numOfSeats;

    @NotNull(message = "Transmission type is required")
    private boolean transmissionType;

    @NotNull(message = "Fuel type is required")
    private boolean fuelType;

    //Document
//    @NotNull(message = "Registration paper is required")
    private String registrationPaper;

//    @NotNull(message = "Certificate of inspection is required")
    private String certificateOfInspection;

    private String insurance;


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

    private boolean other;

    private String status;

    private String otherMessage;

    private User user;

    private Set<CarBooking> carBookings;
}
