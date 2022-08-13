package com.secondhand.secondhand.controller;

import com.secondhand.secondhand.model.Order;
import com.secondhand.secondhand.service.OrderSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * Controller for order search
 */
@RestController
public class OrderSearchController {
    OrderSearchService orderSearchService;

    @Autowired
    public OrderSearchController(OrderSearchService orderSearchService) {
        this.orderSearchService = orderSearchService;
    }

    @GetMapping("/order/getbybuyer")
    public List<Order> searchOrderByBuyer(Principal principal) {
        return orderSearchService.searchOrderByBuyer(principal.getName());
    }

    @GetMapping("/order/getbyseller")
    public List<Order> searchOrderBySeller(Principal principal) {
        return orderSearchService.searchOrderBySeller(principal.getName());
    }

    @GetMapping("/order/getbyorder/{id}")
    public Order searchOrderByOrderId(@PathVariable Long id, Principal principal) {
        return orderSearchService.searchOrderByOrderId(id, principal.getName());
    }
}
