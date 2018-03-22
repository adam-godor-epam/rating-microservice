package com.codecool.enterprise.service;

import com.codecool.enterprise.model.Rating;
import com.codecool.enterprise.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
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

    public String getAverageRatingBySellerId(int sellerId) {
        if (ratingRepository.getAverageRatingBySellerId(sellerId) == null) {
            return "No ratings yet...";
        } else {
            DecimalFormat df = new DecimalFormat("#.0");
            return df.format(ratingRepository.getAverageRatingBySellerId(sellerId));
        }
    }

    public int getRatingCountBySellerId(int sellerId) {
        return ratingRepository.countAllBySellerId(sellerId);
    }

    public Rating getRatingByProductId(int productId) {
        return ratingRepository.findRatingByProductId(productId);
    }

    public boolean starsValid(int stars) {
        return stars > 0 && stars < 6;
    }
}
