package com.sabre.persistance;
import com.sabre.domain.Flight;
import com.sabre.persistance.database.SpringFlightsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DbSpringFlightsRepositoryTest {

    @Autowired
    private SpringFlightsRepository springFlightsRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void save() {
        Flight f = new Flight();
        f.setAirportArrivalCode("AA");
        f.setUserEmail("AAA");
        f.setAirportDepartureCode("AA");
        springFlightsRepository.save(f);
        assertEquals( springFlightsRepository.findAll().size(), 1 );
    }

    @Test
    public void findAll() {
        Flight f = new Flight();
        f.setAirportArrivalCode("AA");
        f.setUserEmail("AAA");
        f.setAirportDepartureCode("AA");
        Flight f1 = new Flight();
        f1.setAirportArrivalCode("AA");
        f1.setUserEmail("AAA");
        f1.setAirportDepartureCode("AA");
        springFlightsRepository.save(f);
        springFlightsRepository.save(f1);
        assertEquals( springFlightsRepository.findAll().size(), 2 );
    }

    @Test
    public void findFlightByUserEmail() {
        Flight f = new Flight();
        f.setAirportArrivalCode("AA");
        f.setUserEmail("AAA");
        f.setAirportDepartureCode("AA");
        springFlightsRepository.save(f);
        assertTrue( springFlightsRepository.findByUserEmail("AAA").equals(Arrays.asList(f)) );
    }
}