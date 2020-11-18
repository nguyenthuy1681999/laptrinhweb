package com.example.demo.service.trip;

import com.example.demo.model.TripDto;

import java.util.List;

public interface TripService {
    TripDto createTrip(TripDto dto);

    TripDto updateTrip(TripDto dto);

    TripDto deleteTrip(int id);

    TripDto findTrip(int id);

    List<TripDto> findAllTrip();
}
