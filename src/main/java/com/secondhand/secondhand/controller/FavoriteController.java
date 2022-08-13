package com.secondhand.secondhand.controller;

import com.secondhand.secondhand.model.Product;
import com.secondhand.secondhand.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@RestController
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @GetMapping("/favorite")
    public Set<Product> getFavoriteProduct(Principal principal){
        return favoriteService.getFavoriteProduct(principal.getName());
    }

    @PostMapping("/favorite")
    public void setFavoriteProduct(Principal principal, @RequestBody Product product){
        favoriteService.setFavoriteProduct(principal.getName(), product.getId());
    }

    @DeleteMapping("/favorite")
    public void unsetFavoriteProduct(Principal principal, @RequestBody Product product){
        favoriteService.unsetFavoriteProduct(principal.getName(), product.getId());
    }
}
