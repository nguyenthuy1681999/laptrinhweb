package com.example.demo.service.driver;


import com.example.demo.model.CarDto;
import com.example.demo.model.DriverDto;

import java.util.List;

public interface DriverService {
    DriverDto createDriver(DriverDto dto);

    DriverDto updateDriver(DriverDto dto);

    DriverDto deleteDriver(int id);

    DriverDto findDriver(int id);

    List<DriverDto> findAllDriver();
}
