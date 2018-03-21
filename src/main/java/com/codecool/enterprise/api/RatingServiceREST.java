package com.codecool.enterprise.api;

import com.codecool.enterprise.model.Rating;
import com.codecool.enterprise.service.RatingService;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class RatingServiceREST {

    private RatingService ratingService;


    public RatingServiceREST(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("/rating/user/{id}")
    public ResponseEntity<List<Rating>> getAllUserRatings(@PathVariable("id") int id) {
        JSONObject ratings = new JSONObject();
        ratings.put("ratingList", ratingService.getRatingsBySellerId(id));
        return new ResponseEntity(ratings, HttpStatus.OK);
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

    @PostMapping("/rating")
    public HttpStatus saveRating(HttpServletRequest req){
        try{
            int sellerId = Integer.parseInt(req.getParameter("sellerId"));
            int buyerId = Integer.parseInt(req.getParameter("buyerId"));
            int productId = Integer.parseInt(req.getParameter("productId"));
            int stars = Integer.parseInt(req.getParameter("stars"));
            String review = req.getParameter("review");
            Rating rating = new Rating(sellerId, buyerId, productId, stars, review);
            ratingService.saveRating(rating);
            return HttpStatus.OK;
        } catch (NumberFormatException e){
            return HttpStatus.BAD_REQUEST;
        }

    }

}

