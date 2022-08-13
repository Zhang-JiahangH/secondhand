package com.secondhand.secondhand.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Model for users table in database
 */
@Entity
@Table(name = "users")
@JsonDeserialize(builder = User.Builder.class)
public class User implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    private String username;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private boolean enabled;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;

    /**
     * Review and Favorite Product
     * */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER) // 对应一个set
    @JoinTable(name = "favorite_collections",
            joinColumns = { @JoinColumn(name = "username")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    @JsonIgnore
    private Set<Product> favoriteProducts = new HashSet<>();


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Review> reviews = new ArrayList<>();


    /**
     * Constructor
     * */
    public User() {}

    private User(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.enabled = builder.enabled;
        this.email = builder.email;
        this.phone = builder.phone;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
    }


    /**
    *  Getter and Setter and Builder
    * */

    public Set<Product> getFavoriteProducts() {
        return favoriteProducts;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public static class Builder {
        @JsonProperty("username")
        private String username;

        @JsonProperty("password")
        private String password;

        @JsonProperty("enabled")
        private boolean enabled;

        @JsonProperty("email")
        private String email;

        @JsonProperty("phone")
        private String phone;

        @JsonProperty("first_name")
        private String firstName;

        @JsonProperty("last_name")
        private String lastName;

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setEnabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

}