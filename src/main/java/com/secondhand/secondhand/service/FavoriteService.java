package com.secondhand.secondhand.service;

import com.secondhand.secondhand.exception.ProductNotExistException;
import com.secondhand.secondhand.exception.UserNotExistException;
import com.secondhand.secondhand.model.Product;
import com.secondhand.secondhand.model.User;
import com.secondhand.secondhand.repository.FavoriteRepository;
import com.secondhand.secondhand.repository.ProductRepository;
import com.secondhand.secondhand.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Service
public class FavoriteService {

    private FavoriteRepository favoriteRepository;

    private UserRepository userRepository;

    private ProductRepository productRepository;

    @Autowired
    public FavoriteService(FavoriteRepository favoriteRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.favoriteRepository = favoriteRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @ResponseBody
    public Set<Product> getFavoriteProduct(String username) {
        if (!userRepository.existsByUsername(username)) {
            throw new UserNotExistException("User not exist.");
        }
        User user = userRepository.findByUsername(username);
        Set<Product> set = user.getFavoriteProducts();

        return set;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void setFavoriteProduct(String username, Long productId) throws UserNotExistException, ProductNotExistException {
        if (!userRepository.existsByUsername(username)) {
            throw new UserNotExistException("User not exist.");
        }
        User user = userRepository.findByUsername(username);

        if (!productRepository.existsById(productId)) {
            throw new ProductNotExistException("Product not exist.");
        }
        Product product = productRepository.findById(productId).get();

        //favoriteRepository.setFavoriteProduct(user, product);

        user.getFavoriteProducts().add(product);
        product.getFavoriteBy().add(user);

        userRepository.save(user);
        productRepository.save(product);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void unsetFavoriteProduct(String username, Long productId) throws UserNotExistException, ProductNotExistException {
        if (!userRepository.existsByUsername(username)) {
            throw new UserNotExistException("User not exist.");
        }
        User user = userRepository.findByUsername(username);

        if (!productRepository.existsById(productId)) {
            throw new ProductNotExistException("Product not exist.");
        }
        Product product = productRepository.findById(productId).get();

        // favoriteRepository.unsetFavoriteProduct(user, product);

        user.getFavoriteProducts().remove(product);
        product.getFavoriteBy().remove(user);

        userRepository.save(user);
        productRepository.save(product);
    }

}
