package com.secondhand.secondhand.repository;

import com.secondhand.secondhand.model.GenreType;
import org.springframework.stereotype.Repository;

@Repository
public class ProductTypeRepository {

    public boolean isValidType(String inputGenreType) {
        for (GenreType genreType : GenreType.values()) {
            if (inputGenreType.equals(genreType.toString())) {
                return true;
            }
        }
        return false;
    }
}
