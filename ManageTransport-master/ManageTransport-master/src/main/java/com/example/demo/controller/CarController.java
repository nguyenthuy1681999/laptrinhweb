package com.example.demo.controller;

import com.example.demo.model.CarDto;
import com.example.demo.service.car.CarService;
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
@RequestMapping("api/v1/cars")
public class CarController {
    private static final Logger logger = LoggerFactory.getLogger(CarController.class);

    @Autowired
    private CarService carService;

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> createPost(@RequestBody CarDto carDto){
        logger.info("created new car : {}", carDto);

        try{
            CarDto createdCarDto =  carService.createCar(carDto);
            logger.info("create successfully");

            return new ResponseEntity<>(createdCarDto, HttpStatus.OK);
        }catch (NullPointerException e){
            logger.error("error : {}", e);
            return new ResponseEntity<>(HttpStatus.SEE_OTHER.getReasonPhrase(), HttpStatus.SEE_OTHER);
        }
    }

    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> updatePost( @RequestBody CarDto dto){
        logger.info("update post = {}", dto);

        CarDto updatedCarDto = carService.updateCar(dto);

        if (updatedCarDto != null){
            logger.info("update successfully");
            return new ResponseEntity<>(updatedCarDto, HttpStatus.OK);
        }
        else{
            logger.error("update error");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(),HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable int id){
        logger.info("delete car with id = {}",id);

        CarDto deletedCarDto = carService.deleteCar(id);

        if(deletedCarDto != null){
            logger.info("delete successfully");
            return new ResponseEntity<>(deletedCarDto,HttpStatus.OK);
        }
        else {
            logger.error("delete failed");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable int id){
        logger.info("get car by id : {}",id);

        CarDto carDto = carService.findCar(id);

        if(carDto != null){
            logger.info("get car by id successfully");
            return new ResponseEntity<>(carDto, HttpStatus.OK);
        }

        else {
            logger.error("get car by id failed");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> findAll(){
        logger.info("get all post" );

        List<CarDto> listDCarDto = carService.findAllCar();

        if(listDCarDto.size() != 0){
            logger.info("get all car successfully");
            return new ResponseEntity<>(listDCarDto, HttpStatus.OK);
        }

        else {
            logger.error("get car failed");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
        }
    }
}
