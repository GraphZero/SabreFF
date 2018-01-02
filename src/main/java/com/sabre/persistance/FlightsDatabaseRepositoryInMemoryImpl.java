package com.sabre.persistance;

import com.sabre.domain.FlightEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Andrzej on 2017-11-15.
 */
@Repository
public class FlightsDatabaseRepositoryInMemoryImpl implements FlightsDatabaseRepository {
    List<FlightEntity> flights;

    public FlightsDatabaseRepositoryInMemoryImpl() {
        flights = new ArrayList<>();
    }

    @Override
    public void persistFlight(FlightEntity flightEntity) {
        flights.add(flightEntity);
    }

    @Override
    public List<FlightEntity> getAllFlights() {
        return flights;
    }

    @Override
    public List<FlightEntity> getFlightsByUserEmail( String email ) {
        return flights.stream()
                .filter( x -> x.getUserEmail().trim().equals(email) )
                .collect(Collectors.toList());
    }
}
