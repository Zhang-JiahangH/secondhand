package com.secondhand.secondhand;

import com.secondhand.secondhand.StubModel.StubPrincipal;
import com.secondhand.secondhand.StubRepository.StubAddressRepository;
import com.secondhand.secondhand.StubRepository.StubUserRepository;
import com.secondhand.secondhand.controller.AddressController;
import com.secondhand.secondhand.model.Address;
import com.secondhand.secondhand.model.User;
import com.secondhand.secondhand.service.AddressService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressControllerTest {
    Logger logger = LoggerFactory.getLogger(FavoriteRepositoryTest.class);

    @Test
    void addAddressTest() {
        logger.info("Running addAddress Test for AddressController.");

        StubPrincipal stubPrincipal = new StubPrincipal("trial");
        String cityName = "trail_cityname";
        int zipCode = 10010;
        String line1 = "929 West Jefferson Blvd";
        String line2 = "Cale and Irani";
        String line3 = "5029D";
        String state = "CA";
        User user = TestFactory.getUser(stubPrincipal.getName());
        Address address = TestFactory.getAddress(user, line1, line2, line3, cityName, state, zipCode);

        StubUserRepository UserRepository = new StubUserRepository();
        UserRepository.save(user);
        StubAddressRepository AddressRepository = new StubAddressRepository();
        AddressService addressService = new AddressService(AddressRepository, UserRepository);
        AddressController addressController = new AddressController(addressService);

        addressController.addAddress(stubPrincipal, line1, line2, line3, cityName, state, zipCode);
        assertEquals(address.getZipcode(), addressController.searchAddress(zipCode).get(0).getZipcode());
    }
}
