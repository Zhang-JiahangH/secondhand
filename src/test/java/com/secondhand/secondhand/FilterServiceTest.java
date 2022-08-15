package com.secondhand.secondhand;

import com.secondhand.secondhand.StubRepository.StubAddressRepository;
import com.secondhand.secondhand.StubRepository.StubProductRepository;
import com.secondhand.secondhand.exception.CityNotExistException;
import com.secondhand.secondhand.exception.InvalidPriceRangeException;
import com.secondhand.secondhand.exception.ZipcodeNotExistException;
import com.secondhand.secondhand.model.Address;
import com.secondhand.secondhand.model.Genre;
import com.secondhand.secondhand.model.Product;
import com.secondhand.secondhand.model.User;
import com.secondhand.secondhand.service.FilterService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

public class FilterServiceTest {
    Logger logger = LoggerFactory.getLogger(FavoriteRepositoryTest.class);

    @Test
    void findProductByFilterTest() {
        logger.info("Running findProductByFilter test for FilterService.");

        StubAddressRepository addressRepository = new StubAddressRepository();
        StubProductRepository productRepository = new StubProductRepository();

        // creation variable
        User user = TestFactory.getUser("trial_user");
        String cityName = "trail_cityname";
        int zipCode = 10010;
        String line1 = "929 West Jefferson Blvd";
        String line2 = "Cale and Irani";
        String line3 = "5029D";
        String state = "CA";
        Address address = TestFactory.getAddress(user, line1, line2, line3, cityName, state, zipCode);
        addressRepository.save(address);

        String productName = "trial_product";
        String description = "nothing";
        int price = 1;
        String genreType = "Clothes";
        Genre genre = TestFactory.getGenre(genreType);
        LocalDateTime createdAt = LocalDateTime.now();
        Product product = TestFactory.getProduct(user, productName, description, price, genre, createdAt);
        productRepository.save(product);

        FilterService filterService = new FilterService(addressRepository, productRepository);

        List<Product> retrivedProducts = filterService.findProductByFilter(10010, "trail_cityname", 0, 2);
        assertNotNull("retirved product list shouldn't be null", retrivedProducts);
        assertEquals(retrivedProducts.get(0).getProductName(), product.getProductName());

        logger.info("Running findProductByFilter test for FilterService. Verifying different conditions.");
        List<Product> retrivedProductsCondition1 = filterService.findProductByFilter(10010, "", 0, 1);
        assertNotNull("retirved product list shouldn't be null", retrivedProductsCondition1);
        List<Product> retrivedProductsCondition2 = filterService.findProductByFilter(null, "trail_cityname", 0, 1);
        assertNotNull("retirved product list shouldn't be null", retrivedProductsCondition2);
        List<Product> retrivedProductsCondition3 = filterService.findProductByFilter(10010, "trail_cityname", null, 1);
        assertNotNull("retirved product list shouldn't be null", retrivedProductsCondition3);
        List<Product> retrivedProductsCondition4 = filterService.findProductByFilter(10010, "trail_cityname", 0, null);
        assertNotNull("retirved product list shouldn't be null", retrivedProductsCondition4);
    }

    @Test
    void findProductByAddressTest() {
        logger.info("Running findProductByAddress test for FilterService.");

        StubAddressRepository addressRepository = new StubAddressRepository();
        StubProductRepository productRepository = new StubProductRepository();

        // creation variable
        User user = TestFactory.getUser("trial_user");
        String cityName = "trail_cityname";
        int zipCode = 10010;
        String line1 = "929 West Jefferson Blvd";
        String line2 = "Cale and Irani";
        String line3 = "5029D";
        String state = "CA";
        Address address = TestFactory.getAddress(user, line1, line2, line3, cityName, state, zipCode);
        addressRepository.save(address);

        String productName = "trial_product";
        String description = "nothing";
        int price = 1;
        String genreType = "Clothes";
        Genre genre = TestFactory.getGenre(genreType);
        LocalDateTime createdAt = LocalDateTime.now();
        Product product = TestFactory.getProduct(user, productName, description, price, genre, createdAt);
        productRepository.save(product);

        FilterService filterService = new FilterService(addressRepository, productRepository);

        List<Product> retrivedProducts = filterService.findProductByFilter(10010, "trail_cityname", null, null);
        assertNotNull("retirved product list shouldn't be null", retrivedProducts);
        assertEquals(retrivedProducts.get(0).getProductName(), product.getProductName());

        logger.info("Running findProductByAddress test for FilterService. Expect ZipcodeNotExistException");
        assertThrows(ZipcodeNotExistException.class, ()->{
            filterService.findProductByFilter(10000, "trail_cityname", null, null);
        });

        logger.info("Running findProductByAddress test for FilterService. Expect CityNotExistException");
        assertThrows(CityNotExistException.class, ()->{
            filterService.findProductByFilter(10010, "LA", null, null);
        });

        logger.info("Running findProductByAddress test for FilterService. Only has zipcode");
        List<Product> condition1 = filterService.findProductByFilter(10010, "", null, null);
        assertNotNull("retirved product list shouldn't be null", condition1);

        logger.info("Running findProductByAddress test for FilterService. Only has city name");
        List<Product> condition2 = filterService.findProductByFilter(null, "trail_cityname", null, null);
        assertNotNull("retirved product list shouldn't be null", condition2);
    }

    @Test
    void findProductByPriceTest() {
        logger.info("Running findProductByPrice test for FilterService.");

        StubAddressRepository addressRepository = new StubAddressRepository();
        StubProductRepository productRepository = new StubProductRepository();

        // creation variable
        User user = TestFactory.getUser("trial_user");
        String cityName = "trail_cityname";
        int zipCode = 10010;
        String line1 = "929 West Jefferson Blvd";
        String line2 = "Cale and Irani";
        String line3 = "5029D";
        String state = "CA";
        Address address = TestFactory.getAddress(user, line1, line2, line3, cityName, state, zipCode);
        addressRepository.save(address);

        String productName = "trial_product";
        String description = "nothing";
        int price = 1;
        String genreType = "Clothes";
        Genre genre = TestFactory.getGenre(genreType);
        LocalDateTime createdAt = LocalDateTime.now();
        Product product = TestFactory.getProduct(user, productName, description, price, genre, createdAt);
        productRepository.save(product);

        FilterService filterService = new FilterService(addressRepository, productRepository);
        List<Product> retrivedProducts = filterService.findProductByFilter(null, "", 0, 1);
        assertNotNull("retirved product list shouldn't be null", retrivedProducts);
        assertEquals(retrivedProducts.get(0).getProductName(), product.getProductName());

        logger.info("Running findProductByPrice test for FilterService. Expect InvalidPriceRangeException");
        InvalidPriceRangeException exception = assertThrows(InvalidPriceRangeException.class, ()->{
            filterService.findProductByFilter(null, "", 2, 1);
        });
        assertEquals("Invalid Price Range. minPrice must be smaller than maxPrice.", exception.getMessage());

        logger.info("Running findProductByPrice test for FilterService. No limit situation.");
        List<Product> allProducts = filterService.findProductByFilter(null, "", null, null);
        assertEquals(allProducts.size(), 1);

        logger.info("Running findProductByPrice test for FilterService. Only lower boundary");
        List<Product> condition1 = filterService.findProductByFilter(null, "", 0, null);
        assertNotNull("retirved product list shouldn't be null", condition1);

        logger.info("Running findProductByPrice test for FilterService. Only higher boundary");
        List<Product> condition2 = filterService.findProductByFilter(null, "", null, 2);
        assertNotNull("retirved product list shouldn't be null", condition2);
    }

    @Test
    void multipleProductsTest() {
        logger.info("Running multiple products test for FilterService.");

        StubAddressRepository addressRepository = new StubAddressRepository();
        StubProductRepository productRepository = new StubProductRepository();

        // creation variable
        User user = TestFactory.getUser("trial_user");
        String cityName = "trail_cityname";
        int zipCode = 10010;
        String line1 = "929 West Jefferson Blvd";
        String line2 = "Cale and Irani";
        String line3 = "5029D";
        String state = "CA";
        Address address = TestFactory.getAddress(user, line1, line2, line3, cityName, state, zipCode);
        addressRepository.save(address);

        String productName = "trial_product";
        String description = "nothing";
        int price = 2;
        String genreType = "Clothes";
        Genre genre = TestFactory.getGenre(genreType);
        LocalDateTime createdAt = LocalDateTime.now();
        Product product = TestFactory.getProduct(user, productName, description, price, genre, createdAt);
        Product product1 = TestFactory.getProduct(user, productName, description, 1, genre, createdAt);
        Product product2 = TestFactory.getProduct(user, productName, description, 5, genre, createdAt);
        productRepository.save(product);
        productRepository.save(product1);
        productRepository.save(product2);

        FilterService filterService = new FilterService(addressRepository, productRepository);
        List<Product> retrivedProducts = filterService.findProductByFilter(10010, "trail_cityname", 2, 3);

        assertEquals(1, retrivedProducts.size());
    }
}
