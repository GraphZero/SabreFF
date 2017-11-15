package com.sabre.persistance;

import com.sabre.domain.FlightEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
    public void addFlight(FlightEntity flightEntity) {
        flights.add(flightEntity);
    }

    @Override
    public List<FlightEntity> getAllFlights() {
        return flights;
    }
}
