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
    CalculateDistancesService calculateDistancesService;

    @Autowired
    public FlightsService(FlightsDatabaseRepository flightsDatabaseRepository, CalculateDistancesService calculateDistancesService) {
        this.flightsDatabaseRepository = flightsDatabaseRepository;
        this.calculateDistancesService = calculateDistancesService;
    }

    public void addFlight(FlightEntity flightEntity){
        /*if ( flightEntity.getMiles() == 1 ){
            flightEntity.setMiles( calculateDistancesService.calculateDistance(flightEntity.getAirportDepartureCode(), flightEntity.getAirportArrivalCode()) );
        }*/
        flightsDatabaseRepository.persistFlight(flightEntity);
    }

    public List<FlightEntity> getAllFlights(){
        return flightsDatabaseRepository.getAllFlights();
    }

    public List<FlightEntity> getFlightsByUserEmail(String email){
        return flightsDatabaseRepository.getFlightsByUserEmail(email);
    }

}
