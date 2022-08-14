package com.secondhand.secondhand;

import com.secondhand.secondhand.model.Product;
import com.secondhand.secondhand.model.User;

import java.time.LocalDateTime;

public class TestFactory {
    public static User getUser(String username) {
        User user = new User();
        user.setUsername(username);
        user.setEmail("user@gmail.com");
        user.setEnabled(true);
        user.setFirstName("User");
        user.setLastName("Name");
        user.setPhone("213-740-1111");
        return user;
    }

    public static Product getProduct(String productname) {
        LocalDateTime createdAt = LocalDateTime.now();
        Product product = new Product();
        product.setProductName(productname);
        product.setCreatedAt(createdAt);
        product.setPrice(0);
        product.setDescription("nothing");
        return product;
    }
}
