package com.example.demo.controller;

import com.example.demo.model.CarDto;
import com.example.demo.model.DriverDto;
import com.example.demo.service.car.CarService;
import com.example.demo.service.driver.DriverService;
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
@RequestMapping("api/v1/drivers")
public class DriverController {
    private static final Logger logger = LoggerFactory.getLogger(DriverController.class);

    @Autowired
    private DriverService driverService;


    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> createDriver(@RequestBody DriverDto driverDto){
        logger.info("created new driver : {}", driverDto);

        try{
            DriverDto createdDriverDto =  driverService.createDriver(driverDto);
            logger.info("create successfully");

            return new ResponseEntity<>(createdDriverDto, HttpStatus.OK);
        }catch (NullPointerException e){
            logger.error("error : {}", e);
            return new ResponseEntity<>(HttpStatus.SEE_OTHER.getReasonPhrase(), HttpStatus.SEE_OTHER);
        }
    }

    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> updateDriver( @RequestBody DriverDto dto){
        logger.info("update driver = {}", dto);

        DriverDto updatedDriverDto = driverService.updateDriver(dto);

        if (updatedDriverDto != null){
            logger.info("update successfully");
            return new ResponseEntity<>(updatedDriverDto, HttpStatus.OK);
        }
        else{
            logger.error("update error");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(),HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable int id){
        logger.info("delete driver with id = {}",id);

        DriverDto deletedDriverDto = driverService.deleteDriver(id);

        if(deletedDriverDto != null){
            logger.info("delete successfully");
            return new ResponseEntity<>(deletedDriverDto,HttpStatus.OK);
        }
        else {
            logger.error("delete failed");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable int id){
        logger.info("get driver by id : {}",id);

        DriverDto driverDto = driverService.findDriver(id);

        if(driverDto != null){
            logger.info("get driver by id successfully");
            return new ResponseEntity<>(driverDto, HttpStatus.OK);
        }

        else {
            logger.error("get driver by id failed");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> findAll(){
        logger.info("get all driver" );

        List<DriverDto> listDriverDto = driverService.findAllDriver();

        if(listDriverDto.size() != 0){
            logger.info("get all driver successfully");
            return new ResponseEntity<>(listDriverDto, HttpStatus.OK);
        }

        else {
            logger.error("get driver by failed");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
        }
    }
}
