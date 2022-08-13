package com.secondhand.secondhand.service;

import com.secondhand.secondhand.exception.AddressOfThisUserAlreadyExistException;
import com.secondhand.secondhand.exception.CityNotExistException;
import com.secondhand.secondhand.exception.UserNotExistException;
import com.secondhand.secondhand.exception.ZipcodeNotExistException;
import com.secondhand.secondhand.model.Address;
import com.secondhand.secondhand.model.User;
import com.secondhand.secondhand.repository.AddressRepository;
import com.secondhand.secondhand.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressService {

    private AddressRepository addressRepository;

    private UserRepository userRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    public List<Address> findAddressByCity(String city) throws CityNotExistException {
        if (!addressRepository.existsByCityIgnoreCase(city)) {
            throw new CityNotExistException("City not exist.");
        }
        return addressRepository.findByCityIgnoreCase(city);
    }

    public List<Address> findAddressByZipcode(int zipcode) throws ZipcodeNotExistException {
        if (!addressRepository.existsByZipcode(zipcode)) {
            throw new ZipcodeNotExistException("Zipcode not exist.");
        }
        return addressRepository.findByZipcode(zipcode);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void createAndAddAddress(String username, String addressLine1, String addressLine2, String addressLine3, String city, String state, int zipcode) throws UserNotExistException, AddressOfThisUserAlreadyExistException {
        if (!userRepository.existsByUsername(username)) {
            throw new UserNotExistException("User Username subject not exist.");
        }
        User user = userRepository.findByUsername(username);

        if (addressRepository.existsByUser(user)) {
            throw new AddressOfThisUserAlreadyExistException("Do not upload address again");
        }

        // Create Address object, then put into repository
        Address address = new Address.Builder()
                .setAddressLine1(addressLine1)
                .setAddressLine2(addressLine2)
                .setAddressLine3(addressLine3)
                .setCity(city)
                .setState(state)
                .setZipcode(zipcode)
                .setUser(user)
                .build();

        addressRepository.save(address);
        userRepository.save(user);
    }
}
