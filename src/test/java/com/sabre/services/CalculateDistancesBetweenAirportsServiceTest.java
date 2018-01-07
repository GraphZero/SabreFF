package com.sabre.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CalculateDistancesBetweenAirportsServiceTest {

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

    }

    @Autowired
    CalculateDistancesBetweenAirportsService calculateDistancesBetweenAirportsService;


    @Test
    public void calculateDistance() {
        assertTrue( calculateDistancesBetweenAirportsService.calculateDistance("KRK", "WMI") > 270.5 &&
                calculateDistancesBetweenAirportsService.calculateDistance("KRK", "WMI") < 270.8);
    }
}