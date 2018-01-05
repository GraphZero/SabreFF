package com.sabre.persistance.database;

import com.sabre.domain.User;
import com.sabre.persistance.UserDatabaseRepository;
import com.sabre.persistance.database.SpringUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

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
    public User findByEmail(String email) {
        return springUserRepository.findByEmail(email);
    }

    @Override
    public double findMilesByEmail(String userEmail) {
        return springUserRepository.findMilesByEmail(userEmail);
    }

    @Override
    public double addMiles(double miles, String userEmail) {
        return 0;
    }
}
