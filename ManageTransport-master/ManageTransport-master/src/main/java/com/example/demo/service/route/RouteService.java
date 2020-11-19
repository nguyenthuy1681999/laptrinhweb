package com.example.demo.service.route;

import com.example.demo.model.RouteDto;

import java.util.List;

public interface RouteService {
    RouteDto createRoute(RouteDto dto);

    RouteDto updateRoute(RouteDto dto);

    RouteDto deleteRoute(int id);

    RouteDto findRoute(int id);

    List<RouteDto> findAllRoute();
}
