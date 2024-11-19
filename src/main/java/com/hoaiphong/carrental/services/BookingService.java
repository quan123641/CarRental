package com.hoaiphong.carrental.services;

import java.util.UUID;

import com.hoaiphong.carrental.entities.Booking;

public interface BookingService {
   void save(Booking carBooking);
   Booking findById(UUID id);
}
