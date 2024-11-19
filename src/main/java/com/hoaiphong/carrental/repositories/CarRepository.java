package com.hoaiphong.carrental.repositories;

import com.hoaiphong.carrental.entities.Car;
import com.hoaiphong.carrental.entities.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<Car, UUID>, JpaSpecificationExecutor<Car> {
    Page<Car> findByUser(User user, Pageable pageable);

    long countByStatus(String status);
}
