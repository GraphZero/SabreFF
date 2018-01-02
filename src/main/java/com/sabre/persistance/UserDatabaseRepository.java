package com.sabre.persistance;

import com.sabre.domain.User;

import java.util.List;

/**
 * Created by Andrzej on 2017-10-31.
 */
public interface UserDatabaseRepository {
    void save(User user);
    void delete(long userId);
    boolean existsByEmail(String email);
    List<User> findAll();
    User findByEmail(String email);
    double findMilesByEmail(String userEmail);
    double addMiles(double miles, String userEmail);
}
