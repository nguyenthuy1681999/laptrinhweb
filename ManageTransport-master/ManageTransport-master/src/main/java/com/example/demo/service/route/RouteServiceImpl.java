package com.example.demo.service.route;


import com.example.demo.entity.RouteEntity;

import com.example.demo.model.RouteDto;
import com.example.demo.repository.RouteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {
    private Logger logger = LoggerFactory.getLogger(RouteServiceImpl.class);
    @Autowired
    RouteRepository repository;

    @Override
    public RouteDto createRoute(RouteDto dto) {
        logger.info("request to create route");
        RouteEntity entity = new RouteEntity();
        entity = entity.builder()
                .start(dto.getStart())
                .end(dto.getEnd())
                .length(dto.getLength())
                .description(dto.getDescription())
                .build();
        return convertToDto(repository.save(entity));
    }



    @Override
    public RouteDto updateRoute(RouteDto dto) {
        RouteEntity updateRoute = new RouteEntity();
        RouteEntity isUpdatedRoute = null;
        isUpdatedRoute = repository.findById(dto.getId()).orElse(null);

        logger.info("route are queried : {}", updateRoute);
        if (isUpdatedRoute != null) {
            updateRoute.setId(isUpdatedRoute.getId());
            updateRoute.setStart(dto.getStart());
            updateRoute.setEnd(dto.getEnd());
            updateRoute.setLength(dto.getLength());
            updateRoute.setDescription(dto.getDescription());
            return convertToDto(repository.saveAndFlush(updateRoute));
        }
        return null;
    }

    @Override
    public RouteDto deleteRoute(int id) {
        RouteEntity deletedRoute = null;
        deletedRoute = repository.findById(id).orElse(null);

        if (deletedRoute != null) {
            repository.deleteById(id);
            return convertToDto(deletedRoute);
        }
        return null;
    }

    @Override
    public RouteDto findRoute(int id) {
        RouteEntity routeEntity = null;
        routeEntity = repository.findById(id).orElse(null);

        if (routeEntity != null) {
            return convertToDto(routeEntity);
        }
        return null;
    }
    @Override
    public List<RouteDto> findAllRoute() {
        List<RouteEntity> routeEntityList = repository.findAll();
        return convertToDtoList(routeEntityList);
    }
    private RouteDto convertToDto(RouteEntity entity) {
        RouteDto dto = new RouteDto();
        if (entity != null) {
            dto.setId(entity.getId());
            dto.setStart(entity.getStart());
            dto.setEnd(entity.getEnd());
            dto.setLength(entity.getLength());
            dto.setDescription(entity.getDescription());
        }
        return dto;
    }
    private List<RouteDto> convertToDtoList(List<RouteEntity> routeList){
        if (routeList != null){
            return routeList.stream().map(this::convertToDto).collect(Collectors.toList());
        }
        return null;
    }
}
