package com.sabre.persistance;

import com.sabre.domain.FlightEntity;

import java.util.List;

/**
 * Created by Andrzej on 2017-11-15.
 */
public interface FlightsDatabaseRepository {
    void addFlight(FlightEntity flightEntity);
    List<FlightEntity> getAllFlights();
    List<FlightEntity> getFlightsByUserEmail(String email);
}
