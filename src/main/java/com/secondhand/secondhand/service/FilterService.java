package com.secondhand.secondhand.service;

import com.secondhand.secondhand.exception.CityNotExistException;
import com.secondhand.secondhand.exception.InvalidPriceRangeException;
import com.secondhand.secondhand.exception.ZipcodeNotExistException;
import com.secondhand.secondhand.model.Address;
import com.secondhand.secondhand.model.Product;
import com.secondhand.secondhand.model.User;
import com.secondhand.secondhand.repository.AddressRepository;
import com.secondhand.secondhand.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FilterService {

    private AddressRepository addressRepository;

    private ProductRepository productRepository;

    @Autowired
    public FilterService(AddressRepository addressRepository, ProductRepository productRepository) {
        this.addressRepository = addressRepository;
        this.productRepository = productRepository;
    }

    public List<Product> findProductByFilter(Integer zipcode, String city, Integer minPrice, Integer maxPrice) {
        if (zipcode == null && city.equals("")) {
            return findProductByPrice(minPrice, maxPrice);
        }
        if (minPrice == null && maxPrice == null) {
            return findProductByAddress(zipcode, city);
        }
        List<Product> productsByAddress = findProductByAddress(zipcode, city);
        return existInBothFilter(productsByAddress, minPrice, maxPrice);
    }

    private List<Product> findProductByAddress(Integer zipcode, String city) throws ZipcodeNotExistException, CityNotExistException{
        // No Limit
        if (zipcode == null && city.equals("")) {
            return productRepository.findAll();
        }

        // With Limit
        if (zipcode != null && !addressRepository.existsByZipcode(zipcode)) {
            throw new ZipcodeNotExistException("No result found. Invalid Zipcode.");
        }
        if (!city.equals("") && !addressRepository.existsByCityIgnoreCase(city)) {
            throw new CityNotExistException("No result found. Invalid City.");
        }
        List<Address> addresses = new ArrayList<>();
        if (zipcode != null && !city.equals("")) {
            addresses = addressRepository.findByZipcodeAndCityIgnoreCase(zipcode, city);
        } else if (zipcode != null) {
            addresses = addressRepository.findByZipcode(zipcode);
        } else {
            addresses = addressRepository.findByCityIgnoreCase(city);
        }

        List<Product> products = new ArrayList<>();
        for (Address address : addresses) {
            User user = address.getUser();
            List<Product> productsOfUser = productRepository.findByUserOrderByCreatedAtDesc(user);
            products.addAll(productsOfUser);
        }
        return products;
    }

    private List<Product> findProductByPrice(Integer minPrice, Integer maxPrice) throws InvalidPriceRangeException {
        // No Limit
        if (minPrice == null && maxPrice == null) {
            return productRepository.findAll();
        }

        // With Limit
        if (minPrice != null && maxPrice != null && minPrice > maxPrice) {
            throw new InvalidPriceRangeException("Invalid Price Range. minPrice must be smaller than maxPrice.");
        }

        if (minPrice != null && maxPrice != null) {
            List<Product> temp =  productRepository.findByPriceGreaterThanEqual(minPrice);
            List<Product> list = new ArrayList<>();
            for (Product product : temp) {
                if (product.getPrice() <= maxPrice) {
                    list.add(product);
                }
            }
            return list;
        }
        if (minPrice != null) {
            return productRepository.findByPriceGreaterThanEqual(minPrice);
        }
        return productRepository.findByPriceLessThanEqual(maxPrice);
    }

    private List<Product> existInBothFilter(List<Product> productList, Integer minPrice, Integer maxPrice) {
        List<Product> res = new ArrayList<>();
        for (Product product : productList) {
            if (minPrice != null && product.getPrice() < minPrice) {
                continue;
            }
            if (maxPrice != null && product.getPrice() > maxPrice) {
                continue;
            }
            res.add(product);
        }
        return res;
    }

}
