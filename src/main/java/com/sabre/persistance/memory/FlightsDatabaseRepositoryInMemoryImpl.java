package com.sabre.persistance.memory;

import com.sabre.domain.Flight;
import com.sabre.persistance.FlightsDatabaseRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FlightsDatabaseRepositoryInMemoryImpl implements FlightsDatabaseRepository {
    private List<Flight> flights;

    public FlightsDatabaseRepositoryInMemoryImpl() {
        flights = new ArrayList<>();
    }

    @Override
    public void save(Flight flight) {
        flights.add(flight);
    }

    @Override
    public List<Flight> findAll() {
        return flights;
    }

    @Override
    public List<Flight> findFlightByUserEmail(String email ) {
        return flights.stream()
                .filter( x -> x.getUserEmail().trim().equals(email) )
                .collect(Collectors.toList());
    }
}
