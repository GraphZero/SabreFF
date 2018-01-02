package com.sabre.services;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by Andrzej on 2017-11-09.
 */

@Service
public class CalculateDistancesService {
    private GeoCodeService geoCodeService;
    private Pair<Double, Double> city1;
    private Pair<Double, Double> city2;
    private static final long radius = 6371;


    @Autowired
    public CalculateDistancesService(GeoCodeService geoCodeService) {
        this.geoCodeService = geoCodeService;
    }

    public double calculateDistance(String id1, String id2) {
        city1 = geoCodeService.returnLattitudeAndLongitude(id1);
        city2 = geoCodeService.returnLattitudeAndLongitude(id2);
        return 2 * radius * Math.asin(calculateSqrt(city1.getKey() * Math.PI / 180,
                city2.getKey() * Math.PI / 180,
                city1.getValue() * Math.PI / 180,
                city2.getValue() * Math.PI / 180));
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
