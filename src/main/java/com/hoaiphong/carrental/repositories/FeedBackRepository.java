package com.hoaiphong.carrental.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.hoaiphong.carrental.entities.FeedBack;

public interface FeedBackRepository extends JpaRepository<FeedBack, UUID>, JpaSpecificationExecutor<FeedBack> {



    Page<FeedBack> findByCarBooking_Booking_User_Id(UUID userId,Pageable pageable);

    Optional<FeedBack> findById(UUID id);


    
    
}