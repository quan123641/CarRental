package com.hoaiphong.carrental.services;


import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hoaiphong.carrental.entities.Car;
public interface CarServiceBooking {
    Car findById(UUID id) ;
    Page<Car> search(String name, Pageable pageable);

}
