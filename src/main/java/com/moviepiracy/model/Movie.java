package com.moviepiracy.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "movies")
public class Movie {
    @Id
    private String id;
    private String title;
    private String synopsis;
    private String poster;
    private String rentPrice;
    private String purchasePrice;
    private boolean featured; // Add this line to include the featured field

    // Constructors
    public Movie() {
    }

    public Movie(String id, String title, String synopsis, String poster, String rentPrice, String purchasePrice, boolean featured) {
        this.id = id;
        this.title = title;
        this.synopsis = synopsis;
        this.poster = poster;
        this.rentPrice = rentPrice;
        this.purchasePrice = purchasePrice;
        this.featured = featured; // Initialize featured field in constructor
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(String rentPrice) {
        this.rentPrice = rentPrice;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public boolean isFeatured() { // Getter for featured
        return featured;
    }

    public void setFeatured(boolean featured) { // Setter for featured
        this.featured = featured;
    }
}
