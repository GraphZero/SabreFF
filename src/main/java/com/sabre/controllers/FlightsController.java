package com.sabre.controllers;

import com.sabre.domain.FlightClass;
import com.sabre.domain.Flight;
import com.sabre.services.FlightsService;
import com.sabre.services.PersistDataFromCsvFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
public class FlightsController {
    private final FlightsService flightsService;
    private final PersistDataFromCsvFileService persistDataFromCsvFileService;

    @Autowired
    public FlightsController(FlightsService flightsService,
                             PersistDataFromCsvFileService persistDataFromCsvFileService) {
        this.flightsService = flightsService;
        this.persistDataFromCsvFileService = persistDataFromCsvFileService;
    }


    @RequestMapping(path = "/getFlightsByUserEmail/{email:.+}", method = RequestMethod.GET)
    public ResponseEntity<List<Flight>> getFlightsByUserEmail(@PathVariable final String email) {
        return new ResponseEntity<>(flightsService.getFlightsByUserEmail(email), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(path = "/getAllFlights", method = RequestMethod.GET)
    public ResponseEntity<List<Flight>> getAllFlights() {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>(flightsService.getAllFlights(), responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(path = "/postFlight", method = RequestMethod.POST)
    public ResponseEntity<Void> postFlight(@RequestBody final Flight flight) {
        flightsService.persistFlight(flight);
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>( responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(path = "/postIncompleteFlight", method = RequestMethod.POST)
    public ResponseEntity<String> postIncompleteFlight(@RequestParam("userEmail") final String userEmail,
                                                       @RequestParam("airportDepartureCode") final String airportDepartureCode,
                                                       @RequestParam("airportArrivalCode") final String airportArrivalCode,
                                                       @RequestParam("airlineCode") final String airlineCode,
                                                       @RequestParam("flightClass") final FlightClass flightClass,
                                                       @RequestParam("returnTicket") final boolean returnTicket,
                                                       @RequestParam("departureFlightDate") final LocalDate departureFlightDate,
                                                       @RequestParam("returnFlightlDate") final LocalDate returnFlightlDate) {
        flightsService.persistFlight(userEmail, airportDepartureCode, airportArrivalCode, airlineCode, flightClass,
                returnTicket, departureFlightDate, returnFlightlDate);
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>("Successfully added flight!", responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(path = "/saveDataFromCsv", method = RequestMethod.POST)
    public ResponseEntity<String> postIncompleteFlight() {
        HttpHeaders responseHeaders = new HttpHeaders();
        try {
            persistDataFromCsvFileService.cacheDataFromCsvFile();
            return new ResponseEntity<>("Successfully cached flights and users!", responseHeaders, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Couldn't cache data, cant find csv file!", responseHeaders, HttpStatus.NOT_MODIFIED);
        }

    }

}
