package com.sabre.services;

import com.sabre.domain.User;
import com.sabre.persistance.UserDatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Andrzej on 2017-11-09.
 */

@Service
public class UserService {
    private UserDatabaseRepository userDatabaseRepository;
    private CalculateDistancesBetweenAirportsService calculateDistancesBetweenAirportsService;
    long milesToLvlUp;

    @Autowired

    public UserService(@Qualifier("dbSpringUserRepository") UserDatabaseRepository userDatabaseRepository, CalculateDistancesBetweenAirportsService calculateDistancesBetweenAirportsService) {
        this.calculateDistancesBetweenAirportsService = calculateDistancesBetweenAirportsService;
        this.userDatabaseRepository = userDatabaseRepository;
        milesToLvlUp = 10000;
    }

    public double getMilesByUserId(final String userEmail) {
        return userDatabaseRepository.findMilesByEmail(userEmail);
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
        return milesToLvlUp - userDatabaseRepository.findMilesByEmail(userEmail);
    }

    public void deleteUser(final long userId) {
        userDatabaseRepository.delete(userId);
    }

    public void addUser(final User user) {
        userDatabaseRepository.save(user);
    }

    public boolean isUserInDatabase(final String email){
        return userDatabaseRepository.existsByEmail(email);
    }

    public List<User> getAllUsers(){
        return userDatabaseRepository.findAll();
    }

    @Nullable
    public User getUserByEmail(String email) {
        return userDatabaseRepository.findByEmail(email);
    }

}
