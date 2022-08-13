package com.secondhand.secondhand.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Address Model
 * */
@Entity
@Table(name = "addresses")
@JsonDeserialize(builder = Address.Builder.class)
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String city;
    private String state;

    private int zipcode;

    @OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "username")
    private User user;

    public Address() {};

    public Address(Builder builder) {
        this.id = builder.id;
        this.addressLine1 = builder.addressLine1;
        this.addressLine2 = builder.addressLine2;
        this.addressLine3 = builder.addressLine3;
        this.city = builder.city;
        this.state = builder.state;
        this.zipcode = builder.zipcode;
        this.user = builder.user;
    }

    /**
     * Getter and Setter
     * */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Builder
     * */
    public static class Builder {

        @JsonProperty("id")
        private Long id;

        @JsonProperty("address_line1")
        private String addressLine1;

        @JsonProperty("address_line2")
        private String addressLine2;

        @JsonProperty("address_line3")
        private String addressLine3;

        @JsonProperty("city")
        private String city;

        @JsonProperty("state")
        private String state;

        @JsonProperty("zipcode")
        private int zipcode;

        @JsonProperty("username")
        private User user;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setAddressLine1(String addressLine1) {
            this.addressLine1 = addressLine1;
            return this;
        }

        public Builder setAddressLine2(String addressLine2) {
            this.addressLine2 = addressLine2;
            return this;
        }

        public Builder setAddressLine3(String addressLine3) {
            this.addressLine3 = addressLine3;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setState(String state) {
            this.state = state;
            return this;
        }

        public Builder setZipcode(int zipcode) {
            this.zipcode = zipcode;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Address build(){
            return new Address(this);
        }
    }
}
