package com.codecool.enterprise.api;

import com.codecool.enterprise.model.Rating;
import com.codecool.enterprise.service.RatingService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        JSONObject jsonObject = new JSONObject();
        List<Rating> userRatings = ratingService.getRatingsBySellerId(id);
        JSONArray jsonArray = createRatingJsonArray(userRatings);
        jsonObject.put("ratingList", jsonArray);
        return new ResponseEntity(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/rating/user/{id}/avg")
    public ResponseEntity getUserRateAvg(@PathVariable("id") int id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("avg", ratingService.getAverageRatingBySellerId(id));
        return new ResponseEntity(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/rating/user/{id}/count")
    public ResponseEntity getUserRateCount(@PathVariable("id") int id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("count", ratingService.getRatingCountBySellerId(id));
        return new ResponseEntity(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/rating")
    public ResponseEntity saveRating(HttpServletRequest req){
        try{
            int sellerId = Integer.parseInt(req.getParameter("sellerId"));
            int buyerId = Integer.parseInt(req.getParameter("buyerId"));
            int productId = Integer.parseInt(req.getParameter("productId"));
            int stars = Integer.parseInt(req.getParameter("stars"));
            String review = req.getParameter("review");
            String errorMessage = "product is already taken";
            boolean starsValid = true;
            if(!ratingService.starsValid(stars)) {
                starsValid = false;
                errorMessage = "the rating has to be between 1-5";
            }

            if (ratingService.getRatingByProductId(productId) == null && starsValid) {
                Rating rating = new Rating(sellerId, buyerId, productId, stars, review);
                ratingService.saveRating(rating);
                return ResponseEntity.status(HttpStatus.OK).body(null);
            } else {
                return new ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST);
            }

        } catch (NumberFormatException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private JSONArray createRatingJsonArray(List<Rating> userRatings) {
        JSONArray jsonArray = new JSONArray();
        for (Rating rating : userRatings) {
            JSONObject rateObj = new JSONObject();
            rateObj.put("id", rating.getId());
            rateObj.put("sellerId", rating.getSellerId());
            rateObj.put("buyerId", rating.getBuyerId());
            rateObj.put("productId", rating.getProductId());
            rateObj.put("stars", rating.getStars());
            rateObj.put("review", rating.getReview());

            jsonArray.add(rateObj);
        }
        return jsonArray;
    }

}

