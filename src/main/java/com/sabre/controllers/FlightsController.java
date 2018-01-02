package com.sabre.controllers;

import com.sabre.domain.FlightClass;
import com.sabre.domain.Flight;
import com.sabre.services.FlightsService;
import com.sabre.services.ParseDataFromCsvFileAndInsertToDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

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

    private void parse() {
        try {
            parseDataFromCsvFileAndInsertToDatabaseService.readDataFromCsvFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(path = "/getFlightsByUserEmail/{email}", method = RequestMethod.GET)
    public ResponseEntity<List<Flight>> getFlightsByUserEmail(@PathVariable final String email) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>(flightsService.getFlightsByUserEmail(email), responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(path = "/getAllFlights", method = RequestMethod.GET)
    public ResponseEntity<List<Flight>> getAllFlights() {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>(flightsService.getAllFlights(), responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(path = "/postFlight", method = RequestMethod.POST)
    public ResponseEntity<String> postFlight(@RequestBody final Flight flight) {
        flightsService.persistFlight(flight);
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>("Successfully added flight!", responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(path = "/postIncompleteFlight", method = RequestMethod.POST)
    public ResponseEntity<String> postIncompleteFlight(@RequestParam("userEmail") final String userEmail,
                                                       @RequestParam("airportDepartureCode") final String airportDepartureCode,
                                                       @RequestParam("airportArrivalCode") final String airportArrivalCode,
                                                       @RequestParam("airlineCode") final String airlineCode,
                                                       @RequestParam("flightClass") final FlightClass flightClass,
                                                       @RequestParam("returnTicket") final boolean returnTicket,
                                                       @RequestParam("departureFlightDate") final Calendar departureFlightDate,
                                                       @RequestParam("returnFlightlDate") final Calendar returnFlightlDate) {
        flightsService.persistFlight(userEmail, airportDepartureCode, airportArrivalCode, airlineCode, flightClass,
                returnTicket, departureFlightDate, returnFlightlDate);
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>("Successfully added flight!", responseHeaders, HttpStatus.OK);
    }

}
