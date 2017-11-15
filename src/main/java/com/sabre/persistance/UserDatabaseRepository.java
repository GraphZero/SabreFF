package com.sabre.persistance;

import com.sabre.domain.UserEntity;

import java.util.List;

/**
 * Created by Andrzej on 2017-10-31.
 */
public interface UserDatabaseRepository {
    void addUser(UserEntity userEntity);
    void deleteUser(long userId);
    boolean isUserInDatabase(String email);
    List<UserEntity> getAllUsers();
    UserEntity getUserByEmail(String email);
    double getMilesByUserId( long userId);
    double addMiles(double miles, String userEmail);
}
