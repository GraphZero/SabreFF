package com.sabre.services;

import com.sabre.domain.FlightClass;
import com.sabre.domain.Flight;
import com.sabre.domain.User;
import com.sabre.persistance.FlightsDatabaseRepository;
import com.sabre.persistance.UserDatabaseRepository;
import com.sabre.persistance.database.DbSpringUserRepository;
import com.sabre.persistance.database.SpringUserRepository;
import com.sabre.persistance.memory.FlightsDatabaseRepositoryInMemoryImpl;
import com.sabre.persistance.memory.UserDatabaseRepositoryInMemoryImpl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightsServiceTest {

    @Autowired
    private FlightsService flightsService;

    @Autowired
    @Qualifier("dbSpringFlightsRepository")
    private FlightsDatabaseRepository flightsDatabaseRepository;

    @Autowired
    @Qualifier("dbSpringUserRepository")
    private UserDatabaseRepository userDatabaseRepository;


    @Test
    public void shouldPersistFinishedFlight() {
        userDatabaseRepository.save(new User("A", "B", "A",
                0, null, "", ""));
        long dbSize = flightsDatabaseRepository.findAll().size();
        flightsService.persistFlight( new Flight("A", "B", "C", "D",
                500.0, FlightClass.ECONOMY, true, null, null ) );
        assertEquals( dbSize + 1, flightsDatabaseRepository.findAll().size() );
    }

    @Test
    public void shouldPersistFlightWithoutMiles() {
        userDatabaseRepository.save(new User("B", "B", "B",
                0, null, "", ""));
        long dbSize = flightsDatabaseRepository.findAll().size();
        flightsService.persistFlight( "B", "KRK", "WMI", "D",
                FlightClass.ECONOMY, true, 0, 0 );
        assertEquals( dbSize + 1, flightsDatabaseRepository.findAll().size() );
    }

    @Test
    public void shouldReturnAllFlights() {
        userDatabaseRepository.save(new User("C", "B", "C",
                0, null, "", ""));
        long dbSize = flightsDatabaseRepository.findAll().size();
        flightsService.persistFlight( "C", "KRK", "WMI", "D",
                FlightClass.ECONOMY, true, 0, 0 );
        flightsService.persistFlight( "C", "KRK", "WMI", "D",
                FlightClass.ECONOMY, true, 0, 0 );
        assertEquals( dbSize + 2, flightsDatabaseRepository.findAll().size() );
    }

    @Test
    public void shouldReturnFlightByUserEmail() {
        userDatabaseRepository.save(new User("D", "B", "D",
                0, null, "", ""));
        flightsService.persistFlight( "D", "KRK", "WMI", "D",
                FlightClass.ECONOMY, true, 0, 0 );
        assertNotNull(  flightsDatabaseRepository.findFlightByUserEmail("A") );
    }

}