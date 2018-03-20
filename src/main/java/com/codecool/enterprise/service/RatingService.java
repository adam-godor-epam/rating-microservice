package com.codecool.enterprise.service;

import com.codecool.enterprise.model.Rating;
import com.codecool.enterprise.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public void saveRating(Rating rating) {
        ratingRepository.save(rating);
    }

    public List<Rating> getRatingsBySellerId(int sellerId) {
        return ratingRepository.findAllBySellerId(sellerId);
    }

    public float getAverageRatingBySellerId(int sellerId) {
        return ratingRepository.getAverageRatingBySellerId(sellerId);
    }

    public int getRatingCountBySellerId(int sellerId) {
        return ratingRepository.countAllBySellerId(sellerId);
    }
}
