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
        JSONArray jsonArray = new JSONArray();

        List<Rating> userRatings = ratingService.getRatingsBySellerId(id);

        for (Rating rating: userRatings) {
            JSONObject rateObj = new JSONObject();
            rateObj.put("id", rating.getId());
            rateObj.put("sellerId", rating.getSellerId());
            rateObj.put("buyerId", rating.getBuyerId());
            rateObj.put("productId", rating.getProductId());
            rateObj.put("stars", rating.getStars());
            rateObj.put("review", rating.getReview());

            jsonArray.add(rateObj);
        }

        jsonObject.put("ratingList", jsonArray);

        return new ResponseEntity(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/rating/user/{id}/avg")
    public ResponseEntity getUserRateAvg(@PathVariable("id") int id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("avg", ratingService.getAverageRatingBySellerId(id));
        return new ResponseEntity(jsonObject.toString(), HttpStatus.OK);
    }

    @GetMapping("/rating/user/{id}/count")
    public ResponseEntity getUserRateCount(@PathVariable("id") int id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("count", ratingService.getRatingCountBySellerId(id));
        return new ResponseEntity(jsonObject.toString(), HttpStatus.OK);
    }
/*
    @PostMapping("/rating")
    public ResponseEntity saveUserRate() {
        String inputString;
        RestTemplate restTemplate = new RestTemplate();
        try {
            inputString = restTemplate.getForEntity(inputUrl, String.class).getBody();
        } catch (RestClientException e) {
            System.out.println("Provider API is not running");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        Gson input = new Gson();
        input.toJson(inputString);

        Rating rating = new Rating(input.fromJson("sellerId", int.class),
                input.fromJson("buyerId", int.class),
                input.fromJson("productId", int.class),
                input.fromJson("stars", int.class),
                input.fromJson("review", String.class));
        ratingService.saveRating(rating);

        return new ResponseEntity(HttpStatus.OK);
    }*/

    @PostMapping("/rating")
    public HttpStatus saveRating(HttpServletRequest req){
        try{
            int sellerId = Integer.parseInt(req.getParameter("sellerId"));
            int buyerId = Integer.parseInt(req.getParameter("buyerId"));
            int productId = Integer.parseInt(req.getParameter("productId")); // TODO check if prod exists and throw error
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

