package com.secondhand.secondhand.service;

import com.secondhand.secondhand.exception.GenreTypeExistException;
import com.secondhand.secondhand.exception.InvalidProductInformationException;
import com.secondhand.secondhand.exception.OrderNotExistException;
import com.secondhand.secondhand.model.Genre;
import com.secondhand.secondhand.model.Order;
import com.secondhand.secondhand.model.Product;
import com.secondhand.secondhand.repository.GenreRepository;
import com.secondhand.secondhand.repository.ProductRepository;
import com.secondhand.secondhand.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private GenreRepository genreRepository;

    private ProductTypeRepository productTypeRepository;

    private ProductRepository productRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository, ProductTypeRepository productTypeRepository, ProductRepository productRepository) {
        this.genreRepository = genreRepository;
        this.productTypeRepository = productTypeRepository;
        this.productRepository = productRepository;
    }

    public List<Product> getProductByGenre(String genreType) throws GenreTypeExistException {
        Optional<Genre> genreFromDBOptional = genreRepository.findById(genreType);
        if (genreFromDBOptional.isEmpty()) {
            throw new GenreTypeExistException("Invalid Product Type Entered. Clothes, Shoe, ElectricDevice, Furniture");
        }

        Genre genre = genreFromDBOptional.get();
        return productRepository.findByGenre(genre);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void createAndAddGenre(String genreType) throws GenreTypeExistException, InvalidProductInformationException {
        if (!productTypeRepository.isValidType(genreType)) {
            throw new InvalidProductInformationException("Invalid Product Type Entered. Clothes, Shoe, ElectricDevice, Furniture");
        }
        if (genreRepository.existsByGenreType(genreType)) {
            throw new GenreTypeExistException("Genre Type already Exist");
        }
        Genre genre = new Genre.Builder()
                .setGenreType(genreType)
                .build();
        genreRepository.save(genre);
    }

}
