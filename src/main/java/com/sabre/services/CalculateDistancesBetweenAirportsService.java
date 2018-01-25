package com.sabre.services;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

/**
 * Used to calculate haversine formula. Uses {@link com.sabre.services.GeoCodeService} to get data from Sabre REST api.
 */

@Service
public class CalculateDistancesBetweenAirportsService {
    private GeoCodeService geoCodeService;
    private Pair<Double, Double> city1;
    private Pair<Double, Double> city2;
    private static final long radius = 6371;

    @Autowired
    public CalculateDistancesBetweenAirportsService(GeoCodeService geoCodeService) {
        this.geoCodeService = geoCodeService;
    }

    /**
     * @return -1 if couldnt get the correct latitude or longitude.
     */
    public double calculateDistance(String id1, String id2) {
        Optional<Pair<Double,Double>> city1 = geoCodeService.returnLattitudeAndLongitude(id1);
        Optional<Pair<Double,Double>> city2 = geoCodeService.returnLattitudeAndLongitude(id2);
        if ( city1.isPresent() && city2.isPresent() ){
            return 2 * radius * Math.asin(calculateSqrt(city1.get().getKey() * Math.PI / 180,
                    city2.get().getKey() * Math.PI / 180,
                    city1.get().getValue() * Math.PI / 180,
                    city2.get().getValue() * Math.PI / 180));
        }
        return -1;
    }

    private double calculateSqrt(final double latitudeOne, final double latitudeTwo,
                                 final double longitudeOne, final double longitudeTwo) {
        return Math.sqrt(haversineFunction(latitudeTwo - latitudeOne) + Math.cos(latitudeOne) * Math.cos(latitudeTwo)
                * haversineFunction(longitudeTwo - longitudeOne));
    }

    private double haversineFunction(final double angle) {
        return Math.sin(angle / 2) * Math.sin(angle / 2);
    }


}
