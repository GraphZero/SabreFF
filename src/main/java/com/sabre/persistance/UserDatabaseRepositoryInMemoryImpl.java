package com.sabre.persistance;

import com.sabre.domain.UserEntity;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrzej on 2017-10-31.
 */


@Repository
public class UserDatabaseRepositoryInMemoryImpl implements UserDatabaseRepository {
    List<UserEntity> users;

    public UserDatabaseRepositoryInMemoryImpl() {
        users = new ArrayList<>();
    }

    @Override
    public double addMiles(double miles, String userEmail  ) {

        for ( UserEntity x : users ){
            if ( x.getEmail() == userEmail ){
                x.setMiles( x.getMiles() + miles);
            }
        }
        return miles;
    }

    @Override
    public double getMilesByUserId(long userId) {
        for ( UserEntity x : users ){
            if ( x.getUserId() == userId ) return x.getMiles();
        }
        return -1;
    }

    @Override
    public void addUser(UserEntity userEntity) {
        users.add(userEntity);
    }

    @Override
    public void deleteUser(long userId) {
        users.remove(userId);
    }

    @Override
    public boolean isUserInDatabase(String email) {
        for ( UserEntity u : users ){
            if ( u.getEmail().equals(email) ) return true;
        }
        return false;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return users;
    }

    @Nullable
    @Override
    public UserEntity getUserByEmail(String email) {
        for ( UserEntity u : users ){
            if ( u.getEmail().equals(email) ) return u;
        }
        return null;
    }
}
