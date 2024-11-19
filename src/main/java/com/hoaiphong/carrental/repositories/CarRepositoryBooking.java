package com.hoaiphong.carrental.repositories;

import java.util.Optional;

import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.hoaiphong.carrental.entities.Car;

public interface CarRepositoryBooking extends JpaRepository<Car, UUID>,
 JpaSpecificationExecutor<Car> {
    Optional findById(java.util.UUID id);
     
}