package com.secondhand.secondhand.service;

import com.secondhand.secondhand.exception.InvalidReviewContentException;
import com.secondhand.secondhand.exception.UserNotExistException;
import com.secondhand.secondhand.model.Review;
import com.secondhand.secondhand.model.User;
import com.secondhand.secondhand.repository.ReviewRepository;
import com.secondhand.secondhand.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;

    private UserRepository userRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    public List<Review> findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return reviewRepository.findByUserOrderByCreatedAtDesc(user);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void createAndAddReview(String username, String reviewContent) throws UserNotExistException, InvalidReviewContentException {
        if (!userRepository.existsByUsername(username)) {
            throw new UserNotExistException("User Username subject not exist.");
        }

        User user = userRepository.findByUsername(username);

        if (reviewContent == null || reviewContent.length() == 0) {
            throw new InvalidReviewContentException("Invalid review content. Review content cannot be null or empty.");
        }
        // Create Review, then put into repository

        Review review = new Review.Builder()
                .setReviewContent(reviewContent)
                .setCreatedAt(LocalDateTime.now())
                .setUser(user)
                .build();

        reviewRepository.save(review);

        List<Review> reviews = user.getReviews();
        reviews.add(review);

        userRepository.save(user);
    }


}
