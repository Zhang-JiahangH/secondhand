package com.secondhand.secondhand.service;

import com.secondhand.secondhand.exception.UserNotExistException;
import com.secondhand.secondhand.model.Order;
import com.secondhand.secondhand.model.User;
import com.secondhand.secondhand.repository.OrderRepository;
import com.secondhand.secondhand.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Service to create order
 */
@Service
public class OrderCreateService {

    private UserRepository userRepository;
    private OrderRepository orderRepository;

    @Autowired
    public OrderCreateService(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void add(String buyerUsername, String sellerUsername, Long productId) throws UserNotExistException {
        if (!userRepository.existsById(buyerUsername)) {
            throw new UserNotExistException("Buyer not exist");
        }

        User buyer = userRepository.findByUsername(buyerUsername);

        if (!userRepository.existsById(sellerUsername)) {
            throw new UserNotExistException("Seller not exist");
        }

        User seller = userRepository.findByUsername(sellerUsername);

        //TODO check whether product exist

        Order order = new Order.Builder()
                .setProductId(productId)
                .setCreatedAt(LocalDateTime.now())
                .setUpdatedAt(LocalDateTime.now())
                .setOrderStatus("ordered")
                .build();
        order.setBuyer(buyer);
        order.setSeller(seller);
        orderRepository.save(order);
    }

}