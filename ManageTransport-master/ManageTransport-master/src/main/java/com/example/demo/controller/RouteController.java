package com.example.demo.controller;

import com.example.demo.model.DriverDto;
import com.example.demo.model.RouteDto;
import com.example.demo.service.driver.DriverService;
import com.example.demo.service.route.RouteService;
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
@RequestMapping("api/v1/routes")

public class RouteController {
    private static final Logger logger = LoggerFactory.getLogger(RouteController.class);

    @Autowired
    private RouteService routeService;

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> createRoute(@RequestBody RouteDto routeDto){
        logger.info("created new route : {}", routeDto);

        try{
            RouteDto createdRouteDto =  routeService.createRoute(routeDto);
            logger.info("create successfully");

            return new ResponseEntity<>(createdRouteDto, HttpStatus.OK);
        }catch (NullPointerException e){
            logger.error("error : {}", e);
            return new ResponseEntity<>(HttpStatus.SEE_OTHER.getReasonPhrase(), HttpStatus.SEE_OTHER);
        }
    }

    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> updateRoute( @RequestBody RouteDto dto){
        logger.info("update route = {}", dto);

        RouteDto updatedRouteDto = routeService.updateRoute(dto);

        if (updatedRouteDto != null){
            logger.info("update successfully");
            return new ResponseEntity<>(updatedRouteDto, HttpStatus.OK);
        }
        else{
            logger.error("update error");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(),HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable int id){
        logger.info("delete route with id = {}",id);

        RouteDto deletedRouteDto = routeService.deleteRoute(id);

        if(deletedRouteDto != null){
            logger.info("delete successfully");
            return new ResponseEntity<>(deletedRouteDto,HttpStatus.OK);
        }
        else {
            logger.error("delete failed");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable int id){
        logger.info("get route by id : {}",id);

        RouteDto routeDto = routeService.findRoute(id);

        if(routeDto != null){
            logger.info("get route by id successfully");
            return new ResponseEntity<>(routeDto, HttpStatus.OK);
        }

        else {
            logger.error("get route by id failed");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> findAll(){
        logger.info("get all route" );

        List<RouteDto> listRouteDto = routeService.findAllRoute();

        if(listRouteDto.size() != 0){
            logger.info("get all route successfully");
            return new ResponseEntity<>(listRouteDto, HttpStatus.OK);
        }

        else {
            logger.error("get route by failed");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
        }
    }
}
