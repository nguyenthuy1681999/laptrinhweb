package com.example.demo.service.trip;

import com.example.demo.entity.RouteEntity;
import com.example.demo.entity.TripEntity;
import com.example.demo.model.RouteDto;
import com.example.demo.model.TripDto;
import com.example.demo.repository.TripRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripServiceImpl implements TripService {
    private Logger logger = LoggerFactory.getLogger(TripServiceImpl.class);
    @Autowired
    TripRepository repository;
    @Override
    public TripDto createTrip(TripDto dto) {
        logger.info("request to create trip");
        TripEntity entity = new TripEntity();
        entity = entity.builder()
                .code(dto.getCode())
                .route(dto.getRoute())
                .driverName(dto.getDriverName())
                .assistantDriverName(dto.getAssistantDriverName())
                .guestNumber(dto.getGuestNumber())
                .price(dto.getPrice())
                .build();
        return convertToDto(repository.save(entity));
    }

    @Override
    public TripDto updateTrip(TripDto dto) {
        TripEntity updateTrip = new TripEntity();
        TripEntity isUpdatedTrip = null;
        isUpdatedTrip = repository.findById(dto.getId()).orElse(null);

        logger.info("trip are queried : {}", updateTrip);
        if (isUpdatedTrip != null) {
            updateTrip.setId(isUpdatedTrip.getId());
            updateTrip.setCode(dto.getCode());
            updateTrip.setRoute(dto.getRoute());
            updateTrip.setDriverName(dto.getDriverName());
            updateTrip.setAssistantDriverName(dto.getAssistantDriverName());
            updateTrip.setGuestNumber(dto.getGuestNumber());
            updateTrip.setPrice(dto.getPrice());
            return convertToDto(repository.saveAndFlush(updateTrip));
        }
        return null;
    }

    @Override
    public TripDto deleteTrip(int id) {
        TripEntity deletedTrip = null;
        deletedTrip = repository.findById(id).orElse(null);

        if (deletedTrip != null) {
            repository.deleteById(id);
            return convertToDto(deletedTrip);
        }
        return null;
    }

    @Override
    public TripDto findTrip(int id) {
        TripEntity tripEntity = null;
        tripEntity = repository.findById(id).orElse(null);

        if (tripEntity != null) {
            return convertToDto(tripEntity);
        }
        return null;
    }

    @Override
    public List<TripDto> findAllTrip() {
        List<TripEntity> tripEntityList = repository.findAll();
        return convertToDtoList(tripEntityList);
    }

    private TripDto convertToDto(TripEntity entity) {
        TripDto dto = new TripDto();
        if (entity != null) {
            dto.setId(entity.getId());
            dto.setCode(entity.getCode());
            dto.setRoute(entity.getRoute());
            dto.setDriverName(entity.getDriverName());
            dto.setAssistantDriverName(entity.getAssistantDriverName());
            dto.setGuestNumber(entity.getGuestNumber());
            dto.setPrice(entity.getPrice());
        }
        return dto;
    }
    private List<TripDto> convertToDtoList(List<TripEntity> tripList){
        if (tripList != null){
            return tripList.stream().map(this::convertToDto).collect(Collectors.toList());
        }
        return null;
    }
}
