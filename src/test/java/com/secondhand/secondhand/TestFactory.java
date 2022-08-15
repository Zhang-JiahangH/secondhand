package com.secondhand.secondhand;

import com.secondhand.secondhand.model.Address;
import com.secondhand.secondhand.model.Genre;
import com.secondhand.secondhand.model.Product;
import com.secondhand.secondhand.model.User;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class TestFactory {
    public static User getUser(String username) {
        User user = new User();
        user.setUsername(username);
        user.setEmail("user@gmail.com");
        user.setEnabled(true);
        user.setFirstName("User");
        user.setLastName("Name");
        user.setPhone("213-740-1111");
        return user;
    }

    public static Product getProduct(String productname) {
        LocalDateTime createdAt = LocalDateTime.now();
        Product product = new Product();
        product.setProductName(productname);
        product.setCreatedAt(createdAt);
        product.setPrice(0);
        product.setDescription("nothing");
        return product;
    }

    public static Product getProduct(User user, String productName, String description, int price, Genre genre, LocalDateTime createdAt) {
        Product product = new Product();
        product.setProductName(productName);
        product.setCreatedAt(createdAt);
        product.setUpdatedAt(createdAt);
        product.setUser(user);
        product.setDescription(description);
        product.setPrice(price);
        product.setGenre(genre);
        Set<User> users = new HashSet<>();
        users.add(user);
        product.setFavoriteBy(users);
        return product;
    }

    public static Address getAddress(String cityname, int zipcode, User user) {
        Address address = new Address();
        address.setCity(cityname);
        address.setZipcode(zipcode);
        address.setUser(user);
        return address;
    }

    public static Address getAddress(User user, String addressLine1, String addressLine2, String addressLine3, String city, String state, int zipcode) {
        Address address = new Address();
        address.setAddressLine1(addressLine1);
        address.setAddressLine2(addressLine2);
        address.setAddressLine3(addressLine3);
        address.setUser(user);
        address.setCity(city);
        address.setState(state);
        address.setZipcode(zipcode);
        return address;
    }

    public static Genre getGenre(String genreType) {
        Genre genre = new Genre();
        genre.setGenreType(genreType);
        return genre;
    }
}
