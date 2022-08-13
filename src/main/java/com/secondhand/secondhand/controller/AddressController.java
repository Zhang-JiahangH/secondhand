package com.secondhand.secondhand.controller;

import com.secondhand.secondhand.model.Address;
import com.secondhand.secondhand.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class AddressController {

    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/addresses")
    public void addAddress(Principal principal,
                           @RequestParam(name = "address_line1") String addressLine1,
                           @RequestParam(name = "address_line2") String addressLine2,
                           @RequestParam(name = "address_line2") String addressLine3,
                           @RequestParam(name = "city") String city,
                           @RequestParam(name = "state") String state,
                           @RequestParam(name = "zipcode") int zipcode) {
        addressService.createAndAddAddress(principal.getName(), addressLine1, addressLine2, addressLine3, city, state, zipcode);
    }

    @GetMapping("/search/addresses")
    public List<Address> searchAddress(@RequestParam(name = "zipcode") int zipcode) {
        return addressService.findAddressByZipcode(zipcode);
    }

}
