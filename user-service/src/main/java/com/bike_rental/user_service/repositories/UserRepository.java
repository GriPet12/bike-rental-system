package com.bike_rental.user_service.repositories;

import com.bike_rental.user_service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    void deleteById(int id);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

}
