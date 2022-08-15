package com.secondhand.secondhand;

import com.secondhand.secondhand.StubRepository.StubAddressRepository;
import com.secondhand.secondhand.StubRepository.StubUserRepository;
import com.secondhand.secondhand.exception.AddressOfThisUserAlreadyExistException;
import com.secondhand.secondhand.exception.CityNotExistException;
import com.secondhand.secondhand.exception.UserNotExistException;
import com.secondhand.secondhand.exception.ZipcodeNotExistException;
import com.secondhand.secondhand.model.Address;
import com.secondhand.secondhand.model.User;
import com.secondhand.secondhand.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddressServiceTest {
    Logger logger = LoggerFactory.getLogger(FavoriteRepositoryTest.class);

    @Test
    void findAddressByCityTest() {
        logger.info("Running findAddressByCity test for AddressService.");
        User user = TestFactory.getUser("trial_user");
        String cityName = "trail_cityname";
        Address address = TestFactory.getAddress(cityName, 10010, user);
        List<Address> createdCities = new ArrayList<>();
        createdCities.add(address);

        StubUserRepository UserRepository = new StubUserRepository();
        StubAddressRepository AddressRepository = new StubAddressRepository();
        AddressRepository.save(address);
        AddressService addressService = new AddressService(AddressRepository, UserRepository);

        List<Address> addresses = addressService.findAddressByCity(cityName);
        assertEquals(addresses, createdCities);
    }

    @Test
    void findAddressByCityThrowError() {
        logger.info("Running findAddressByCity test for AddressService. Expected throw errors.");
        String cityName = "trail_cityname";
        StubUserRepository UserRepository = new StubUserRepository();
        StubAddressRepository AddressRepository = new StubAddressRepository();
        AddressService addressService = new AddressService(AddressRepository, UserRepository);

        assertThrows(CityNotExistException.class, () -> {
            List<Address> addresses = addressService.findAddressByCity(cityName);
        });
    }

    @Test
    void findAddressByZipcodeTest() {
        logger.info("Running findAddressByZipcode test for AddressService.");
        User user = TestFactory.getUser("trial_user");
        String cityName = "trail_cityname";
        int zipCode = 10010;
        Address address = TestFactory.getAddress(cityName, zipCode, user);
        List<Address> createdCities = new ArrayList<>();
        createdCities.add(address);

        StubUserRepository UserRepository = new StubUserRepository();
        StubAddressRepository AddressRepository = new StubAddressRepository();
        AddressRepository.save(address);
        AddressService addressService = new AddressService(AddressRepository, UserRepository);

        List<Address> addresses = addressService.findAddressByZipcode(zipCode);
        assertEquals(addresses, createdCities);
    }

    @Test
    void findAddressByZipcodeThrowError() {
        logger.info("Running findAddressByZipcode test for AddressService. Expected throw errors.");
        int zipCode = 10010;
        StubUserRepository UserRepository = new StubUserRepository();
        StubAddressRepository AddressRepository = new StubAddressRepository();
        AddressService addressService = new AddressService(AddressRepository, UserRepository);

        assertThrows(ZipcodeNotExistException.class, () -> {
            List<Address> addresses = addressService.findAddressByZipcode(zipCode);
        });
    }

    @Test
    void createAndAddAddressTest() {
        logger.info("Running createAndAddAddress Test for AddressService.");
        User user = TestFactory.getUser("trial_user");
        String cityName = "trail_cityname";
        int zipCode = 10010;
        String line1 = "929 West Jefferson Blvd";
        String line2 = "Cale and Irani";
        String line3 = "5029D";
        String state = "CA";
        // target or comparator
        Address address = TestFactory.getAddress(user, line1, line2, line3, cityName, state, zipCode);
        List<Address> createdCities = new ArrayList<>();
        createdCities.add(address);

        StubUserRepository UserRepository = new StubUserRepository();
        UserRepository.save(user);
        StubAddressRepository AddressRepository = new StubAddressRepository();
        AddressService addressService = new AddressService(AddressRepository, UserRepository);
        addressService.createAndAddAddress("trial_user", line1, line2, line3, cityName, state, zipCode);
        List<Address> addresses = addressService.findAddressByCity(cityName);
        assertEquals(addresses.get(0).getCity(), createdCities.get(0).getCity());
        assertEquals(addresses.get(0).getAddressLine1(), createdCities.get(0).getAddressLine1());
        assertEquals(addresses.get(0).getAddressLine2(), createdCities.get(0).getAddressLine2());
        assertEquals(addresses.get(0).getAddressLine3(), createdCities.get(0).getAddressLine3());
        assertEquals(addresses.get(0).getState(), createdCities.get(0).getState());
    }

    @Test
    void createAndAddAddressThrowUserNotExistException() {
        logger.info("Running createAndAddAddress Test for AddressService. Expected UserNotExistException.");
        User user = TestFactory.getUser("trial_user");
        String cityName = "trail_cityname";
        int zipCode = 10010;
        String line1 = "929 West Jefferson Blvd";
        String line2 = "Cale and Irani";
        String line3 = "5029D";
        String state = "CA";
        // target or comparator
        Address address = TestFactory.getAddress(user, line1, line2, line3, cityName, state, zipCode);
        List<Address> createdCities = new ArrayList<>();
        createdCities.add(address);

        StubUserRepository UserRepository = new StubUserRepository();
        StubAddressRepository AddressRepository = new StubAddressRepository();
        AddressService addressService = new AddressService(AddressRepository, UserRepository);

        assertThrows(UserNotExistException.class, () -> {
            addressService.createAndAddAddress("trial_user", line1, line2, line3, cityName, state, zipCode);
        });
    }

    @Test
    void createAndAddAddressAddressOfThisUserAlreadyExistException() {
        logger.info("Running createAndAddAddress Test for AddressService. Expected AddressOfThisUserAlreadyExistException.");
        User user = TestFactory.getUser("trial_user");
        String cityName = "trail_cityname";
        int zipCode = 10010;
        String line1 = "929 West Jefferson Blvd";
        String line2 = "Cale and Irani";
        String line3 = "5029D";
        String state = "CA";
        // target or comparator
        Address address = TestFactory.getAddress(user, line1, line2, line3, cityName, state, zipCode);
        List<Address> createdCities = new ArrayList<>();
        createdCities.add(address);

        StubUserRepository UserRepository = new StubUserRepository();
        UserRepository.save(user);
        StubAddressRepository AddressRepository = new StubAddressRepository();
        AddressRepository.save(address);
        AddressService addressService = new AddressService(AddressRepository, UserRepository);

        assertThrows(AddressOfThisUserAlreadyExistException.class, () -> {
            addressService.createAndAddAddress("trial_user", line1, line2, line3, cityName, state, zipCode);
        });
    }
}
