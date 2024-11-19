package com.hoaiphong.carrental.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hoaiphong.carrental.entities.CarBooking;
import com.hoaiphong.carrental.entities.CarBookingId;

public interface CarBookingRepository extends JpaRepository<CarBooking, CarBookingId>,
        JpaSpecificationExecutor<CarBooking> {

    // Câu truy vấn sửa lại, bỏ toán tử BETWEEN cho từng trường riêng biệt
    List<CarBooking> findByCar_AddressAndBooking_StartDateGreaterThanEqualAndBooking_EndDateLessThanEqual(
            String address, LocalDateTime startDate, LocalDateTime endDate);

    List<CarBooking> findByCarAddressContaining(String address);

    Page<CarBooking> findByCarAddressContaining(String address, Pageable pageable);

    CarBooking findByCar_Id(UUID carId);

    Page<CarBooking> findByBooking_User_Id(UUID userId, Pageable pageable);

    CarBooking findByCar_IdAndBooking_Id(UUID carId, UUID bookingId);

    Optional findById(CarBookingId carBooking);


    @Query("SELECT SUM(f.rating) FROM FeedBack f WHERE f.carBooking.id = :carBookingId")
    Integer sumRatingByCarBookingId(@Param("carBookingId") UUID carBookingId);

    @Query("SELECT cb FROM CarBooking cb WHERE cb.feedBackId IN (SELECT f.id FROM FeedBack f WHERE f.rating = :star)")
Page<CarBooking> findAllWithFeedbackByRating(@Param("star") int star, Pageable pageable);

}