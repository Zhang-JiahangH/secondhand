package com.secondhand.secondhand.repository;

import com.secondhand.secondhand.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for authority
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {
    Authority findAuthorityByUsername(String username);
}
