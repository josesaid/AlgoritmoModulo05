package com.example.security.repository;

import com.example.security.entities.UserDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author josesaidolanogarcia
 */

@Repository
public interface UserRepository extends CrudRepository<UserDetails, Integer> {
    Optional<UserDetails> findByUsername(String username);
}
