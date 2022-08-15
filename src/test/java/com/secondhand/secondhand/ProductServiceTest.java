package com.secondhand.secondhand;

import com.secondhand.secondhand.StubRepository.StubGenreRepository;
import com.secondhand.secondhand.StubRepository.StubProductRepository;
import com.secondhand.secondhand.StubRepository.StubUserRepository;
import com.secondhand.secondhand.exception.UserNotExistException;
import com.secondhand.secondhand.model.Genre;
import com.secondhand.secondhand.model.Product;
import com.secondhand.secondhand.model.User;
import com.secondhand.secondhand.repository.ProductTypeRepository;
import com.secondhand.secondhand.service.ProductService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductServiceTest {
    Logger logger = LoggerFactory.getLogger(FavoriteRepositoryTest.class);

    @Test
    void getProductByUsernameTest() {
        logger.info("Running getProductByUsername test for ProductService.");
        StubProductRepository productRepository = new StubProductRepository();
        StubUserRepository userRepository = new StubUserRepository();
        StubGenreRepository genreRepository = new StubGenreRepository();
        ProductTypeRepository productTypeRepository = new ProductTypeRepository();

        String username = "trial";
        User user = TestFactory.getUser(username);
        userRepository.save(user);
        Product product = TestFactory.getProduct("trial_product");
        product.setUser(user);
        productRepository.save(product);

        ProductService productService = new ProductService(productRepository, userRepository,productTypeRepository, genreRepository);

        List<Product> productList = productService.getProductByUsername(username);

        assertEquals(productList.get(0), product);
    }

    @Test
    void getProductByUsernameTestThrowUserNotExistException() {
        logger.info("Running getProductByUsername test for ProductService. Throw UserNotExistException Error");
        StubProductRepository productRepository = new StubProductRepository();
        StubUserRepository userRepository = new StubUserRepository();
        StubGenreRepository genreRepository = new StubGenreRepository();
        ProductTypeRepository productTypeRepository = new ProductTypeRepository();

        String username = "trial";
        User user = TestFactory.getUser(username);
        Product product = TestFactory.getProduct("trial_product");
        product.setUser(user);
        productRepository.save(product);

        ProductService productService = new ProductService(productRepository, userRepository,productTypeRepository, genreRepository);

        assertThrows(UserNotExistException.class,() -> {
            List<Product> productList = productService.getProductByUsername(username);
        });
    }

    @Test
    void createAndAddProductTest() {
        logger.info("Running createAndAddProduct test for ProductService.");
        String username = "trail_user";
        String productName = "trial_product";
        String description = "nothing";
        int price = 1;
        String genreType = "Clothes";

        StubProductRepository productRepository = new StubProductRepository();
        StubUserRepository userRepository = new StubUserRepository();
        StubGenreRepository genreRepository = new StubGenreRepository();
        ProductTypeRepository productTypeRepository = new ProductTypeRepository();

        User user = TestFactory.getUser(username);
        userRepository.save(user);

        Genre genre = TestFactory.getGenre(genreType);
        genreRepository.save(genre);

        ProductService productService = new ProductService(productRepository, userRepository,productTypeRepository, genreRepository);

        productService.createAndAddProduct(username, productName, description, price, genreType);

        assertEquals(productService.getProductByUsername(username).get(0).getProductName(), productName);
    }
}
