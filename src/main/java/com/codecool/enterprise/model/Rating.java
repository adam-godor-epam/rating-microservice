package com.codecool.enterprise.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private int sellerId;

    @NotNull
    private int buyerId;

    @NotNull
    @Column(unique = true)
    private int productId;

    @NotNull
    @Min(1)
    @Max(5)
    private int stars;

    private String review;

    private Rating() {
    }

    public Rating(int sellerId, int buyerId, int productId, int stars, String review) {
        this.sellerId = sellerId;
        this.buyerId = buyerId;
        this.productId = productId;
        this.stars = stars;
        this.review = review;
    }

    public int getId() {
        return id;
    }

    public int getSellerId() {
        return sellerId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public int getProductId() {
        return productId;
    }

    public int getStars() {
        return stars;
    }

    public String getReview() {
        return review;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", sellerId=" + sellerId +
                ", buyerId=" + buyerId +
                ", productId=" + productId +
                ", stars=" + stars +
                ", review='" + review;
    }
}
