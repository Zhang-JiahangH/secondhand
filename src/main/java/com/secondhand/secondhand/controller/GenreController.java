package com.secondhand.secondhand.controller;

import com.secondhand.secondhand.model.Product;
import com.secondhand.secondhand.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenreController {

    private GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping("/genres")
    public void addGenre(@RequestParam(name = "genre_type") String genreType) {
        genreService.createAndAddGenre(genreType);
    }

    @GetMapping("/products/genres")
    public List<Product> getProductByGenre(@RequestParam(name = "genre_type") String genreType) {
        return genreService.getProductByGenre(genreType);
    }
}
