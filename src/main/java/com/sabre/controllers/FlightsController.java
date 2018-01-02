package com.sabre.controllers;

import com.sabre.domain.FlightEntity;
import com.sabre.domain.UserEntity;
import com.sabre.services.FlightsService;
import com.sabre.services.ParseDataFromCsvFileAndInsertToDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrzej on 2017-11-15.
 */
@RestController
public class FlightsController {
    FlightsService flightsService;
    ParseDataFromCsvFileAndInsertToDatabaseService parseDataFromCsvFileAndInsertToDatabaseService;

    @Autowired
    public FlightsController(FlightsService flightsService,
                             ParseDataFromCsvFileAndInsertToDatabaseService parseDataFromCsvFileAndInsertToDatabaseService) {
        this.flightsService = flightsService;
        this.parseDataFromCsvFileAndInsertToDatabaseService = parseDataFromCsvFileAndInsertToDatabaseService;
        parse();
    }

    private void parse(){
        try {
            parseDataFromCsvFileAndInsertToDatabaseService.readDataFromCsvFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping( path = "/getFlightsByUserEmail/{email:.+}", method = RequestMethod.GET)
    public ResponseEntity<List<FlightEntity>> getFlightsByUserEmail(@PathVariable final String email){
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>( flightsService.getFlightsByUserEmail(email) , responseHeaders, HttpStatus.OK);
    }

    @RequestMapping( path = "/getAllFlights", method = RequestMethod.GET)
    public ResponseEntity<List<FlightEntity>> getAllFlights(){
        HttpHeaders responseHeaders = new HttpHeaders();
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
