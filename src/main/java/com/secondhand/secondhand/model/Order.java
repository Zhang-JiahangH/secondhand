package com.secondhand.secondhand.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Model for orders table in database
 */
@Entity
@Table(name = "orders")
@JsonDeserialize(builder = Order.Builder.class)
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("product_id")
    private Long productId;

    @ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "buyer_username")
    private User buyer;

    @ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "seller_username")
    private User seller;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("status")
    private String orderStatus;

    public Order() {}

    private Order(Builder builder) {
        this.id = builder.id;
        this.productId = builder.productId;

        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
        this.orderStatus = builder.orderStatus;
    }


    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public User getBuyer() {
        return buyer;
    }

    public User getSeller() {
        return seller;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public Order setId(Long id) {
        this.id = id;
        return this;
    }

    public Order setProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    public Order setBuyer(User buyer) {
        this.buyer = buyer;
        return this;
    }

    public Order setSeller(User seller) {
        this.seller = seller;
        return this;
    }

    public Order setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Order setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Order setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public static class Builder {

        @JsonProperty("id")
        private Long id;

        @JsonProperty("product_id")
        private Long productId;

        @JsonProperty("buyer_username")
        private User buyer;

        @JsonProperty("seller_username")
        private User seller;

        @JsonProperty("created_at")
        private LocalDateTime createdAt;

        @JsonProperty("updated_at")
        private LocalDateTime updatedAt;

        @JsonProperty("status")
        private String orderStatus;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setProductId(Long productId) {
            this.productId = productId;
            return this;
        }

        public Builder setBuyer(User buyer) {
            this.buyer = buyer;
            return this;
        }

        public Builder setSeller(User seller) {
            this.seller = seller;
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

        public Builder setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}