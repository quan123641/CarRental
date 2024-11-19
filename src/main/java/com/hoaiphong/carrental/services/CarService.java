package com.hoaiphong.carrental.services;

import com.hoaiphong.carrental.dtos.car.*;
import com.hoaiphong.carrental.entities.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface CarService {
    List<CarDTO> findAll();

    Page<CarDTO> findAll(String keyword, Pageable pageable);

    Page<CarDTO> findByUser(User user, Pageable pageable);

    CarDTO findById(UUID id);

    CarDTO create(CarCreateDTO carCreateDTO);

    CarDTO update(UUID id, CarUpdateDetailDTO carUpdateDetailDTO);

    CarDTO update(UUID id, CarUpdatePricingDTO carUpdatePricingDTO);

    CarDTO update(UUID id, CarUpdateStatusDTO carUpdateStatusDTO);
}
