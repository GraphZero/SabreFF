package com.sabre.services;

import com.sabre.domain.FlightEntity;
import com.sabre.persistance.FlightsDatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Andrzej on 2017-11-15.
 */

@Service
public class FlightsService {
    FlightsDatabaseRepository flightsDatabaseRepository;

    @Autowired
    public FlightsService(FlightsDatabaseRepository flightsDatabaseRepository) {
        this.flightsDatabaseRepository = flightsDatabaseRepository;
    }

    public void addFlight(FlightEntity flightEntity){
        flightsDatabaseRepository.addFlight(flightEntity);
    }

    public List<FlightEntity> getAllFlights(){
        return flightsDatabaseRepository.getAllFlights();
    }

}
