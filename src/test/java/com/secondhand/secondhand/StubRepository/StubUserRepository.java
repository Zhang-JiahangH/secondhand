package com.secondhand.secondhand.StubRepository;

import com.secondhand.secondhand.model.User;
import com.secondhand.secondhand.repository.UserRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class StubUserRepository implements UserRepository{
    List<User> stubUserRepository;

    public StubUserRepository() {
        this.stubUserRepository = new ArrayList<>();
    }

    @Override
    public User findByUsername(String username) {
        User user = null;
        for(var u:this.stubUserRepository) {
            if(u.getUsername() == username) {
                user = u;
                break;
            }
        }
        return user;
    }

    @Override
    public boolean existsByUsername(String username) {
        User user = null;
        for(var u:this.stubUserRepository) {
            if(u.getUsername() == username) {
                user = u;
                break;
            }
        }
        return user == null ? false : true;
    }

    @Override
    public List<User> findAll() {
        return this.stubUserRepository;
    }

    @Override
    public List<User> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<User> findAllById(Iterable<String> strings) {
        List<User> users = new ArrayList<>();
        for(var name:strings) {
            for(var user:this.stubUserRepository) {
                if(user.getUsername() == name) {
                    users.add(user);
                    break;
                }
            }
        }
        return users;
    }

    @Override
    public long count() {
        return this.stubUserRepository.size();
    }

    @Override
    public void deleteById(String s) {
        for(var user:this.stubUserRepository) {
            if(user.getUsername() == s) {
                this.stubUserRepository.remove(user);
                break;
            }
        }
    }

    @Override
    public void delete(User entity) {
        this.stubUserRepository.remove(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {
        List<User> deletedUser = this.findAllById((Iterable<String>) strings);
        for(var user:deletedUser) {
            this.stubUserRepository.remove(user);
        }
    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {
        for(var i:entities) {
            this.stubUserRepository.remove(i);
        }
    }

    @Override
    public void deleteAll() {
        this.stubUserRepository.clear();
    }

    @Override
    public <S extends User> S save(S entity) {
        this.stubUserRepository.add(entity);
        return null;
    }

    @Override
    public <S extends User> List<S> saveAll(Iterable<S> entities) {
        for(var i:entities) {
            this.stubUserRepository.add(i);
        }
        return null;
    }

    @Override
    public Optional<User> findById(String s) {
        for(var user:stubUserRepository) {
            if(user.getUsername() == s) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return !this.findById(s).isEmpty();
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends User> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends User> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<User> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<String> strings) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public User getOne(String s) {
        return null;
    }

    @Override
    public User getById(String s) {
        return null;
    }

    @Override
    public User getReferenceById(String s) {
        return null;
    }

    @Override
    public <S extends User> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends User> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends User> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends User, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
