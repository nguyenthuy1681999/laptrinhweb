package com.example.demo.service.driver;


import com.example.demo.entity.DriverEntity;

import com.example.demo.model.DriverDto;

import com.example.demo.repository.DriverRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverServiceImpl implements DriverService {
    private Logger logger = LoggerFactory.getLogger(DriverServiceImpl.class);

    @Autowired
    DriverRepository repository;

    @Override
    public DriverDto createDriver(DriverDto dto) {
        logger.info("request to create driver");
        DriverEntity entity = new DriverEntity();
        entity = entity.builder()
                .name(dto.getName())
                .cmt(dto.getCmt())
                .licenseCode(dto.getLicenseCode())
                .licenseType(dto.getLicenseType())
                .address(dto.getAddress())
                .dateOfBirth(dto.getDateOfBirth())
                .seniority(dto.getSeniority())
                .build();
        return convertToDto(repository.save(entity));
    }

    @Override
    public DriverDto updateDriver(DriverDto dto) {
        DriverEntity updateDriver = new DriverEntity();
        DriverEntity isUpdatedDriver = null;
        isUpdatedDriver = repository.findById(dto.getId()).orElse(null);

        logger.info("driver are queried : {}", updateDriver);
        if(isUpdatedDriver != null){
            updateDriver.setId(isUpdatedDriver.getId());
            updateDriver.setName(dto.getName());
            updateDriver.setCmt(dto.getCmt());
            updateDriver.setLicenseCode(dto.getLicenseCode());
            updateDriver.setLicenseType(dto.getLicenseType());
            updateDriver.setAddress(dto.getAddress());
            updateDriver.setDateOfBirth(dto.getDateOfBirth());
            updateDriver.setSeniority(dto.getSeniority());
            return convertToDto(repository.saveAndFlush(updateDriver));
        }
        return null;
    }

    @Override
    public DriverDto deleteDriver(int id) {
        DriverEntity deletedDriver = null;
        deletedDriver = repository.findById(id).orElse(null);

        if(deletedDriver != null){
            repository.deleteById(id);
            return convertToDto(deletedDriver);
        }
        return null;    }

    @Override
    public DriverDto findDriver(int id) {
        DriverEntity driverEntity = null;
        driverEntity = repository.findById(id).orElse(null) ;

        if(driverEntity != null){
            return convertToDto(driverEntity);
        }
        return null;
    }

    @Override
    public List<DriverDto> findAllDriver() {
        List<DriverEntity> driverEntityList = repository.findAll();
        return convertToDtoList(driverEntityList);    }

    public DriverDto convertToDto(DriverEntity entity) {
        DriverDto dto = new DriverDto();
        if (entity != null) {
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setCmt(entity.getCmt());
            dto.setLicenseCode(entity.getLicenseCode());
            dto.setLicenseType(entity.getLicenseType());
            dto.setAddress(entity.getAddress());
            dto.setDateOfBirth(entity.getDateOfBirth());
            dto.setSeniority(entity.getSeniority());
        }
        return dto;
    }

    private List<DriverDto> convertToDtoList(List<DriverEntity> driverList){
        if (driverList != null){
            return driverList.stream().map(this::convertToDto).collect(Collectors.toList());
        }
        return null;
    }
}
