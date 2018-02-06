package com.sabre.persistance;

import com.sabre.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserDatabaseRepository {
    void save(User user);
    void delete(long userId);
    boolean existsByEmail(String email);
    List<User> findAll();
    Optional<User> findByEmail(String email);
    double findMilesByEmail(String userEmail);
    double addMiles(double miles, String userEmail);
}
