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
    long milesToLvlUp;

    @Autowired
    public UserService(UserDatabaseRepository userDatabaseRepository) {
        this.userDatabaseRepository = userDatabaseRepository;
        milesToLvlUp = 10000;
    }

    public long getMilesByUserId( long userId){
        return userDatabaseRepository.getMilesByUserId(userId);
    }

    public void addMiles(long miles, long userId){
        userDatabaseRepository.addMiles(miles, userId);
    }

    public long getMissingMiles(long userId){
       return milesToLvlUp - userDatabaseRepository.getMilesByUserId(userId);
    }

    public void deleteUser(long userId){
        userDatabaseRepository.deleteUser(userId);
    }

    public void addUser(UserEntity userEntity){
        userDatabaseRepository.addUser(userEntity);
    }

}
