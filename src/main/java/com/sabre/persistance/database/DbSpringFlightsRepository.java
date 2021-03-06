package com.sabre.persistance.database;

import com.sabre.domain.Flight;
import com.sabre.persistance.FlightsDatabaseRepository;
import com.sabre.persistance.database.SpringFlightsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class DbSpringFlightsRepository implements FlightsDatabaseRepository {
    private SpringFlightsRepository springFlightsRepository;

    @Autowired
    public DbSpringFlightsRepository(SpringFlightsRepository springFlightsRepository) {
        this.springFlightsRepository = springFlightsRepository;
    }

    @Override
    public void save(Flight flight) {
        springFlightsRepository.save(flight);
    }

    @Override
    public List<Flight> findAll() {
        return springFlightsRepository.findAll();
    }

    @Override
    public List<Flight> findFlightByUserEmail(String email) {
        return springFlightsRepository.findByUserEmail(email);
    }
}
