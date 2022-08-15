package com.secondhand.secondhand;

import com.secondhand.secondhand.StubRepository.StubGenreRepository;
import com.secondhand.secondhand.StubRepository.StubProductRepository;
import com.secondhand.secondhand.StubRepository.StubUserRepository;
import com.secondhand.secondhand.exception.InvalidProductInformationException;
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

import java.time.LocalDateTime;
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

        LocalDateTime createdAt = LocalDateTime.now();

        Product product = TestFactory.getProduct(user, productName, description, price, genre, createdAt);

        ProductService productService = new ProductService(productRepository, userRepository,productTypeRepository, genreRepository);

        productService.createAndAddProduct(username, productName, description, price, genreType);

        Product retrivedProduct = productService.getProductByUsername(username).get(0);
        assertEquals(retrivedProduct.getProductName(), product.getProductName());
        assertEquals(retrivedProduct.getDescription(), product.getDescription());
        assertEquals(retrivedProduct.getPrice(), product.getPrice());
        assertEquals(retrivedProduct.getGenre(), product.getGenre());
        assertEquals(retrivedProduct.getFavoriteBy().size(), 0);
    }

    @Test
    void createAndAddProductTestThrowUserNotExistException() {
        logger.info("Running createAndAddProduct test for ProductService. Expect UserNotExistException");

        String username = "trail_user";
        String productName = "trial_product";
        String description = "nothing";
        int price = 1;
        String genreType = "Clothes";

        StubProductRepository productRepository = new StubProductRepository();
        StubUserRepository userRepository = new StubUserRepository();
        StubGenreRepository genreRepository = new StubGenreRepository();
        ProductTypeRepository productTypeRepository = new ProductTypeRepository();

        Genre genre = TestFactory.getGenre(genreType);
        genreRepository.save(genre);

        ProductService productService = new ProductService(productRepository, userRepository,productTypeRepository, genreRepository);

        assertThrows(UserNotExistException.class, ()->{
            productService.createAndAddProduct(username, productName, description, price, genreType);
        });
    }

    @Test
    void createAndAddProductTestThrowInvalidProductInformationException() {
        logger.info("Running createAndAddProduct test for ProductService. Expect throw InvalidProductInformationException");
        String username = "trail_user";
        String productName = "trial_product";
        String description = "nothing";
        int price = -1;
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

        InvalidProductInformationException exception = assertThrows(InvalidProductInformationException.class, ()->{
            productService.createAndAddProduct(username, productName, description, price, genreType);
        });

        assertEquals("Invalid product information. Price <= 0 or Type invalid.", exception.getMessage());
    }
}
