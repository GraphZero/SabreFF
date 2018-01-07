package com.sabre.services;

import com.sabre.domain.User;
import com.sabre.persistance.UserDatabaseRepository;
import com.sabre.persistance.database.DbSpringUserRepository;
import com.sabre.persistance.database.SpringUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private CalculateDistancesBetweenAirportsService calculateDistancesBetweenAirportsService;

    @Autowired
    @Qualifier("dbSpringUserRepository")
    private UserDatabaseRepository dbSpringUserRepository;

    @Autowired
    private SpringUserRepository springUserRepository;

    @TestConfiguration
    @DataJpaTest
    @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
    class UserServiceTestContextConfiguration {

        @Bean
        public GeoCodeService geoCodeService() {
            return new GeoCodeService();
        }

        @Bean
        public CalculateDistancesBetweenAirportsService calculateDistancesBetweenAirportsService() {
            return new CalculateDistancesBetweenAirportsService(geoCodeService());
        }

        @Bean
        public DbSpringUserRepository userDatabaseRepository() {
            return new DbSpringUserRepository(springUserRepository);
        }

        @Bean
        public UserService userService() {
            return new UserService( userDatabaseRepository(), calculateDistancesBetweenAirportsService());
        }


    }

    @Test
    public void addUser() {
        long dbSize = dbSpringUserRepository.findAll().size();
        dbSpringUserRepository.save(new User("A", "B",
                "C", 1, null, "D"));
        assertEquals(dbSize + 1, dbSpringUserRepository.findAll().size());
    }

    @Test
    public void getMilesByUserId() {
        dbSpringUserRepository.save(new User("A", "B",
                "C", 1, null, "D"));
        assertTrue(  dbSpringUserRepository.findMilesByEmail("C") == 1 );
    }

    @Test
    public void addMilesByCities() {
    }

    @Test
    public void addMiles() {
    }

    @Test
    public void getMissingMiles() {
    }

    @Test
    public void deleteUser() {
    }

    @Test
    public void isUserInDatabase() {
    }

    @Test
    public void getAllUsers() {
    }

    @Test
    public void getUserByEmail() {
    }
}