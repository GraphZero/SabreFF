package com.sabre.services;

import com.sabre.domain.UserEntity;
import com.sabre.persistance.UserDatabaseRepository;
import org.apache.catalina.User;
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

    public double addMilesByCities(final String userEmail, final String cityId1, final String cityId2) {
        return userDatabaseRepository
                .addMiles(
                        calculateDistancesService.calculateDistance(cityId1, cityId2),
                        userEmail
                );
    }

    public void addMiles(final long miles, final String userEmail) {
        userDatabaseRepository.addMiles(miles, userEmail);
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
