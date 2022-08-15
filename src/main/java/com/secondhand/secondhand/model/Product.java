package com.secondhand.secondhand.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@JsonDeserialize(builder = Product.Builder.class)
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("product_id")
    private Long id;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("description")
    private String description;

    @JsonProperty("price")
    private int price;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
//    private List<ProductImage> imagesList = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "username")
    @JsonIgnore
    @JsonProperty("username")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "genre_type")
    @JsonProperty("genre_type")
    private Genre genre;

    @ManyToMany(mappedBy = "favoriteProducts")
    @JsonIgnore
    private Set<User> favoriteBy = new HashSet<>();

    /**
     * Constructor
     * */
    public Product(){};

    private Product(Builder builder) {
        this.id = builder.id;
        this.productName = builder.productName;
        this.description = builder.description;
        this.price = builder.price;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
        //this.imagesList = builder.imagesList;
        this.user = builder.user;
        this.genre = builder.genre;
    }

    /**
     * Setter and Getter
     * */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

//    public List<ProductImage> getImagesList() {
//        return imagesList;
//    }
//
//    public void setImagesList(List<ProductImage> imagesList) {
//        this.imagesList = imagesList;
//    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Set<User> getFavoriteBy() {
        return favoriteBy;
    }

    public void setFavoriteBy(Set<User> favoriteBy) {
        this.favoriteBy = favoriteBy;
    }

    /**
     * Builder
     * */
    public static class Builder {

        @JsonProperty("product_id")
        private Long id;

        @JsonProperty("product_name")
        private String productName;

        @JsonProperty("description")
        private String description;

        @JsonProperty("price")
        private int price;

        @JsonProperty("created_at")
        private LocalDateTime createdAt;

        @JsonProperty("updated_at")
        private LocalDateTime updatedAt;

//        @JsonProperty("images_list")
//        private List<ProductImage> imagesList;

        @JsonProperty("username")
        private User user;

        @JsonProperty("genre_type")
        private Genre genre;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setProductName(String productName) {
            this.productName = productName;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setPrice(int price) {
            this.price = price;
            return this;
        }

        public Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

//        public Builder setImagesList(List<ProductImage> imagesList) {
//            this.imagesList = imagesList;
//            return this;
//        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setGenre(Genre genre) {
            this.genre = genre;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }

}
