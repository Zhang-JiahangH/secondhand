package com.secondhand.secondhand.repository;

import com.secondhand.secondhand.model.Genre;
import com.secondhand.secondhand.model.Product;
import com.secondhand.secondhand.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByUserOrderByCreatedAtDesc(User user);
    List<Product> findByGenre(Genre genre);
    List<Product> findByGenreOrderByCreatedAtDesc(Genre genre);
    List<Product> findByProductName(String productName);

    // public List<Student> findByRollNumberGreaterThanEqual(String rollnumber);
    List<Product> findByPriceGreaterThanEqual(int price);
    List<Product> findByPriceLessThanEqual(int price);

    boolean existsByProductName(String productName);
}
