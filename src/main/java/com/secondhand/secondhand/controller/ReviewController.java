package com.secondhand.secondhand.controller;

import com.secondhand.secondhand.model.Review;
import com.secondhand.secondhand.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/{username}/reviews")
    public List<Review> getAllReviewsOfUser(@PathVariable String username) {
        return reviewService.findByUsername(username);
    }

    @GetMapping("/reviews")
    public List<Review> getAllReviewsOfUserPath(@RequestParam("username") String username) {
        return reviewService.findByUsername(username);
    }

    @PostMapping("/{username}/reviews")
    public void addReviewPath(@PathVariable String username, @RequestParam("review_content") String reviewContent) {
        reviewService.createAndAddReview(username, reviewContent);
    }

    @PostMapping("/reviews")
    public void addReview(@RequestParam(name = "review_content") String reviewContent,
                          @RequestParam(name = "username") String username) {
        reviewService.createAndAddReview(username, reviewContent);
    }

}
