package com.codecool.enterprise.model;

import javax.persistence.Entity;

@Entity
public class Rating {

    private int id;

    private int sellerId;

    private int buyerId;

    private int start;

    private  int productId;
}
