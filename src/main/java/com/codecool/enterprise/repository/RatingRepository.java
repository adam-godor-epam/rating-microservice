package com.codecool.enterprise.repository;


import com.codecool.enterprise.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RatingRepository extends JpaRepository<Rating, Integer> {
    public Rating findRatingByProductId(int productId);

    public List<Rating> findAllBySellerId(int sellerId);

    public int countAllBySellerId(int sellerId);

    @Query("SELECT AVG(stars) FROM Rating WHERE seller_id=:sellerId")
    public Float getAverageRatingBySellerId(@Param("sellerId") int sellerId);
}
