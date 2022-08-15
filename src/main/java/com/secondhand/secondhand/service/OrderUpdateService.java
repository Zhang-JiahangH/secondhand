package com.secondhand.secondhand.service;

import com.secondhand.secondhand.exception.OrderNotExistException;
import com.secondhand.secondhand.exception.OrderStatusNotExistException;
import com.secondhand.secondhand.exception.UserNotMatchException;
import com.secondhand.secondhand.model.Order;
import com.secondhand.secondhand.model.User;
import com.secondhand.secondhand.repository.OrderRepository;
import com.secondhand.secondhand.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Service to update order
 */
@Service
public class OrderUpdateService {
    private UserRepository userRepository;
    private OrderRepository orderRepository;

    @Autowired
    public OrderUpdateService(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void update(Order order, String username) throws OrderNotExistException, OrderStatusNotExistException, UserNotMatchException {
        if (!orderRepository.existsById(order.getId())) {
            throw new OrderNotExistException("Order Doesn't Exist");
        }

        String orderStatus = order.getOrderStatus();
        if (!orderStatus.equals("paid")
                && !orderStatus.equals("shipped")
                && !orderStatus.equals("delivered")
                && !orderStatus.equals("given")
                && !orderStatus.equals("completed")
                && !orderStatus.equals("deleted")) {
            throw new OrderStatusNotExistException("Order Status Doesn't Exist");
        }


        Optional<Order> orderFromDBOptional = orderRepository.findById(order.getId());
        if (orderFromDBOptional.isEmpty()) {
            throw new OrderNotExistException("Order does not exist");
        }

        Order orderFromDB = orderFromDBOptional.get();

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

        orderFromDB.setOrderStatus(order.getOrderStatus());
        orderFromDB.setUpdatedAt(LocalDateTime.now());
        orderRepository.save(orderFromDB);

    }
}
