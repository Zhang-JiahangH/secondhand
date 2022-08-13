package com.secondhand.secondhand.controller;

import com.secondhand.secondhand.model.Product;
import com.secondhand.secondhand.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FilterController {

    private FilterService filterService;

    @Autowired
    public FilterController(FilterService filterService) {
        this.filterService = filterService;
    }

    @GetMapping("/filters")
    @ResponseBody
    public List<Product> findProducts(@RequestParam(name = "zipcode", required = false) Integer zipcode,
                                      @RequestParam(name = "city", required = false) String city,
                                      @RequestParam(name = "min_price", required = false) Integer minPrice,
                                      @RequestParam(name = "max_price", required = false) Integer maxPrice) {

        return filterService.findProductByFilter(zipcode, city, minPrice, maxPrice);
    }


}
