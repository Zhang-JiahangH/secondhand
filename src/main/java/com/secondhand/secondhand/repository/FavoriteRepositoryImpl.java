package com.secondhand.secondhand.repository;

import com.secondhand.secondhand.model.Product;
import com.secondhand.secondhand.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FavoriteRepositoryImpl implements FavoriteRepository{

    private UserRepository userRepository;

    @Autowired
    public FavoriteRepositoryImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void setFavoriteProduct(User user, Product product) {
        user.getFavoriteProducts().add(product);
        userRepository.save(user);
    }

    @Override
    public void unsetFavoriteProduct(User user, Product product) {
        user.getFavoriteProducts().remove(product);
        userRepository.save(user);
    }
}
