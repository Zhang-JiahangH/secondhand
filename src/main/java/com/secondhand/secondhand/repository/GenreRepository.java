package com.secondhand.secondhand.repository;

import com.secondhand.secondhand.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, String> {
    boolean existsByGenreType(String genreType);
}
