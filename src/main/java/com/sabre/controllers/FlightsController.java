package com.sabre.controllers;

import com.sabre.domain.FlightEntity;
import com.sabre.domain.UserEntity;
import com.sabre.services.FlightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Andrzej on 2017-11-15.
 */
@RestController
public class FlightsController {
    FlightsService flightsService;

    @Autowired
    public FlightsController(FlightsService flightsService) {
        this.flightsService = flightsService;
    }

    @RequestMapping( path = "/getAllFlights", method = RequestMethod.GET)
    public ResponseEntity<List<FlightEntity>> getAllFlights(){
        HttpHeaders responseHeaders = new HttpHeaders();
        System.out.println( flightsService.getAllFlights() );
        return new ResponseEntity<>( flightsService.getAllFlights() , responseHeaders, HttpStatus.OK);
    }

    @RequestMapping( path = "/postFlight", method = RequestMethod.POST)
    public ResponseEntity<String> postFlight(@RequestBody final FlightEntity flightEntity ){
        System.out.println(flightEntity);
        flightsService.addFlight(flightEntity);
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>( "Successfully added flight!", responseHeaders, HttpStatus.OK);
    }



}
