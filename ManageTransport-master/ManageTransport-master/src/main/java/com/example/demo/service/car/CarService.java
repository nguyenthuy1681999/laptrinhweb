package com.example.demo.service.car;

import com.example.demo.model.CarDto;

import java.util.List;

public interface CarService {
    CarDto createCar(CarDto dto);

    CarDto updateCar(CarDto dto);

    CarDto deleteCar(int id);

    CarDto findCar(int id);

    List<CarDto> findAllCar();
}
