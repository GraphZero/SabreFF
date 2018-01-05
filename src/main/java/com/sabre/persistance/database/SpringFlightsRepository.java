package com.sabre.persistance.database;

import com.sabre.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.ArrayList;

public interface SpringFlightsRepository extends JpaRepository<Flight, Long> {
    ArrayList<Flight> findByUserEmail(String email);
}
