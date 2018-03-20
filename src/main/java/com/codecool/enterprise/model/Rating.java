package com.codecool.enterprise.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Rating {

    @Id
    private int id;

    @NotNull
    private int sellerId;

    @NotNull
    private int buyerId;

    @NotNull
    private int productId;

    @NotNull
    @Min(1)
    @Max(5)
    private int stars;

    private String review;

    public Rating() {
    }

    public Rating(int id, int sellerId, int buyerId, int productId, int stars, String review) {
        this.id = id;
        this.sellerId = sellerId;
        this.buyerId = buyerId;
        this.productId = productId;
        this.stars = stars;
        this.review = review;
    }
}
