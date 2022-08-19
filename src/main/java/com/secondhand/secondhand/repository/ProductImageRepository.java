package com.secondhand.secondhand.repository;

import com.secondhand.secondhand.model.Product;
import com.secondhand.secondhand.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, String> {
        List<ProductImage> findByProduct(Product product);

        boolean existsByUrl(String url);
}

