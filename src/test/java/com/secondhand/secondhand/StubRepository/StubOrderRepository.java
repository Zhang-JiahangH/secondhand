package com.secondhand.secondhand.StubRepository;

import com.secondhand.secondhand.model.Order;
import com.secondhand.secondhand.model.User;
import com.secondhand.secondhand.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class StubOrderRepository implements OrderRepository {
    List<Order> OrderRepository;

    public StubOrderRepository() { this.OrderRepository = new ArrayList<>(); }

    @Override
    public List<Order> findByBuyer(User user) {
        List<Order> retrivedOrders = new ArrayList<>();
        for(var order:OrderRepository) {
            if(order.getBuyer() == user) {
                retrivedOrders.add(order);
            }
        }
        return retrivedOrders;
    }

    @Override
    public List<Order> findBySeller(User user) {
        List<Order> retrivedOrders = new ArrayList<>();
        for(var order:OrderRepository) {
            if(order.getSeller() == user) {
                retrivedOrders.add(order);
            }
        }
        return retrivedOrders;
    }

    @Override
    public List<Order> findAll() {
        return this.OrderRepository;
    }

    @Override
    public List<Order> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Order> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return this.OrderRepository.stream().count();
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Order entity) {
        this.OrderRepository.remove(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Order> entities) {
        for(var order:entities) {
            this.OrderRepository.remove(order);
        }
    }

    @Override
    public void deleteAll() {
        this.OrderRepository.clear();
    }

    @Override
    public <S extends Order> S save(S entity) {
        this.OrderRepository.add(entity);
        return null;
    }

    @Override
    public <S extends Order> List<S> saveAll(Iterable<S> entities) {
        for(var order:entities) {
            this.OrderRepository.add(order);
        }
        return null;
    }

    @Override
    public Optional<Order> findById(Long aLong) {
        for(var order:OrderRepository) {
            if(order.getId() == null) {
                continue;
            }
            if(order.getId() == aLong) {
                return Optional.of(order);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return !this.findById(aLong).isEmpty();
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Order> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Order> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Order> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Order getOne(Long aLong) {
        return null;
    }

    @Override
    public Order getById(Long aLong) {
        return null;
    }

    @Override
    public Order getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Order> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Order> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Order> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Order> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Order> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Order> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Order, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
