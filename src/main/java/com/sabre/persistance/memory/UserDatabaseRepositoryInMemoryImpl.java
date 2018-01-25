package com.sabre.persistance.memory;

import com.sabre.domain.User;
import com.sabre.persistance.UserDatabaseRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDatabaseRepositoryInMemoryImpl implements UserDatabaseRepository {
    List<User> users;

    public UserDatabaseRepositoryInMemoryImpl() {
        users = new ArrayList<>();
    }

    @Override
    public double addMiles(double miles, String userEmail  ) {
        for ( User x : users ){
            if ( x.getEmail() == userEmail ){
                x.setInitialMiles( x.getInitialMiles() + miles);
            }
        }
        return miles;
    }

    @Override
    public double findMilesByEmail(String userEmail) {
        for ( User x : users ){
            if ( x.getEmail() == userEmail ) return x.getInitialMiles();
        }
        return -1;
    }

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public void delete(long userId) {
        users.remove(userId);
    }

    @Override
    public boolean existsByEmail(String email) {
        for ( User u : users ){
            if ( u.getEmail().equals(email) ) return true;
        }
        return false;
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Nullable
    @Override
    public User findByEmail(String email) {
        for ( User u : users ){
            if ( u.getEmail().equals(email) ) return u;
        }
        return null;
    }
}
