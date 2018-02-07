package com.sabre.services;

import com.sabre.domain.Flight;
import com.sabre.domain.FlightClass;
import com.sabre.persistance.FlightsDatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

/**
 * Controls communication with DAO.
 */

@Service
public class FlightsService {
    private final FlightsDatabaseRepository flightsDatabaseRepository;
    private final CalculateDistancesBetweenAirportsService calculateDistancesBetweenAirportsService;
    private final UserService userService;

    @Autowired
    public FlightsService(@Qualifier("dbSpringFlightsRepository") FlightsDatabaseRepository flightsDatabaseRepository,
                          CalculateDistancesBetweenAirportsService calculateDistancesBetweenAirportsService,
                          UserService userService) {
        this.flightsDatabaseRepository = flightsDatabaseRepository;
        this.calculateDistancesBetweenAirportsService = calculateDistancesBetweenAirportsService;
        this.userService = userService;
    }

    /**
     * Persists fly that doesn't need calculating its distance
     */
    public void persistFlight(Flight flight) {
        flightsDatabaseRepository.save(flight);
        userService.addMiles( (long) flight.getMiles(), flight.getUserEmail());
    }

    /**
     * Persists fly that needs calculating its distance
     */
    public void persistFlight(String userEmail, String airportDepartureCode, String airportArrivalCode,
                              String airlineCode, FlightClass flightClass, boolean returnTicket,
                              long departureFlightDate, long returnFlightlDate) {
        double distance = calculateDistancesBetweenAirportsService.calculateDistance(airportDepartureCode, airportArrivalCode);
        flightsDatabaseRepository.save(new Flight(userEmail, airportDepartureCode, airportArrivalCode, airlineCode,
                distance,
                flightClass, returnTicket,
                Instant.ofEpochMilli(departureFlightDate).atZone(ZoneId.systemDefault()).toLocalDate(),
                Instant.ofEpochMilli(returnFlightlDate).atZone(ZoneId.systemDefault()).toLocalDate()));
        userService.addMiles( (long) distance, userEmail);
    }

    public List<Flight> getAllFlights() {
        return flightsDatabaseRepository.findAll();
    }

    public List<Flight> getFlightsByUserEmail(String email) {
        return flightsDatabaseRepository.findFlightByUserEmail(email.trim());
    }

}
