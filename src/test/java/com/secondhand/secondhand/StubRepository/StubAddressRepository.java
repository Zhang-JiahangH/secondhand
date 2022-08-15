package com.secondhand.secondhand.StubRepository;

import com.secondhand.secondhand.model.Address;
import com.secondhand.secondhand.model.User;
import com.secondhand.secondhand.repository.AddressRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class StubAddressRepository implements AddressRepository {
    List<Address> stubAddressRepository;

    public StubAddressRepository() { this.stubAddressRepository = new ArrayList<>(); }

    @Override
    public List<Address> findByCityIgnoreCase(String city) {
        List<Address> addresses = new ArrayList<>();
        for(var address:stubAddressRepository) {
            if(address.getCity() == city) {
                addresses.add(address);
            }
        }
        return addresses;
    }

    @Override
    public List<Address> findByZipcode(int zipcode) {
        List<Address> addresses = new ArrayList<>();
        for(var address:stubAddressRepository) {
            if(address.getZipcode() == zipcode) {
                addresses.add(address);
            }
        }
        return addresses;
    }

    @Override
    public List<Address> findByZipcodeAndCityIgnoreCase(int zipcode, String city) {
        List<Address> addresses = new ArrayList<>();
        for(var address:stubAddressRepository) {
            if(address.getZipcode() == zipcode && address.getCity() == city) {
                addresses.add(address);
            }
        }
        return addresses;
    }

    @Override
    public boolean existsByZipcode(int zipcode) {
        List<Address> addresses = this.findByZipcode(zipcode);
        return addresses.stream().count() != 0;
    }

    @Override
    public boolean existsByCityIgnoreCase(String city) {
        List<Address> addresses = this.findByCityIgnoreCase(city);
        return addresses.stream().count() != 0;
    }

    @Override
    public boolean existsByUser(User user) {
        List<Address> addresses = new ArrayList<>();
        for(var address:stubAddressRepository) {
            if(address.getUser() == user) {
                addresses.add(address);
            }
        }
        return addresses.stream().count() != 0;
    }

    @Override
    public List<Address> findAll() {
        return this.stubAddressRepository;
    }

    @Override
    public List<Address> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Address> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Address> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return this.stubAddressRepository.stream().count();
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Address entity) {
        this.stubAddressRepository.remove(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Address> entities) {
        for(var address : entities) {
            this.stubAddressRepository.remove(address);
        }
    }

    @Override
    public void deleteAll() {
        this.stubAddressRepository.clear();
    }

    @Override
    public <S extends Address> S save(S entity) {
        this.stubAddressRepository.add(entity);
        return null;
    }

    @Override
    public <S extends Address> List<S> saveAll(Iterable<S> entities) {
        for(var address:entities) {
            this.stubAddressRepository.add(address);
        }
        return null;
    }

    @Override
    public Optional<Address> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Address> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Address> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Address> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Address getOne(Long aLong) {
        return null;
    }

    @Override
    public Address getById(Long aLong) {
        return null;
    }

    @Override
    public Address getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Address> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Address> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Address> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Address> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Address> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Address> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Address, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
