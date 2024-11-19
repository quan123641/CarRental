package com.hoaiphong.carrental.services.impl;

import com.hoaiphong.carrental.dtos.car.*;
import com.hoaiphong.carrental.entities.Car;
import com.hoaiphong.carrental.entities.User;
import com.hoaiphong.carrental.repositories.CarRepository;
import com.hoaiphong.carrental.services.CarService;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<CarDTO> findAll() {
        // Get List of entities
        var cars = carRepository.findAll();

        // Convert entities to DTOs
        var carDTOs = cars.stream().map(car -> {
            var carDTO = new CarDTO();
            carDTO.setId(car.getId());
            carDTO.setStatus(car.getStatus());
            carDTO.setLicensePlate(car.getLicensePlate());
            carDTO.setColor(car.getColor());
            carDTO.setBrand(car.getBrand());
            carDTO.setModel(car.getModel());
            carDTO.setProductionYear(car.getProductionYear());
            carDTO.setNumOfSeats(car.getNumOfSeats());
            carDTO.setTransmissionType(car.isTransmissionType());
            carDTO.setFuelType(car.isFuelType());

            // Document
            carDTO.setRegistrationPaper(car.getRegistrationPaper());
            carDTO.setCertificateOfInspection(car.getCertificateOfInspection());
            carDTO.setInsurance(car.getInsurance());

            carDTO.setMileage(car.getMileage());
            carDTO.setFuelConsumption(car.getFuelConsumption());
            // Address
            carDTO.setAddress(car.getAddress());
            carDTO.setDescription(car.getDescription());


            // Additional functions
            carDTO.setFunctionsBluetooth(car.isFunctionsBluetooth());
            carDTO.setFunctionsGPS(car.isFunctionsGPS());
            carDTO.setFunctionsCamera(car.isFunctionsCamera());
            carDTO.setFunctionsSunRoof(car.isFunctionsSunRoof());
            carDTO.setFunctionsChildLock(car.isFunctionsChildLock());
            carDTO.setFunctionsChildSeat(car.isFunctionsChildSeat());
            carDTO.setFunctionsDvd(car.isFunctionsDvd());
            carDTO.setFunctionsUSB(car.isFunctionsUSB());

            // Images
            carDTO.setImageFront(car.getImageFront());
            carDTO.setImageBack(car.getImageBack());
            carDTO.setImageLeft(car.getImageLeft());
            carDTO.setImageRight(car.getImageRight());

            carDTO.setBasePrice(car.getBasePrice());
            carDTO.setDeposit(car.getDeposit());
            // Term of use
            carDTO.setTermOfUse(car.getTermOfUse());
            carDTO.setNoSmoking(car.isNoSmoking());
            carDTO.setNoPet(car.isNoPet());
            carDTO.setNoFoodInCar(car.isNoFoodInCar());
            carDTO.setOther(car.isOther());
            carDTO.setOtherMessage(car.getOtherMessage());

            carDTO.setUser(car.getUser());

            return carDTO;
        }).toList();

        return carDTOs;
    }


    @Override
    public Page<CarDTO> findAll(String keyword, Pageable pageable) {
        var baseImageUrl = "/images/";
        Specification<Car> specification = (root, query, criteriaBuilder) -> {
            if (keyword == null) {
                return null;
            }
            Predicate brandPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("brand")), "%" + keyword.toLowerCase() + "%");
            Predicate namePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + keyword.toLowerCase() + "%");
            Predicate modelPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("model")), "%" + keyword.toLowerCase() + "%");
            Predicate licensePlatePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("licensePlate")), "%" + keyword.toLowerCase() + "%");
            return criteriaBuilder.or(brandPredicate, namePredicate, modelPredicate, licensePlatePredicate);
        };
        var cars = carRepository.findAll(specification, pageable);
        return cars.map(car -> {
            var carDTO = new CarDTO();
            carDTO.setId(car.getId());
            carDTO.setLicensePlate(car.getLicensePlate());
            carDTO.setModel(car.getModel());
            carDTO.setBrand(car.getBrand());
            carDTO.setColor(car.getColor());
            carDTO.setNumOfSeats(car.getNumOfSeats());
            carDTO.setProductionYear(car.getProductionYear());
            carDTO.setTransmissionType(car.isTransmissionType());
            carDTO.setFuelType(car.isFuelType());
            carDTO.setMileage(car.getMileage());
            carDTO.setFuelConsumption(car.getFuelConsumption());
            carDTO.setBasePrice(car.getBasePrice());
            carDTO.setDeposit(car.getDeposit());
            // Document
            carDTO.setRegistrationPaper(car.getRegistrationPaper());
            carDTO.setCertificateOfInspection(car.getCertificateOfInspection());
            carDTO.setInsurance(car.getInsurance());
            // Address
            carDTO.setAddress(car.getAddress());
            carDTO.setDescription(car.getDescription());
            // Additional functions
            carDTO.setFunctionsBluetooth(car.isFunctionsBluetooth());
            carDTO.setFunctionsGPS(car.isFunctionsGPS());
            carDTO.setFunctionsCamera(car.isFunctionsCamera());
            carDTO.setFunctionsSunRoof(car.isFunctionsSunRoof());
            carDTO.setFunctionsChildLock(car.isFunctionsChildLock());
            carDTO.setFunctionsChildSeat(car.isFunctionsChildSeat());
            carDTO.setFunctionsDvd(car.isFunctionsDvd());
            carDTO.setFunctionsUSB(car.isFunctionsUSB());
            // Term of use
            carDTO.setTermOfUse(car.getTermOfUse());
            carDTO.setNoSmoking(car.isNoSmoking());
            carDTO.setNoPet(car.isNoPet());
            carDTO.setNoFoodInCar(car.isNoFoodInCar());
            carDTO.setOther(car.isOther());
            carDTO.setOtherMessage(car.getOtherMessage());
            // Images
            carDTO.setImageFront(baseImageUrl + car.getImageFront());
            carDTO.setImageBack(baseImageUrl + car.getImageBack());
            carDTO.setImageLeft(baseImageUrl + car.getImageLeft());
            carDTO.setImageRight(baseImageUrl + car.getImageRight());

            carDTO.setUser(car.getUser());

//            Set<CarBooking> carBookingSet =car.getCarBookings().stream().map(carBooking -> {
//                var carBookingDTO = new CarBookingDTO();
//                carBookingDTO.setId(carBooking.getId());
//                carBookingDTO.setCar(carBooking.getCar());
//                carBookingDTO.setUser(carBooking.getUser());
//                carBookingDTO.setStartAt(carBooking.getStartAt());
//                carBookingDTO.setEndAt(carBooking.getEndAt());
//                return carBookingDTO;
//            }).collect(Collectors.toSet());
//
//            carDTO.setCarBookings(carBookingSet);

            return carDTO;
        });
    }

    @Override
    public CarDTO findById(UUID id) {
        // Get entity by id
        var car = carRepository.findById(id).orElse(null);

        // Check if entity is null then return null
        if (car == null) {
            return null;
        }
        // Convert entity to DTO
        var carDTO = new CarDTO();
        carDTO.setId(car.getId());
        carDTO.setStatus(car.getStatus());
        carDTO.setLicensePlate(car.getLicensePlate());
        carDTO.setColor(car.getColor());
        carDTO.setBrand(car.getBrand());
        carDTO.setModel(car.getModel());
        carDTO.setProductionYear(car.getProductionYear());
        carDTO.setNumOfSeats(car.getNumOfSeats());
        carDTO.setTransmissionType(car.isTransmissionType());
        carDTO.setFuelType(car.isFuelType());

        // Document
        carDTO.setRegistrationPaper(car.getRegistrationPaper());
        carDTO.setCertificateOfInspection(car.getCertificateOfInspection());
        carDTO.setInsurance(car.getInsurance());

        carDTO.setMileage(car.getMileage());
        carDTO.setFuelConsumption(car.getFuelConsumption());
        // Address
        carDTO.setAddress(car.getAddress());
        carDTO.setDescription(car.getDescription());


        // Additional functions
        carDTO.setFunctionsBluetooth(car.isFunctionsBluetooth());
        carDTO.setFunctionsGPS(car.isFunctionsGPS());
        carDTO.setFunctionsCamera(car.isFunctionsCamera());
        carDTO.setFunctionsSunRoof(car.isFunctionsSunRoof());
        carDTO.setFunctionsChildLock(car.isFunctionsChildLock());
        carDTO.setFunctionsChildSeat(car.isFunctionsChildSeat());
        carDTO.setFunctionsDvd(car.isFunctionsDvd());
        carDTO.setFunctionsUSB(car.isFunctionsUSB());

        // Images
        carDTO.setImageFront(car.getImageFront());
        carDTO.setImageBack(car.getImageBack());
        carDTO.setImageLeft(car.getImageLeft());
        carDTO.setImageRight(car.getImageRight());

        carDTO.setBasePrice(car.getBasePrice());
        carDTO.setDeposit(car.getDeposit());
        // Term of use
        carDTO.setTermOfUse(car.getTermOfUse());
        carDTO.setNoSmoking(car.isNoSmoking());
        carDTO.setNoPet(car.isNoPet());
        carDTO.setNoFoodInCar(car.isNoFoodInCar());
        carDTO.setOther(car.isOther());
        carDTO.setOtherMessage(car.getOtherMessage());

        carDTO.setUser(car.getUser());
        return carDTO;
    }

    @Override
    public CarDTO create(CarCreateDTO carCreateDTO) {
        // Check if carDTO is null then throw exception
        if (carCreateDTO == null) {
            throw new IllegalArgumentException("Car is required");
        }
        var car = new Car();
        car.setId(carCreateDTO.getId());
        car.setLicensePlate(carCreateDTO.getLicensePlate());
        car.setColor(carCreateDTO.getColor());
        car.setBrand(carCreateDTO.getBrand());
        car.setModel(carCreateDTO.getModel());
        car.setProductionYear(carCreateDTO.getProductionYear());
        car.setNumOfSeats(carCreateDTO.getNumOfSeats());
        car.setTransmissionType(carCreateDTO.isTransmissionType());
        car.setFuelType(carCreateDTO.isFuelType());

        // Document
        car.setRegistrationPaper(carCreateDTO.getRegistrationPaper());
        car.setCertificateOfInspection(carCreateDTO.getCertificateOfInspection());
        car.setInsurance(carCreateDTO.getInsurance());

        car.setMileage(carCreateDTO.getMileage());
        car.setFuelConsumption(carCreateDTO.getFuelConsumption());
        // Address
        car.setAddress(carCreateDTO.getAddress());
        car.setDescription(carCreateDTO.getDescription());


        // Additional functions
        car.setFunctionsBluetooth(carCreateDTO.isFunctionsBluetooth());
        car.setFunctionsGPS(carCreateDTO.isFunctionsGPS());
        car.setFunctionsCamera(carCreateDTO.isFunctionsCamera());
        car.setFunctionsSunRoof(carCreateDTO.isFunctionsSunRoof());
        car.setFunctionsChildLock(carCreateDTO.isFunctionsChildLock());
        car.setFunctionsChildSeat(carCreateDTO.isFunctionsChildSeat());
        car.setFunctionsDvd(carCreateDTO.isFunctionsDvd());
        car.setFunctionsUSB(carCreateDTO.isFunctionsUSB());

        // Images
        car.setImageFront(carCreateDTO.getImageFront());
        car.setImageBack(carCreateDTO.getImageBack());
        car.setImageLeft(carCreateDTO.getImageLeft());
        car.setImageRight(carCreateDTO.getImageRight());

        car.setBasePrice(carCreateDTO.getBasePrice());
        car.setDeposit(carCreateDTO.getDeposit());
        // Term of use
        car.setTermOfUse(carCreateDTO.getTermOfUse());
        car.setNoSmoking(carCreateDTO.isNoSmoking());
        car.setNoPet(carCreateDTO.isNoPet());
        car.setNoFoodInCar(carCreateDTO.isNoFoodInCar());
        car.setOther(carCreateDTO.isOther());
        car.setOtherMessage(carCreateDTO.getOtherMessage());
        car.setStatus("Available");
        car.setUser(carCreateDTO.getUser());

        carRepository.save(car);

        // Convert entity to DTO
        var carDTO = new CarDTO();
        carDTO.setId(car.getId());
        carDTO.setLicensePlate(car.getLicensePlate());
        carDTO.setColor(car.getColor());
        carDTO.setBrand(car.getBrand());
        carDTO.setModel(car.getModel());
        carDTO.setProductionYear(car.getProductionYear());
        carDTO.setNumOfSeats(car.getNumOfSeats());
        carDTO.setTransmissionType(car.isTransmissionType());
        carDTO.setFuelType(car.isFuelType());

        // Document
        carDTO.setRegistrationPaper(car.getRegistrationPaper());
        carDTO.setCertificateOfInspection(car.getCertificateOfInspection());
        carDTO.setInsurance(car.getInsurance());

        carDTO.setMileage(car.getMileage());
        carDTO.setFuelConsumption(car.getFuelConsumption());
        // Address
        carDTO.setAddress(car.getAddress());
        carDTO.setDescription(car.getDescription());


        // Additional functions
        carDTO.setFunctionsBluetooth(car.isFunctionsBluetooth());
        carDTO.setFunctionsGPS(car.isFunctionsGPS());
        carDTO.setFunctionsCamera(car.isFunctionsCamera());
        carDTO.setFunctionsSunRoof(car.isFunctionsSunRoof());
        carDTO.setFunctionsChildLock(car.isFunctionsChildLock());
        carDTO.setFunctionsChildSeat(car.isFunctionsChildSeat());
        carDTO.setFunctionsDvd(car.isFunctionsDvd());
        carDTO.setFunctionsUSB(car.isFunctionsUSB());

        // Images
        carDTO.setImageFront(car.getImageFront());
        carDTO.setImageBack(car.getImageBack());
        carDTO.setImageLeft(car.getImageLeft());
        carDTO.setImageRight(car.getImageRight());

        carDTO.setBasePrice(car.getBasePrice());
        carDTO.setDeposit(car.getDeposit());
        // Term of use
        carDTO.setTermOfUse(car.getTermOfUse());
        carDTO.setNoSmoking(car.isNoSmoking());
        carDTO.setNoPet(car.isNoPet());
        carDTO.setNoFoodInCar(car.isNoFoodInCar());
        carDTO.setOther(car.isOther());
        carDTO.setOtherMessage(car.getOtherMessage());

        carDTO.setUser(car.getUser());

        return carDTO;
    }

    @Override
    public CarDTO update(UUID id, CarUpdateDetailDTO carUpdateDetailDTO) {
        // Check if carDTO is null then throw exception
        if (carUpdateDetailDTO == null) {
            throw new IllegalArgumentException("Car is required");
        }

        var car = carRepository.findById(id).orElse(null);

        if (car == null) {
            throw new IllegalArgumentException("Car not found");
        }

        // Convert entity to DTO
        car.setMileage(carUpdateDetailDTO.getMileage());
        car.setFuelConsumption(carUpdateDetailDTO.getFuelConsumption());
        // Address
        car.setAddress(carUpdateDetailDTO.getAddress());
        car.setDescription(carUpdateDetailDTO.getDescription());
        // Additional functions
        car.setFunctionsBluetooth(carUpdateDetailDTO.isFunctionsBluetooth());
        car.setFunctionsGPS(carUpdateDetailDTO.isFunctionsGPS());
        car.setFunctionsCamera(carUpdateDetailDTO.isFunctionsCamera());
        car.setFunctionsSunRoof(carUpdateDetailDTO.isFunctionsSunRoof());
        car.setFunctionsChildLock(carUpdateDetailDTO.isFunctionsChildLock());
        car.setFunctionsChildSeat(carUpdateDetailDTO.isFunctionsChildSeat());
        car.setFunctionsDvd(carUpdateDetailDTO.isFunctionsDvd());
        car.setFunctionsUSB(carUpdateDetailDTO.isFunctionsUSB());

        // Images
        car.setImageFront(carUpdateDetailDTO.getImageFront());
        car.setImageBack(carUpdateDetailDTO.getImageBack());
        car.setImageLeft(carUpdateDetailDTO.getImageLeft());
        car.setImageRight(carUpdateDetailDTO.getImageRight());

        car.setUser(carUpdateDetailDTO.getUser());

        carRepository.save(car);

        // Convert the updated car entity to a CarDTO object
        CarDTO updatedCarDTO = new CarDTO();
        updatedCarDTO.setId(car.getId());
        updatedCarDTO.setMileage(car.getMileage());
        updatedCarDTO.setFuelConsumption(car.getFuelConsumption());
        updatedCarDTO.setAddress(car.getAddress());
        updatedCarDTO.setDescription(car.getDescription());

        // Additional functions
        updatedCarDTO.setFunctionsBluetooth(car.isFunctionsBluetooth());
        updatedCarDTO.setFunctionsGPS(car.isFunctionsGPS());
        updatedCarDTO.setFunctionsCamera(car.isFunctionsCamera());
        updatedCarDTO.setFunctionsSunRoof(car.isFunctionsSunRoof());
        updatedCarDTO.setFunctionsChildLock(car.isFunctionsChildLock());
        updatedCarDTO.setFunctionsChildSeat(car.isFunctionsChildSeat());
        updatedCarDTO.setFunctionsDvd(car.isFunctionsDvd());
        updatedCarDTO.setFunctionsUSB(car.isFunctionsUSB());

        // Images
        updatedCarDTO.setImageFront(car.getImageFront());
        updatedCarDTO.setImageBack(car.getImageBack());
        updatedCarDTO.setImageLeft(car.getImageLeft());
        updatedCarDTO.setImageRight(car.getImageRight());

        updatedCarDTO.setUser(car.getUser());

        return updatedCarDTO;
    }


    @Override
    public CarDTO update(UUID id, CarUpdatePricingDTO carUpdatePricingDTO) {
        // Check if carDTO is null then throw exception
        if (carUpdatePricingDTO == null) {
            throw new IllegalArgumentException("Car is required");
        }

        var car = carRepository.findById(id).orElse(null);

        if (car == null) {
            throw new IllegalArgumentException("Car not found");
        }

        // Convert entity to DTO
        car.setBasePrice(carUpdatePricingDTO.getBasePrice());
        car.setDeposit(carUpdatePricingDTO.getDeposit());
        car.setTermOfUse(carUpdatePricingDTO.getTermOfUse());
        car.setNoSmoking(carUpdatePricingDTO.isNoSmoking());
        car.setNoPet(carUpdatePricingDTO.isNoPet());
        car.setNoFoodInCar(carUpdatePricingDTO.isNoFoodInCar());

        car.setUser(carUpdatePricingDTO.getUser());


        carUpdatePricingDTO.setUser(car.getUser());

        carRepository.save(car);

        // Convert the updated car entity to a CarDTO object
        CarDTO updatedCarDTO = new CarDTO();
        updatedCarDTO.setId(car.getId());
        updatedCarDTO.setBasePrice(car.getBasePrice());
        updatedCarDTO.setDeposit(car.getDeposit());
        updatedCarDTO.setTermOfUse(car.getTermOfUse());
        updatedCarDTO.setNoSmoking(car.isNoSmoking());
        updatedCarDTO.setNoPet(car.isNoPet());
        updatedCarDTO.setNoFoodInCar(car.isNoFoodInCar());

        updatedCarDTO.setUser(car.getUser());

        return updatedCarDTO;
    }

    @Override
    public CarDTO update(UUID id, CarUpdateStatusDTO carUpdateStatusDTO) {
        if (carUpdateStatusDTO == null) {
            throw new IllegalArgumentException("Car is required");
        }

        var car = carRepository.findById(id).orElse(null);

        if (car == null) {
            throw new IllegalArgumentException("Car not found");
        }

        // Convert entity to DTO
        car.setStatus(carUpdateStatusDTO.getStatus());

        carRepository.save(car);

        // Convert the updated car entity to a CarDTO object
        CarDTO updatedCarDTO = new CarDTO();
        updatedCarDTO.setId(car.getId());
        updatedCarDTO.setStatus(car.getStatus());

        return updatedCarDTO;
    }

    @Override
    public Page<CarDTO> findByUser(User user, Pageable pageable) {
       // Lấy danh sách xe theo người dùng và phân trang
        Page<Car> cars = carRepository.findByUser(user, pageable);

        // Chuyển đổi các Car sang CarDTO và trả về trang kết quả
        return cars.map(car -> {
            var carDTO = new CarDTO();
            carDTO.setId(car.getId());
            carDTO.setMileage(car.getMileage());
            carDTO.setFuelConsumption(car.getFuelConsumption());
            carDTO.setAddress(car.getAddress());
            carDTO.setDescription(car.getDescription());
            carDTO.setStatus(car.getStatus());
            carDTO.setUser(car.getUser());
            return carDTO;
        });
    }

    

   

}
