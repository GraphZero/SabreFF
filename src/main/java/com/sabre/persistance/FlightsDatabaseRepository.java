package com.sabre.persistance;

import com.sabre.domain.Flight;

import java.util.List;


public interface FlightsDatabaseRepository {
    void persistFlight(Flight flight);
    List<Flight> getAllFlights();
    List<Flight> getFlightsByUserEmail(String email);
}
