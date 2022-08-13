package com.secondhand.secondhand.repository;

import com.secondhand.secondhand.model.Order;
import com.secondhand.secondhand.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository to connect with orders table in database
 * Order is model class, Long is primary id type
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByBuyer(User user);
    List<Order> findBySeller(User user);
}

