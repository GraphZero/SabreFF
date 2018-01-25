package com.sabre.services;

import com.sabre.domain.Flight;
import com.sabre.domain.FlightClass;
import com.sabre.persistance.FlightsDatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Controls communication with DAO.
 */

@Service
public class FlightsService {
    private FlightsDatabaseRepository flightsDatabaseRepository;
    private CalculateDistancesBetweenAirportsService calculateDistancesBetweenAirportsService;

    @Autowired
    public FlightsService(@Qualifier("dbSpringFlightsRepository") FlightsDatabaseRepository flightsDatabaseRepository,
                          CalculateDistancesBetweenAirportsService calculateDistancesBetweenAirportsService) {
        this.flightsDatabaseRepository = flightsDatabaseRepository;
        this.calculateDistancesBetweenAirportsService = calculateDistancesBetweenAirportsService;
    }

    /**
     * Persists fly that doesn't need calculating its distance
     */
    public void persistFlight(Flight flight) {
        flightsDatabaseRepository.save(flight);
    }

    /**
     * Persists fly that needs calculating its distance
     */
    public void persistFlight(String userEmail, String airportDepartureCode, String airportArrivalCode,
                              String airlineCode, FlightClass flightClass, boolean returnTicket,
                              LocalDate departureFlightDate, LocalDate returnFlightlDate) {
        flightsDatabaseRepository.save(new Flight(userEmail, airportDepartureCode, airportArrivalCode, airlineCode,
                calculateDistancesBetweenAirportsService.calculateDistance(airportDepartureCode, airportArrivalCode),
                flightClass, returnTicket, departureFlightDate, returnFlightlDate));
    }

    public List<Flight> getAllFlights() {
        return flightsDatabaseRepository.findAll();
    }

    public List<Flight> getFlightsByUserEmail(String email) {
        return flightsDatabaseRepository.findFlightByUserEmail(email);
    }

}
