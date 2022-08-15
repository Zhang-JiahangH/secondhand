package com.secondhand.secondhand;

import com.secondhand.secondhand.StubRepository.StubOrderRepository;
import com.secondhand.secondhand.StubRepository.StubUserRepository;
import com.secondhand.secondhand.exception.UserNotExistException;
import com.secondhand.secondhand.model.Genre;
import com.secondhand.secondhand.model.Order;
import com.secondhand.secondhand.model.Product;
import com.secondhand.secondhand.model.User;
import com.secondhand.secondhand.service.OrderCreateService;
import com.secondhand.secondhand.service.OrderSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.jupiter.api.Test;

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
    }
}
