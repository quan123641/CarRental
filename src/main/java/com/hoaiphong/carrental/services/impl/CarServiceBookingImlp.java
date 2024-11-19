package com.hoaiphong.carrental.services.impl;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.hoaiphong.carrental.entities.Car;
import com.hoaiphong.carrental.repositories.CarRepository;
import com.hoaiphong.carrental.repositories.CarRepositoryBooking;
import com.hoaiphong.carrental.services.CarService;
import com.hoaiphong.carrental.services.CarServiceBooking;

@Service
public class CarServiceBookingImlp implements CarServiceBooking {

    private final CarRepositoryBooking carRepositoryBooking;

    public CarServiceBookingImlp(CarRepositoryBooking carRepositoryBooking) {
        this.carRepositoryBooking = carRepositoryBooking;
    }

    @Override
    public Car findById(UUID id) {
        return (Car) carRepositoryBooking.findById(id).orElse(null);
    }

   @Override
    public Page<Car> search(String name, Pageable pageable) {
        Specification<Car> spec = (root, query, criteriaBuilder) -> {
            if (name == null || name.isEmpty()) {
                // return criteriaBuilder.conjunction();
                return null; // Trả về tất cả kết quả nếu name rỗng
            }

            // Sử dụng criteriaBuilder để tạo điều kiện tìm kiếm theo name hoặc description
            return criteriaBuilder.or(
                    criteriaBuilder.like(root.get("name"), "%" + name + "%"),
                    criteriaBuilder.like(root.get("description"), "%" + name + "%"),
                    criteriaBuilder.like(root.get("address"), "%" + name + "%")
            );
        };

        // Tìm kiếm và trả về kết quả dưới dạng Page<Car>
        return carRepositoryBooking.findAll(spec, pageable);
    }

}
