package com.codecool.enterprise.repository;


import com.codecool.enterprise.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RatingRepository extends JpaRepository<Rating, Integer> {

}
