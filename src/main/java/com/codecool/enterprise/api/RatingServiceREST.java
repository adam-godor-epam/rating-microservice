package com.codecool.enterprise.api;

import com.codecool.enterprise.model.Rating;
import com.codecool.enterprise.service.RatingService;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RatingServiceREST {

    private RatingService ratingService;


    public RatingServiceREST(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("/rating/user/{id}")
    public ResponseEntity<List<Rating>> getAllUserRatings(@PathVariable("id") int id) {
        return new ResponseEntity(ratingService.getRatingsBySellerId(id), HttpStatus.OK);
    }

    @GetMapping("/rating/user/{id}/avg")
    public ResponseEntity getUserRateAvg(@PathVariable("id") int id) {
        JSONObject avg = new JSONObject();
        avg.put("avg", ratingService.getAverageRatingBySellerId(id));
        return new ResponseEntity(avg, HttpStatus.OK);
    }

    @GetMapping("/rating/user/{id}/count")
    public ResponseEntity getUserRateCount(@PathVariable("id") int id) {
        JSONObject avg = new JSONObject();
        avg.put("count", ratingService.getRatingCountBySellerId(id));
        return new ResponseEntity(avg, HttpStatus.OK);
    }

}

