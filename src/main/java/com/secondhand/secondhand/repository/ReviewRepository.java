package com.secondhand.secondhand.repository;

import com.secondhand.secondhand.model.Review;
import com.secondhand.secondhand.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByUserOrderByCreatedAtDesc(User user);
}
