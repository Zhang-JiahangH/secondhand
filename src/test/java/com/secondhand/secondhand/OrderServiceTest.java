package com.secondhand.secondhand;

import com.secondhand.secondhand.StubRepository.StubOrderRepository;
import com.secondhand.secondhand.StubRepository.StubUserRepository;
import com.secondhand.secondhand.exception.OrderNotExistException;
import com.secondhand.secondhand.exception.OrderStatusNotExistException;
import com.secondhand.secondhand.exception.UserNotExistException;
import com.secondhand.secondhand.exception.UserNotMatchException;
import com.secondhand.secondhand.model.Genre;
import com.secondhand.secondhand.model.Order;
import com.secondhand.secondhand.model.Product;
import com.secondhand.secondhand.model.User;
import com.secondhand.secondhand.service.OrderCreateService;
import com.secondhand.secondhand.service.OrderSearchService;
import com.secondhand.secondhand.service.OrderUpdateService;
import org.aspectj.weaver.ast.Or;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

public class OrderServiceTest {
    Logger logger = LoggerFactory.getLogger(FavoriteRepositoryTest.class);

    @Test
    void orderTest() {
        logger.info("Running add test for OrderCreateService");
        StubUserRepository userRepository = new StubUserRepository();
        StubOrderRepository orderRepository = new StubOrderRepository();

        String username1 = "trail_buyer";
        User user1 = TestFactory.getUser(username1);
        String username2 = "trial_seller";
        User user2 = TestFactory.getUser(username2);
        userRepository.save(user1);
        userRepository.save(user2);

        String productName = "trial_product";
        String description = "nothing";
        int price = 1;
        String genreType = "Clothes";
        Genre genre = TestFactory.getGenre(genreType);
        LocalDateTime createdAt = LocalDateTime.now();
        Product product = TestFactory.getProduct(user2, productName, description, price, genre, createdAt);
        product.setId(0L);

        OrderCreateService orderCreateService = new OrderCreateService(userRepository, orderRepository);

        assertEquals(0, orderRepository.count());
        orderCreateService.add(username1, username2, product.getId());
        assertEquals(1, orderRepository.count());

        logger.info("Running add test for OrderCreateService. Missing buyer. Expect UserNotExistException.");
        assertThrows(UserNotExistException.class, ()->{
            orderCreateService.add("null", username2, product.getId());
        });

        logger.info("Running add test for OrderCreateService. Missing seller. Expect UserNotExistException.");
        assertThrows(UserNotExistException.class, ()->{
            orderCreateService.add(username1, "null", product.getId());
        });

        OrderSearchService orderSearchService = new OrderSearchService(userRepository, orderRepository);
        logger.info("Running searchOrderByBuyer test for OrderSearchService.");
        List<Order> ordersByBuyer= orderSearchService.searchOrderByBuyer(username1);
        assertNotNull("retrived list should not be null", ordersByBuyer);
        assertEquals(product.getId(), ordersByBuyer.get(0).getProductId());
        assertEquals(user1, ordersByBuyer.get(0).getBuyer());
        assertEquals(user2, ordersByBuyer.get(0).getSeller());
        assertEquals("ordered", ordersByBuyer.get(0).getOrderStatus());
        assertNotNull("has created time", ordersByBuyer.get(0).getCreatedAt());
        assertNotNull("has updated time", ordersByBuyer.get(0).getUpdatedAt());

        logger.info("Running searchOrderByBuyer test for OrderSearchService. Non-exist Buyer. Expect UserNotExistException");
        assertThrows(UserNotExistException.class, ()->{
            orderSearchService.searchOrderByBuyer("null");
        });

        logger.info("Running searchOrderBySeller test for OrderSearchService.");
        List<Order> ordersBySeller= orderSearchService.searchOrderBySeller(username2);
        assertNotNull("retrived list should not be null", ordersBySeller);
        assertEquals(product.getId(), ordersBySeller.get(0).getProductId());
        assertEquals(user1, ordersBySeller.get(0).getBuyer());
        assertEquals(user2, ordersBySeller.get(0).getSeller());
        assertEquals("ordered", ordersBySeller.get(0).getOrderStatus());
        assertNotNull("has created time", ordersBySeller.get(0).getCreatedAt());
        assertNotNull("has updated time", ordersBySeller.get(0).getUpdatedAt());

        logger.info("Running searchOrderBySeller test for OrderSearchService. Non-exist Seller. Expect UserNotExistException");
        assertThrows(UserNotExistException.class, ()->{
            orderSearchService.searchOrderBySeller("null");
        });

        Long id = 0L;
        Order order = TestFactory.getOrder(product.getId(), user1, user2, id);
        orderRepository.save(order);

        logger.info("Running searchOrderByOrderId test for OrderSearchService.");
        Order retrivedOrder = orderSearchService.searchOrderByOrderId(id, username1);
        assertNotNull("order should not be a null", retrivedOrder);

        logger.info("Running searchOrderByOrderId test for OrderSearchService. Invalid order. Expect OrderNotExistException");
        OrderNotExistException expection = assertThrows(OrderNotExistException.class, ()->{
            orderSearchService.searchOrderByOrderId(1L, username1);
        });
        assertEquals("Order Doesn't Exist", expection.getMessage());

        logger.info("Running searchOrderByOrderId test for OrderSearchService. Invalid user. Expect UserNotExistException");
        assertThrows(UserNotExistException.class, ()->{
            orderSearchService.searchOrderByOrderId(id, "null");
        });

        Order newOrder = new Order();
        newOrder.setId(1L);
        orderRepository.save(newOrder);

        logger.info("Running searchOrderByOrderId test for OrderSearchService. Invalid Order. Expect UserNotMatchException");
        UserNotMatchException userException = assertThrows(UserNotMatchException.class, ()->{
            orderSearchService.searchOrderByOrderId(1L, username1);
        });
        assertEquals("Current user is not owner of this order", userException.getMessage());

        logger.info("Running searchOrderByOrderId test for OrderSearchService. Invalid User. Expect UserNotMatchException");
        User nonExistUser = TestFactory.getUser("wow");
        userRepository.save(nonExistUser);
        assertThrows(UserNotMatchException.class, ()->{
            orderSearchService.searchOrderByOrderId(id, "wow");
        });

        logger.info("Running update for OrderUpdateService.");
        OrderUpdateService orderUpdateService = new OrderUpdateService(userRepository, orderRepository);
        order.setOrderStatus("deleted");
        orderUpdateService.update(order, username1);
        assertEquals("deleted", orderSearchService.searchOrderByOrderId(id, username1).getOrderStatus());

        logger.info("Running update for OrderUpdateService. Invalid Order. Expect OrderStatusNotExistException");
        newOrder.setOrderStatus("ordered");
        OrderStatusNotExistException orderStatusNotExistException = assertThrows(OrderStatusNotExistException.class, ()->{
            orderUpdateService.update(newOrder, username1);
        });
        assertEquals("Order Status Doesn't Exist", orderStatusNotExistException.getMessage());

        logger.info("Running update for OrderUpdateService. Invalid Order. Expect OrderNotExistException");
        Order invalidOrder = TestFactory.getOrder(2L, user1, user2, 9L);
        assertThrows(OrderNotExistException.class, ()->{
            orderUpdateService.update(invalidOrder, username1);
        });

        newOrder.setOrderStatus("deleted");
        assertThrows(UserNotMatchException.class, ()->{
            orderUpdateService.update(newOrder, username1);
        });

        order.setOrderStatus("paid");
        assertThrows(UserNotMatchException.class, ()->{
            orderUpdateService.update(order, "null");
        });
    }
}
