package com.sabre.services;

import com.sabre.domain.UserEntity;
import com.sabre.persistance.UserDatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Andrzej on 2017-11-09.
 */

@Service
public class UserService {
    UserDatabaseRepository userDatabaseRepository;
    CalculateDistancesBetweenAirportsService calculateDistancesBetweenAirportsService;
    long milesToLvlUp;

    @Autowired
    public UserService(UserDatabaseRepository userDatabaseRepository, CalculateDistancesBetweenAirportsService calculateDistancesBetweenAirportsService) {
        this.calculateDistancesBetweenAirportsService = calculateDistancesBetweenAirportsService;
        this.userDatabaseRepository = userDatabaseRepository;
        milesToLvlUp = 10000;
    }

    public double getMilesByUserId(final String userEmail) {
        return userDatabaseRepository.getMilesByUserEmail(userEmail);
    }

    public double addMilesByCities(final String userEmail, final String cityId1, final String cityId2) {
        return userDatabaseRepository
                .addMiles(
                        calculateDistancesBetweenAirportsService.calculateDistance(cityId1, cityId2),
                        userEmail
                );
    }

    public void addMiles(final long miles, final String userEmail) {
        userDatabaseRepository.addMiles(miles, userEmail);
    }

    public double getMissingMiles(final String userEmail) {
        return milesToLvlUp - userDatabaseRepository.getMilesByUserEmail(userEmail);
    }

    public void deleteUser(final long userId) {
        userDatabaseRepository.deleteUser(userId);
    }

    public void addUser(final UserEntity userEntity) {
        userDatabaseRepository.persistUser(userEntity);
    }

    public boolean isUserInDatabase(final String email){
        return userDatabaseRepository.isUserInDatabase(email);
    }

    public List<UserEntity> getAllUsers(){
        return userDatabaseRepository.getAllUsers();
    }

    @Nullable
    public UserEntity getUserByEmail(String email) {
        return userDatabaseRepository.getUserByEmail(email);
    }

}
