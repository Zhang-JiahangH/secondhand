package com.secondhand.secondhand.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "genres")
@JsonDeserialize(builder = Genre.Builder.class)
public class Genre implements Serializable {
    private static final long serialVersionUID = 1L;

    //@JsonProperty("genre_type")
    @Id
    private String genreType;
    //private GenreType genreType;

    public Genre() {};

    public Genre(Builder builder) {
        this.genreType = builder.genreType;
    }

    /**
     * Getter and Setter
     * */
//    public GenreType getGenreType() {
//        return this.genreType;
//    }
//
//    public void setGenreType(GenreType genreType) {
//        this.genreType = genreType;
//    }
    public String getGenreType() {
        return this.genreType;
    }

    public void setGenreType(String genreType) {
        this.genreType = genreType;
    }

    /**
     * Builder
     * */
    public static class Builder {
        @JsonProperty("genre_type")
        private String genreType;
        //private GenreType genreType;

        public Builder setGenreType(String genreType) {
            this.genreType = genreType;
            return this;
        }

//        public Builder setGenreType(GenreType genreType) {
//            this.genreType = genreType;
//            return this;
//        }

        public Genre build() {
            return new Genre(this);
        }
    }

}
