package com.sabre.persistance;

import com.sabre.domain.Flight;

import java.util.List;


public interface FlightsDatabaseRepository {
    void save(Flight flight);
    List<Flight> findAll();
    List<Flight> findFlightByUserEmail(String email);
}
