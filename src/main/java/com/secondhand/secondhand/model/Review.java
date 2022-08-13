package com.secondhand.secondhand.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Model for reviews table in database
 */
@Entity
@Table(name = "reviews")
@JsonDeserialize(builder = Review.Builder.class)
public class Review implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("review_content")
    private String reviewContent;

    @ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "username")
    @JsonIgnore
    private User user;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;


    // Constructor
    public Review() {}

    public Review(Builder builder) {
        this.id = builder.id;
        this.reviewContent = builder.reviewContent;
        this.user = builder.user;
        this.createdAt = builder.createdAt;
    }

    /**
     * Getter and Setter
     * */
    public Long getId() {
        return id;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Builder
     * */
    public static class Builder {

        @JsonProperty("id")
        private Long id;

        @JsonProperty("review_content")
        private String reviewContent;

        @JsonProperty("username")
        private User user;

        @JsonProperty("created_at")
        private LocalDateTime createdAt;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setReviewContent(String reviewContent) {
            this.reviewContent = reviewContent;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Review build(){
            return new Review(this);
        }
    }
}
