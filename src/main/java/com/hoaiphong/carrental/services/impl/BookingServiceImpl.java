package com.hoaiphong.carrental.services.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.hoaiphong.carrental.entities.Booking;
import com.hoaiphong.carrental.repositories.BookingRepository;
import com.hoaiphong.carrental.services.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository carRepository;

    public BookingServiceImpl(BookingRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void save(Booking booking) {
        carRepository.save(booking);
    }

    @Override
    public Booking findById(UUID id) {
        return carRepository.findById(id).orElse(null);
    }

}
