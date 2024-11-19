package com.hoaiphong.carrental.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hoaiphong.carrental.entities.CarBooking;
import com.hoaiphong.carrental.entities.CarBookingId;
import com.hoaiphong.carrental.entities.FeedBack;
import com.hoaiphong.carrental.repositories.CarBookingRepository;
import com.hoaiphong.carrental.repositories.FeedBackRepository;
import com.hoaiphong.carrental.services.CarBookingService;

@Service
public class CarBookingServiceimpl implements CarBookingService {

    private final CarBookingRepository carBookingRepository;
    private final FeedBackRepository feedBackRepository;

    public CarBookingServiceimpl(CarBookingRepository carBookingRepository,
            FeedBackRepository feedBackRepository) {
        this.feedBackRepository = feedBackRepository;
        this.carBookingRepository = carBookingRepository;
    }

    public void save(CarBooking carBooking) {
        carBookingRepository.save(carBooking);
    }

    @Override
    public List<CarBooking> find(String address,
            LocalDateTime startDate, LocalDateTime endDate) {
        List<CarBooking> carBookings = carBookingRepository.findByCar_AddressAndBooking_StartDateGreaterThanEqualAndBooking_EndDateLessThanEqual(
                address, startDate, endDate
        );
        return carBookings;
    }

    @Override
    public List<CarBooking> findByAddress(String address) {
        List<CarBooking> carBookings = carBookingRepository.findByCarAddressContaining(address);
        return carBookings;
    }

    @Override
    public Page<CarBooking> searchByAddress(String address, Pageable pageable) {
        return carBookingRepository.findByCarAddressContaining(address, pageable);
    }

    @Override
    public List<CarBooking> findAll() {
        // TODO Auto-generated method stub
        return carBookingRepository.findAll();
    }

    @Override
    public CarBooking findByCarId(UUID carId) {
        return carBookingRepository.findByCar_Id(carId);
    }

    @Override
    public CarBooking findById(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    public Page<CarBooking> findByAll(Pageable pageable) {
        return carBookingRepository.findAll(pageable);
    }

    @Override
    public List<CarBooking> findByBooking_User_Id(UUID userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByBooking_User_Id'");
    }

    @Override
    public Page<CarBooking> findByAll(UUID userId, Pageable pageable) {
return carBookingRepository.findByBooking_User_Id(userId, pageable);  }

    @Override
    public CarBooking findByCarIdAndBookingId(UUID carId, UUID bookingId) {
        return carBookingRepository.findByCar_IdAndBooking_Id(carId, bookingId);
    }

    @Override
    public CarBooking findById(CarBookingId carBooking) {
        return (CarBooking) carBookingRepository.findById(carBooking).orElse(null);
    }

    @Override
    public Double calculateTotalRatingForCarBookings(List<CarBooking> carBookings) {
     double totalRating = 0;
        int count = 0;

        for (CarBooking carBooking : carBookings) {
            UUID feedbackId = carBooking.getFeedBackId(); // Lấy ID Feedback từ CarBooking
            Optional<FeedBack> feedBack = feedBackRepository.findById(feedbackId);
            
            if (feedBack.isPresent()) {
                totalRating += feedBack.get().getRating(); // Cộng dồn rating
                count++;
            }
        }

        return count > 0 ? totalRating / count : 0; // Trả về trung bình rating
    }

 

   
    }
    



