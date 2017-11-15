package com.sabre.services;

import com.sabre.domain.UserEntity;
import com.sabre.persistance.UserDatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Andrzej on 2017-11-09.
 */

@Service
public class UserService {
    UserDatabaseRepository userDatabaseRepository;
    CalculateDistancesService calculateDistancesService;
    long milesToLvlUp;

    @Autowired
    public UserService(UserDatabaseRepository userDatabaseRepository, CalculateDistancesService calculateDistancesService) {
        this.calculateDistancesService = calculateDistancesService;
        this.userDatabaseRepository = userDatabaseRepository;
        milesToLvlUp = 10000;
    }

    public double getMilesByUserId(final long userId) {
        return userDatabaseRepository.getMilesByUserId(userId);
    }

    public double addMilesByCities(final long userId, final String cityId1, final String cityId2) {
        return userDatabaseRepository
                .addMiles(
                        calculateDistancesService.calculateDistance(cityId1, cityId2),
                        userId
                );
    }

    public void addMiles(final long miles, final long userId) {
        userDatabaseRepository.addMiles(miles, userId);
    }

    public double getMissingMiles(final long userId) {
        return milesToLvlUp - userDatabaseRepository.getMilesByUserId(userId);
    }

    public void deleteUser(final long userId) {
        userDatabaseRepository.deleteUser(userId);
    }

    public void addUser(final UserEntity userEntity) {
        userDatabaseRepository.addUser(userEntity);
    }

}
