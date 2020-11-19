package com.example.demo.service.car;

import com.example.demo.entity.CarEntity;
import com.example.demo.model.CarDto;
import com.example.demo.repository.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

    @Autowired
    CarRepository repository;

    @Override
    public CarDto createCar(CarDto dto) {
        logger.info("request to create car");
        CarEntity entity = new CarEntity();
        entity = entity.builder()
                .licensePlate(dto.getLicensePlate())
                .color(dto.getColor())
                .manufacturer(dto.getManufacturer())
                .model(dto.getModel())
                .version(dto.getVersion())
                .seats(dto.getSeats())
                .yearsOfUse(dto.getYearsOfUse())
                .lastMaintenanceDate(dto.getLastMaintenanceDate())
                .build();
        return convertToDto(repository.save(entity));
    }

    @Override
    public CarDto updateCar(CarDto dto) {
        CarEntity updateCar = new CarEntity();
        CarEntity isUpdatedCar = null;
        isUpdatedCar = repository.findById(dto.getId()).orElse(null);

        logger.info("car are queried : {}", updateCar);
        if(isUpdatedCar != null){
            updateCar.setId(isUpdatedCar.getId());
            updateCar.setLicensePlate(dto.getLicensePlate());
            updateCar.setColor(dto.getColor());
            updateCar.setManufacturer(dto.getManufacturer());
            updateCar.setVersion(dto.getVersion());
            updateCar.setModel(dto.getModel());
            updateCar.setSeats(dto.getSeats());
            updateCar.setYearsOfUse(dto.getYearsOfUse());
            updateCar.setLastMaintenanceDate(dto.getLastMaintenanceDate());

            return convertToDto(repository.saveAndFlush(updateCar));
        }
        return null;
    }

    @Override
    public CarDto deleteCar(int id) {
        CarEntity deletedCar = null;
        deletedCar = repository.findById(id).orElse(null);

        if(deletedCar != null){
            repository.deleteById(id);
            return convertToDto(deletedCar);
        }
        return null;
    }

    @Override
    public CarDto findCar(int id) {
        CarEntity carEntity = null;
        carEntity = repository.findById(id).orElse(null) ;

        if(carEntity != null){
            return convertToDto(carEntity);
        }
        return null;
    }

    @Override
    public List<CarDto> findAllCar() {
        List<CarEntity> carEntityList = repository.findAll();
        return convertToDtoList(carEntityList);
    }

    public CarDto convertToDto(CarEntity entity) {
        CarDto dto = new CarDto();
        if (entity != null) {
            dto.setId(entity.getId());
            dto.setLicensePlate(entity.getLicensePlate());
            dto.setColor(entity.getColor());
            dto.setManufacturer(entity.getManufacturer());
            dto.setVersion(entity.getVersion());
            dto.setModel(entity.getModel());
            dto.setSeats(entity.getSeats());
            dto.setYearsOfUse(entity.getYearsOfUse());
            dto.setLastMaintenanceDate(entity.getLastMaintenanceDate());
        }
        return dto;
    }

    private List<CarDto> convertToDtoList(List<CarEntity> carList){
        if (carList != null){
            return carList.stream().map(this::convertToDto).collect(Collectors.toList());
        }
        return null;
    }
}
