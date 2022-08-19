package com.secondhand.secondhand.service;

import com.secondhand.secondhand.exception.GenreTypeExistException;
import com.secondhand.secondhand.exception.InvalidProductInformationException;
import com.secondhand.secondhand.exception.UrlAlreadyExistException;
import com.secondhand.secondhand.exception.UserNotExistException;
import com.secondhand.secondhand.model.Genre;
import com.secondhand.secondhand.model.Product;
import com.secondhand.secondhand.model.ProductImage;
import com.secondhand.secondhand.model.User;
import com.secondhand.secondhand.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ProductService {

    private ProductRepository productRepository;

    private UserRepository userRepository;

    private ProductTypeRepository productTypeRepository;

    private GenreRepository genreRepository;

    private ProductImageRepository productImageRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, UserRepository userRepository, ProductTypeRepository productTypeRepository, GenreRepository genreRepository, ProductImageRepository productImageRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.productTypeRepository = productTypeRepository;
        this.genreRepository = genreRepository;
        this.productImageRepository = productImageRepository;
    }

    public List<Product> getProductByUsername(String username) {
        if (!userRepository.existsByUsername(username)) {
            throw new UserNotExistException("User not exist.");
        }
        User user = userRepository.findByUsername(username);
        return productRepository.findByUserOrderByCreatedAtDesc(user);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void createAndAddProduct(String username, String productName, String description, int price, List<String> urlList, String genreType) throws UserNotExistException, InvalidProductInformationException, UrlAlreadyExistException  {
        if (!userRepository.existsByUsername(username)) {
            throw new UserNotExistException("User not exist.");
        }
        User user = userRepository.findByUsername(username);

        if (price <= 0 || genreType == null) {
            throw new InvalidProductInformationException("Invalid product information. Price <= 0");
        }

        Optional<Genre> genreFromDBOptional = genreRepository.findById(genreType);
        if (genreFromDBOptional.isEmpty()) {
            throw new GenreTypeExistException("Invalid Product Type Entered. Clothes, Shoe, ElectricDevice, Furniture");
        }

        Genre genre = genreFromDBOptional.get();

        Product product = new Product.Builder()
                .setUser(user)
                .setProductName(productName)
                .setDescription(description)
                .setCreatedAt(LocalDateTime.now())
                .setPrice(price)
                .setGenre(genre)
                .build();

        if (urlList != null && urlList.size() != 0) {
            Set<ProductImage> imageList = new HashSet<>();
            for (String url: urlList) {
                ProductImage productImage = new ProductImage(url, product);
                if (productImageRepository.existsByUrl(url)){
                    throw new UrlAlreadyExistException("Url Already Exist.");
                }
                //productImageRepository.save(productImage);
                imageList.add(productImage);
            }

            product.setImagesList(imageList);
        }

        productRepository.save(product);
    }
}
