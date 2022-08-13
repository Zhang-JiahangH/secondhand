package com.secondhand.secondhand.service;

import com.secondhand.secondhand.exception.InvalidProductInformationException;
import com.secondhand.secondhand.exception.UserNotExistException;
import com.secondhand.secondhand.model.Genre;
import com.secondhand.secondhand.model.Product;
import com.secondhand.secondhand.model.User;
import com.secondhand.secondhand.repository.GenreRepository;
import com.secondhand.secondhand.repository.ProductRepository;
import com.secondhand.secondhand.repository.ProductTypeRepository;
import com.secondhand.secondhand.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    private UserRepository userRepository;

    private ProductTypeRepository productTypeRepository;

    private GenreRepository genreRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, UserRepository userRepository, ProductTypeRepository productTypeRepository, GenreRepository genreRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.productTypeRepository = productTypeRepository;
        this.genreRepository = genreRepository;
    }

    public List<Product> getProductByUsername(String username) {
        if (!userRepository.existsByUsername(username)) {
            throw new UserNotExistException("User not exist.");
        }
        User user = userRepository.findByUsername(username);
        return productRepository.findByUserOrderByCreatedAtDesc(user);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void createAndAddProduct(String username, String productName, String description, int price, String genreType) throws UserNotExistException, InvalidProductInformationException {
        if (!userRepository.existsByUsername(username)) {
            throw new UserNotExistException("User not exist.");
        }
        User user = userRepository.findByUsername(username);

        if (price <= 0 || genreType == null || !productTypeRepository.isValidType(genreType)) {
            throw new InvalidProductInformationException("Invalid product information. Price <= 0 or Type invalid.");
        }

        // PizzaStatusEnum readyStatus = PizzaStatusEnum.valueOf("READY");
        //Genre genre = new Genre.Builder().setGenreType(GenreType.valueOf(genreType)).build();

        // Genre genre = new Genre.Builder().setGenreType(genreType).build();
        // genreRepository.save(genre);

        Genre genre = genreRepository.findById(genreType).get();

        Product product = new Product.Builder()
                .setUser(user)
                .setProductName(productName)
                .setDescription(description)
                .setCreatedAt(LocalDateTime.now())
                .setPrice(price)
                .setGenre(genre)
                .build();

        productRepository.save(product);
    }
}
