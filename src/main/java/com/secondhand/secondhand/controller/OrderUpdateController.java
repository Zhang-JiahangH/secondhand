package com.secondhand.secondhand.controller;

import com.secondhand.secondhand.model.Order;
import com.secondhand.secondhand.service.OrderUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Controller for order create
 */
@RestController
public class OrderUpdateController {
    OrderUpdateService orderUpdateService;

    @Autowired
    public OrderUpdateController(OrderUpdateService orderUpdateService) {
        this.orderUpdateService = orderUpdateService;
    }

    @PostMapping("/order/update")
    public void createOrder(
            @RequestBody Order order,
            Principal principal) {
        orderUpdateService.update(order, principal.getName());
    }
}
