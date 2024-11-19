package com.hoaiphong.carrental.entities;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "car_bookings")
public class CarBooking {

    @EmbeddedId
    private CarBookingId carBookingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("bookingId")
    private Booking booking;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("carId")
    private Car car;

    //Renter's information
    @Column(name = "Renter_full_name")
    private String renterFullName;

    @Column(name = "Renter_phone")
    private String renterPhone;

    @Column(name = "Renter_national_id")
    private String renterNationalId;

    @Column(name = "Renter_date_of_birth")
    private LocalDate renterDateOfBirth;

    @Column(name = "Renter_email")
    private String renterEmail;

    @Column(name = "Renter_driving_license")
    private String renterDrivingLicense;

    //Address
    @Column(name = "Renter_city")
    private String renterCity;

    @Column(name = "Renter_street")
    private String renterStreet;

    @Column(name = "Renter_house_number")
    private String renterHouseNumber;

    // Driver's Information
    @Column(name = "Driver_full_name")
    private String driverFullName;

    @Column(name = "Driver_phone")
    private String driverPhone;

    @Column(name = "Driver_national_id")
    private String driverNationalId;

    @Column(name = "Driver_date_of_birth")
    private LocalDate driverDateOfBirth;

    @Column(name = "Driver_email")
    private String driverEmail;

    @Column(name = "Driver_driving_license")
    private String driverDrivingLicense;

    //Address
    @Column(name = "Driver_city")
    private String driverCity;

    @Column(name = "Driver_street")
    private String driverStreet;

    @Column(name = "Driver_house_number")
    private String driverHouseNumber;

    //Payment
    @Column(name = "Payment_method")
    private String paymentMethod;

    @Column(name = "Status")
    private String status;

    @Column(name = "Total_price")
    private double totalPrice;

    @Column(name = "feedBack_id")
    private UUID feedBackId;

    // @OneToOne(mappedBy = "carBooking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
}