package com.secondhand.secondhand.StubRepository;

import com.secondhand.secondhand.model.Genre;
import com.secondhand.secondhand.model.Product;
import com.secondhand.secondhand.model.User;
import com.secondhand.secondhand.repository.ProductRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class StubProductRepository implements ProductRepository {
    List<Product> ProductRepository;

    public StubProductRepository() { this.ProductRepository = new ArrayList<>(); }

    @Override
    public List<Product> findByUserOrderByCreatedAtDesc(User user) {
        List<Product> productList = new ArrayList<>();
        for(var product:this.ProductRepository) {
            if(product.getUser() == user) {
                productList.add(product);
            }
        }
        return productList;
    }

    @Override
    public List<Product> findByGenre(Genre genre) {
        List<Product> productList = new ArrayList<>();
        for(var product:this.ProductRepository) {
            if(product.getGenre() == genre) {
                productList.add(product);
            }
        }
        return productList;
    }

    @Override
    public List<Product> findByGenreOrderByCreatedAtDesc(Genre genre) {
        List<Product> productList = new ArrayList<>();
        for(var product:this.ProductRepository) {
            if(product.getGenre() == genre) {
                productList.add(product);
            }
        }
        return productList;
    }

    @Override
    public List<Product> findByProductName(String productName) {
        List<Product> productList = new ArrayList<>();
        for(var product:this.ProductRepository) {
            if(product.getProductName() == productName) {
                productList.add(product);
            }
        }
        return productList;
    }

    @Override
    public List<Product> findByPriceGreaterThanEqual(int price) {
        List<Product> productList = new ArrayList<>();
        for(var product:this.ProductRepository) {
            if(product.getPrice() >= price) {
                productList.add(product);
            }
        }
        return productList;
    }

    @Override
    public List<Product> findByPriceLessThanEqual(int price) {
        List<Product> productList = new ArrayList<>();
        for(var product:this.ProductRepository) {
            if(product.getPrice() <= price) {
                productList.add(product);
            }
        }
        return productList;
    }

    @Override
    public boolean existsByProductName(String productName) {
        return this.findByProductName(productName).stream().count() > 0;
    }

    @Override
    public List<Product> findAll() {
        return this.ProductRepository;
    }

    @Override
    public List<Product> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Product> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return this.ProductRepository.stream().count();
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Product entity) {
        this.ProductRepository.remove(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Product> entities) {
        for(var product:entities) {
            this.ProductRepository.remove(product);
        }
    }

    @Override
    public void deleteAll() {
        this.ProductRepository.clear();
    }

    @Override
    public <S extends Product> S save(S entity) {
        this.ProductRepository.add(entity);
        return null;
    }

    @Override
    public <S extends Product> List<S> saveAll(Iterable<S> entities) {
        for(var product:entities) {
            this.ProductRepository.add(product);
        }
        return null;
    }

    @Override
    public Optional<Product> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Product> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Product> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Product> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Product getOne(Long aLong) {
        return null;
    }

    @Override
    public Product getById(Long aLong) {
        return null;
    }

    @Override
    public Product getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Product> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Product> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Product> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Product> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Product> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Product, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
