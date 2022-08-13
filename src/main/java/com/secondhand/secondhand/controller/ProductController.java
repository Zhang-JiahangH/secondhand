package com.secondhand.secondhand.controller;


import com.secondhand.secondhand.model.Product;
import com.secondhand.secondhand.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public void addProduct(Principal principal,
                           @RequestParam(name = "product_name") String productName,
                           @RequestParam(name = "description") String description,
                           @RequestParam(name = "price") int price,
                           @RequestParam(name = "genre_type") String genreType) {
        productService.createAndAddProduct(principal.getName(), productName, description, price, genreType);
    }

    @GetMapping("/products")
    public List<Product> getMyProduct(Principal principal) {
        return productService.getProductByUsername(principal.getName());
    }


}
