package com.hoaiphong.carrental.entities;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "Name")
    private String name;

    @Column(name = "License_plate")
    private String licensePlate;

    @Column(name = "Brand")
    private String brand;

    @Column(name = "Model")
    private String model;

    @Column(name = "Color")
    private String color;

    @Column(name = "Num_of_seats")
    private int numOfSeats;

    @Column(name = "Production_year")
    private int productionYear;

    @Column(name = "Transmission_type")
    private boolean transmissionType;

    @Column(name = "Fuel_type")
    private boolean fuelType;

    @Column(name = "Mileage")
    private int mileage;

    @Column(name = "Fuel_consumption")
    private int fuelConsumption;

    @Column(name = "Base_price")
    private double basePrice;

    @Column(name = "Deposit")
    private double deposit;

    //Document
    @Column(name = " Registration_paper")
    private String registrationPaper;

    @Column(name = "Certificate_of_inspection")
    private String certificateOfInspection;

    @Column(name = "Insurance")
    private String insurance;

    //address
    @Column(name = "Address")
    private String address;

    @Column(name = "Description")
    private String description;


    //addditional function
    @Column(name = "Functions_bluetooth")
    private boolean functionsBluetooth;

    @Column(name = "Functions_gps")
    private boolean functionsGPS;

    @Column(name = "Functions_camera")
    private boolean functionsCamera;

    @Column(name = "Functions_sun_roof")
    private boolean functionsSunRoof;

    @Column(name = "Functions_child_lock")
    private boolean functionsChildLock;

    @Column(name = "Functions_child_seat")
    private boolean functionsChildSeat;

    @Column(name = "Functions_dvd")
    private boolean functionsDvd;

    @Column(name = "Functions_usb")
    private boolean functionsUSB;

    //term of use
    @Column(name = "Term_of_use")
    private String termOfUse;

    @Column(name = "No_smoking")
    private boolean noSmoking;

    @Column(name = "No_pet")
    private boolean noPet;

    @Column(name = "No_food_in_car")
    private boolean noFoodInCar;

    @Column(name = "Other")
    private boolean other;

    @Column(name = "Other_message")
    private String otherMessage;

    //image
    @Column(name = "Image_front")
    private String imageFront;

    @Column(name = "Image_back")
    private String imageBack;

    @Column(name = "Image_left")
    private String imageLeft;

    @Column(name = "Image_right")
    private String imageRight;

    @Column(name = "Status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "car")
    private Set<CarBooking> carBookings;
}
