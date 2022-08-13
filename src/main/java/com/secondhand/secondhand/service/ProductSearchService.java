package com.secondhand.secondhand.service;

import com.secondhand.secondhand.model.Product;
import com.secondhand.secondhand.repository.GenreRepository;
import com.secondhand.secondhand.repository.ProductRepository;
import com.secondhand.secondhand.repository.ProductTypeRepository;
import com.secondhand.secondhand.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductSearchService {

    private ProductRepository productRepository;

    private UserRepository userRepository;

    private ProductTypeRepository productTypeRepository;

    private GenreRepository genreRepository;

    @Autowired
    public ProductSearchService(ProductRepository productRepository, UserRepository userRepository, ProductTypeRepository productTypeRepository, GenreRepository genreRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.productTypeRepository = productTypeRepository;
        this.genreRepository = genreRepository;
    }

    public List<Product> searchProductByName(String productName) {
        if (!productRepository.existsByProductName(productName)) {
            return new ArrayList<>();
        }
        return productRepository.findByProductName(productName);
    }
}
