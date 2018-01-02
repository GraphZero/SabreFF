package com.sabre.services;

import com.sabre.domain.Flight;
import com.sabre.domain.FlightClass;
import com.sabre.persistance.FlightsDatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Andrzej on 2017-11-15.
 */

@Service
public class FlightsService {
    private FlightsDatabaseRepository flightsDatabaseRepository;
    private CalculateDistancesBetweenAirportsService calculateDistancesBetweenAirportsService;

    @Autowired
    public FlightsService(FlightsDatabaseRepository flightsDatabaseRepository,
                          CalculateDistancesBetweenAirportsService calculateDistancesBetweenAirportsService) {
        this.flightsDatabaseRepository = flightsDatabaseRepository;
        this.calculateDistancesBetweenAirportsService = calculateDistancesBetweenAirportsService;
    }

    /**
     * Persists fly that doesn't need calculating its distance
     */
    public void persistFlight(Flight flight) {
        flightsDatabaseRepository.persistFlight(flight);
    }

    /**
     * Persists fly that needs calculating its distance
     */

    public void persistFlight(String userEmail, String airportDepartureCode, String airportArrivalCode,
                              String airlineCode, FlightClass flightClass, boolean returnTicket,
                              Calendar departureFlightDate, Calendar returnFlightlDate) {
        flightsDatabaseRepository.persistFlight(new Flight(userEmail, airportDepartureCode, airportArrivalCode,
                airlineCode, calculateDistancesBetweenAirportsService.calculateDistance(airportDepartureCode,
                airportArrivalCode), flightClass, returnTicket, departureFlightDate, returnFlightlDate));
    }

    public List<Flight> getAllFlights() {
        return flightsDatabaseRepository.getAllFlights();
    }

    public List<Flight> getFlightsByUserEmail(String email) {
        return flightsDatabaseRepository.getFlightsByUserEmail(email);
    }

}
