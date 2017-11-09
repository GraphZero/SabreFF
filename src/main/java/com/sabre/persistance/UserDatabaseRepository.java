package com.sabre.persistance;

import com.sabre.domain.UserEntity;

/**
 * Created by Andrzej on 2017-10-31.
 */
public interface UserDatabaseRepository {
    void addUser(UserEntity userEntity);
    void deleteUser(long userId);
    long getMilesByUserId( long userId);
    void addMiles(long miles, long userId);
}
