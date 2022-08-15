package com.secondhand.secondhand.StubRepository;

import com.secondhand.secondhand.model.Genre;
import com.secondhand.secondhand.repository.GenreRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class StubGenreRepository implements GenreRepository {
    List<Genre> GenreRepository;

    public StubGenreRepository() { this.GenreRepository = new ArrayList<>(); }

    @Override
    public boolean existsByGenreType(String genreType) {
        for(var genre:GenreRepository) {
            if(genre.getGenreType() == genreType) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Genre> findAll() {
        return this.GenreRepository;
    }

    @Override
    public List<Genre> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Genre> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Genre> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return this.GenreRepository.stream().count();
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Genre entity) {
        this.GenreRepository.remove(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Genre> entities) {
        for(var genre:entities) {
            this.GenreRepository.remove(genre);
        }
    }

    @Override
    public void deleteAll() {
        this.GenreRepository.clear();
    }

    @Override
    public <S extends Genre> S save(S entity) {
        this.GenreRepository.add(entity);
        return null;
    }

    @Override
    public <S extends Genre> List<S> saveAll(Iterable<S> entities) {
        for(var genre:entities) {
            this.GenreRepository.add(genre);
        }
        return null;
    }

    @Override
    public Optional<Genre> findById(String s) {
        for(var genre:GenreRepository) {
            if(genre.getGenreType() == s) {
                return Optional.of(genre);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Genre> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Genre> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Genre> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<String> strings) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Genre getOne(String s) {
        return null;
    }

    @Override
    public Genre getById(String s) {
        return null;
    }

    @Override
    public Genre getReferenceById(String s) {
        return null;
    }

    @Override
    public <S extends Genre> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Genre> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Genre> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Genre> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Genre> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Genre> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Genre, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
