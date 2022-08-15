package com.secondhand.secondhand.service;

import com.secondhand.secondhand.exception.OrderNotExistException;
import com.secondhand.secondhand.exception.UserNotExistException;
import com.secondhand.secondhand.exception.UserNotMatchException;
import com.secondhand.secondhand.model.Order;
import com.secondhand.secondhand.model.User;
import com.secondhand.secondhand.repository.OrderRepository;
import com.secondhand.secondhand.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service to search order
 */
@Service
public class OrderSearchService {
    private UserRepository userRepository;
    private OrderRepository orderRepository;

    @Autowired
    public OrderSearchService(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<Order> searchOrderByBuyer(String username) throws UserNotExistException {
        if (!userRepository.existsById(username)) {
            throw new UserNotExistException("User not exist");
        }

        User buyer = userRepository.findByUsername(username);

        return orderRepository.findByBuyer(buyer);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<Order> searchOrderBySeller(String username) throws UserNotExistException {
        if (!userRepository.existsById(username)) {
            throw new UserNotExistException("User not exist");
        }

        User seller = userRepository.findByUsername(username);

        return orderRepository.findBySeller(seller);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Order searchOrderByOrderId(Long id, String username) throws OrderNotExistException, UserNotExistException, UserNotMatchException {
        Optional<Order> orderFromDBOptional = orderRepository.findById(id);
        if (orderFromDBOptional.isEmpty()) {
            throw new OrderNotExistException("Order Doesn't Exist");
        }

        Order orderFromDB = orderFromDBOptional.get();

        if (!userRepository.existsById(username)) {
            throw new UserNotExistException("User Doesn't Exist");
        }

        User buyer = orderFromDB.getBuyer();
        User seller = orderFromDB.getSeller();
        String buyerUsername = null;
        String sellerUsername = null;
        try {
            buyerUsername = buyer.getUsername();
            sellerUsername = seller.getUsername();
        } catch (NullPointerException e) {
            throw new UserNotMatchException("Current user is not owner of this order");
        }

        if (!buyerUsername.equals(username)
                &&!sellerUsername.equals(username)) {
            throw new UserNotMatchException("Current user is not owner of this order");
        }

        return orderFromDB;
    }
}
