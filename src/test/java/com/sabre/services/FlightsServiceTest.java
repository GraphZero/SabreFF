package com.sabre.services;

import com.sabre.domain.FlightClass;
import com.sabre.domain.FlightEntity;
import com.sabre.persistance.FlightsDatabaseRepository;
import com.sabre.persistance.FlightsDatabaseRepositoryInMemoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightsServiceTest {

    @Autowired
    FlightsService flightsService;

    @Autowired
    FlightsDatabaseRepository flightsDatabaseRepository;


    @TestConfiguration
    class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public GeoCodeService geoCodeService() {
            return new GeoCodeService();
        }

        @Bean
        public CalculateDistancesBetweenAirportsService calculateDistancesBetweenAirportsService() {
            return new CalculateDistancesBetweenAirportsService(geoCodeService());
        }

        @Bean
        public FlightsDatabaseRepositoryInMemoryImpl flightsDatabaseRepositoryInMemory() {
            return new FlightsDatabaseRepositoryInMemoryImpl();
        }

        @Bean
        public FlightsService flightsService() {
            return new FlightsService( flightsDatabaseRepositoryInMemory(), calculateDistancesBetweenAirportsService());
        }


    }

    @Test
    public void shouldPersistFinishedFlight() {
        long dbSize = flightsDatabaseRepository.getAllFlights().size();
        flightsService.persistFlight( new FlightEntity("A", "B", "C", "D",
                500.0, FlightClass.ECONOMY, true, null, null ) );
        assertEquals( dbSize + 1, flightsDatabaseRepository.getAllFlights().size() );
    }

    @Test
    public void shouldPersistFlightWithoutMiles() {
        long dbSize = flightsDatabaseRepository.getAllFlights().size();
        flightsService.persistFlight( "A", "KRK", "WMI", "D",
                FlightClass.ECONOMY, true, null, null );
        assertEquals( dbSize + 1, flightsDatabaseRepository.getAllFlights().size() );
    }

}