package com.sabre.persistance;

import com.sabre.domain.UserEntity;
import org.springframework.stereotype.Repository;

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
        users.add( new UserEntity ( "Andrew", "Ad", "a@b", 10, 1 ) );
    }

    @Override
    public void addMiles(long miles, long userId ) {

        for ( UserEntity x : users ){
            if ( x.getUserId() == userId ){
                x.setMiles( x.getMiles() + miles);
            }
        }
    }

    @Override
    public long getMilesByUserId(long userId) {
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
}
