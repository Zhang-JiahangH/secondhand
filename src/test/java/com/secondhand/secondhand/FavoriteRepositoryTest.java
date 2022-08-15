package com.secondhand.secondhand;

import com.secondhand.secondhand.StubRepository.StubUserRepository;
import com.secondhand.secondhand.model.Product;
import com.secondhand.secondhand.model.User;
import com.secondhand.secondhand.repository.FavoriteRepositoryImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class FavoriteRepositoryTest {
    Logger logger = LoggerFactory.getLogger(FavoriteRepositoryTest.class);

    @Test
    public void setFavoriteProductTest() {
        logger.info("Running setFavoriteProductTest");
        User user = TestFactory.getUser("trial");
        Product product = TestFactory.getProduct("trialProduct");
        StubUserRepository UserRepository = new StubUserRepository();
        FavoriteRepositoryImpl favoriteRepository = new FavoriteRepositoryImpl(UserRepository);
        favoriteRepository.setFavoriteProduct(user, product);

        // repository is not NULL
        assertNotNull("The favorite list should not be empty", user.getFavoriteProducts());
        assertTrue("The favorite list should contain the target product",user.getFavoriteProducts().contains(product));
    }

    @Test
    public void unsetFavoriteProductTest() {
        logger.info("Running unsetFavoriteProductTest");
        User user = TestFactory.getUser("trial");
        Product product = TestFactory.getProduct("trialProduct");
        StubUserRepository UserRepository = new StubUserRepository();
        FavoriteRepositoryImpl favoriteRepository = new FavoriteRepositoryImpl(UserRepository);
        favoriteRepository.setFavoriteProduct(user, product);
        favoriteRepository.unsetFavoriteProduct(user, product);

        Set<Product> favoriteProducts = new HashSet<>();

        assertEquals(favoriteProducts, user.getFavoriteProducts());
    }
}
