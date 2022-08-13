package com.secondhand.secondhand.repository;

import com.secondhand.secondhand.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to connect with users table in database
 * User is model class, String is primary id type
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
    boolean existsByUsername(String username);
}