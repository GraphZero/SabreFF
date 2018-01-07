package com.sabre.persistance;

import com.sabre.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringUserRepository extends JpaRepository<User, Long>{
    User save(User o);
    User findByEmail(String email);
    boolean existsByEmail(String email);
    double findMilesByEmail(String email);
}
