package com.sabre.services;

import com.sabre.domain.User;
import com.sabre.persistance.UserDatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

/**
 * Controls communication with DAO.
 */

@Service
public class UserService {
    private UserDatabaseRepository userDatabaseRepository;
    private CalculateDistancesBetweenAirportsService calculateDistancesBetweenAirportsService;

    @Autowired
    public UserService(@Qualifier("dbSpringUserRepository") UserDatabaseRepository userDatabaseRepository,
                       CalculateDistancesBetweenAirportsService calculateDistancesBetweenAirportsService) {
        this.calculateDistancesBetweenAirportsService = calculateDistancesBetweenAirportsService;
        this.userDatabaseRepository = userDatabaseRepository;
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

    public boolean validateUser(final String email, final String name){
        return userDatabaseRepository.findByEmail(email)
                .map( x -> x.getFirstName().trim().equalsIgnoreCase(name.trim()))
                .orElse(false);
    }

    public Optional<User> getUserByEmail(String email) {
        return userDatabaseRepository.findByEmail(email);
        }

}
