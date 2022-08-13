package com.secondhand.secondhand.repository;

import com.secondhand.secondhand.model.Address;
import com.secondhand.secondhand.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByCityIgnoreCase(String city);
    List<Address> findByZipcode(int zipcode);

    List<Address> findByZipcodeAndCityIgnoreCase(int zipcode, String city);

    boolean existsByZipcode(int zipcode);
    boolean existsByCityIgnoreCase(String city);
    boolean existsByUser(User user);
}
