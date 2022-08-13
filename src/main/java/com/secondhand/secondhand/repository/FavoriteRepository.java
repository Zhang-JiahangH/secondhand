package com.secondhand.secondhand.repository;

import com.secondhand.secondhand.model.Product;
import com.secondhand.secondhand.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository {
    public void setFavoriteProduct(User user, Product product);

    public void unsetFavoriteProduct(User user, Product product);
}
