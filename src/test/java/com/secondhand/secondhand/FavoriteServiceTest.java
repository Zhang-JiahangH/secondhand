package com.secondhand.secondhand;

import com.secondhand.secondhand.StubRepository.StubProductRepository;
import com.secondhand.secondhand.StubRepository.StubUserRepository;
import com.secondhand.secondhand.exception.ProductNotExistException;
import com.secondhand.secondhand.exception.UserNotExistException;
import com.secondhand.secondhand.model.Genre;
import com.secondhand.secondhand.model.Product;
import com.secondhand.secondhand.model.User;
import com.secondhand.secondhand.repository.FavoriteRepository;
import com.secondhand.secondhand.repository.FavoriteRepositoryImpl;
import com.secondhand.secondhand.service.FavoriteService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FavoriteServiceTest {
    Logger logger = LoggerFactory.getLogger(FavoriteRepositoryTest.class);

    @Test
    void setGetUnsetFavoriteProductTest() {
        // initialize all repos
        StubUserRepository userRepository = new StubUserRepository();
        StubProductRepository productRepository = new StubProductRepository();
        FavoriteRepositoryImpl favoriteRepository = new FavoriteRepositoryImpl(userRepository);

        // create test user
        String username = "trail_user";
        User user = TestFactory.getUser(username);

        // create test product
        String productName = "trial_product";
        String description = "nothing";
        int price = 1;
        String genreType = "Clothes";
        Genre genre = TestFactory.getGenre(genreType);
        LocalDateTime createdAt = LocalDateTime.now();
        Product product = TestFactory.getProduct(user, productName, description, price, genre, createdAt);
        product.setId(0L);

        // create collections
        userRepository.save(user);
        productRepository.save(product);

        FavoriteService favoriteService = new FavoriteService(favoriteRepository, userRepository, productRepository);
        favoriteService.setFavoriteProduct(username, product.getId());

        assertEquals(1, userRepository.findByUsername(username).getFavoriteProducts().size());
        assertEquals(1, productRepository.findByProductName(productName).get(0).getFavoriteBy().size());

        // exception case
        assertThrows(UserNotExistException.class,()->{
            favoriteService.setFavoriteProduct("null", product.getId());
        });

        assertThrows(ProductNotExistException.class, ()->{
            favoriteService.setFavoriteProduct(username, 1L);
        });

        Set<Product> favoritedProducts = favoriteService.getFavoriteProduct(username);
        assertEquals(1, favoritedProducts.size());

        assertThrows(UserNotExistException.class,()->{
            favoriteService.getFavoriteProduct("null user");
        });

        favoriteService.unsetFavoriteProduct(username, product.getId());
        assertEquals(0, userRepository.findByUsername(username).getFavoriteProducts().size());

        assertThrows(UserNotExistException.class,()->{
            favoriteService.unsetFavoriteProduct("null", product.getId());
        });

        assertThrows(ProductNotExistException.class, ()->{
            favoriteService.unsetFavoriteProduct(username, 1L);
        });
    }
}
