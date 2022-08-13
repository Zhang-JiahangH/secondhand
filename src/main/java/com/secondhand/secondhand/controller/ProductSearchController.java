package com.secondhand.secondhand.controller;

import com.secondhand.secondhand.model.Product;
import com.secondhand.secondhand.service.ProductSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductSearchController {

    private ProductSearchService productSearchService;

    @Autowired
    public ProductSearchController(ProductSearchService productSearchService) {
        this.productSearchService = productSearchService;
    }

    @GetMapping("/products/search/{productName}")
    public List<Product> searchProductByName(@PathVariable String productName) {
        return productSearchService.searchProductByName(productName);
    }

}
