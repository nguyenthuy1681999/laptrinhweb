package com.example.demo.controller;

import com.example.demo.model.TripDto;
import com.example.demo.service.trip.TripService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin( origins = "*" )
@RestController
@RequestMapping("api/v1/trips")
public class TripController {
    private static final Logger logger = LoggerFactory.getLogger(TripController.class);

    @Autowired
    private TripService tripService;


    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> createTrip(@RequestBody TripDto tripDto){
        logger.info("created new trip : {}", tripDto);

        try{
            TripDto createdTripDto =  tripService.createTrip(tripDto);
            logger.info("create successfully");

            return new ResponseEntity<>(createdTripDto, HttpStatus.OK);
        }catch (NullPointerException e){
            logger.error("error : {}", e);
            return new ResponseEntity<>(HttpStatus.SEE_OTHER.getReasonPhrase(), HttpStatus.SEE_OTHER);
        }
    }

    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> updateTrip( @RequestBody TripDto dto){
        logger.info("update trip = {}", dto);

        TripDto updatedTripDto = tripService.updateTrip(dto);

        if (updatedTripDto != null){
            logger.info("update successfully");
            return new ResponseEntity<>(updatedTripDto, HttpStatus.OK);
        }
        else{
            logger.error("update error");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(),HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable int id){
        logger.info("delete route with id = {}",id);

        TripDto deletedTripDto = tripService.deleteTrip(id);

        if(deletedTripDto != null){
            logger.info("delete successfully");
            return new ResponseEntity<>(deletedTripDto,HttpStatus.OK);
        }
        else {
            logger.error("delete failed");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable int id){
        logger.info("get trip by id : {}",id);

        TripDto tripDto = tripService.findTrip(id);

        if(tripDto != null){
            logger.info("get trip by id successfully");
            return new ResponseEntity<>(tripDto, HttpStatus.OK);
        }

        else {
            logger.error("get route by id failed");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> findAll(){
        logger.info("get all trip" );

        List<TripDto> listTripDto = tripService.findAllTrip();

        if(listTripDto.size() != 0){
            logger.info("get all route successfully");
            return new ResponseEntity<>(listTripDto, HttpStatus.OK);
        }

        else {
            logger.error("get trip by failed");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
        }
    }
}
