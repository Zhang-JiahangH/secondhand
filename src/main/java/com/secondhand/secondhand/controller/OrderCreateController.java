package com.secondhand.secondhand.controller;

import com.secondhand.secondhand.service.OrderCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Controller for order create
 */
@RestController
public class OrderCreateController {
    OrderCreateService orderCreateService;

    @Autowired
    public OrderCreateController(OrderCreateService orderCreateService) {
        this.orderCreateService = orderCreateService;
    }

    @PostMapping("/order/create")
    public void createOrder(
            @RequestParam("seller_username") String sellerUsername,
            @RequestParam("product_id") Long productId,
            Principal principal) {
        orderCreateService.add(principal.getName(), sellerUsername, productId);
    }
}
