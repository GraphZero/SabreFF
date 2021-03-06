package com.sabre.persistance.database;

import com.sabre.domain.User;
import com.sabre.persistance.UserDatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class DbSpringUserRepository implements UserDatabaseRepository {
    private SpringUserRepository springUserRepository;

    @Autowired
    public DbSpringUserRepository(SpringUserRepository springUserRepository) {
        this.springUserRepository = springUserRepository;
    }

    @Override
    public void save(User user) {
        springUserRepository.save(user);
    }

    @Override
    public void delete(long userId) {
        springUserRepository.delete(userId);
    }

    @Override
    public boolean existsByEmail(String email) {
        return springUserRepository.existsByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return springUserRepository.findAll();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(springUserRepository.findByEmail(email));
    }

    @Override
    public double findMilesByEmail(String userEmail) {
        return springUserRepository.findByEmail(userEmail).getInitialMiles();
    }

    @Override
    public double addMiles(double miles, String userEmail) {
        User userToUpdate = springUserRepository.findByEmail(userEmail);
        double distance = userToUpdate.getInitialMiles();
        userToUpdate.setInitialMiles( distance+ miles);
        springUserRepository.save(userToUpdate);
        return distance + miles;
    }
}
