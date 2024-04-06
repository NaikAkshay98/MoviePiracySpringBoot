package com.moviepiracy.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "banners")
public class Banner {

    @Id
    private String id;
    private String image; // Assuming you meant "image" to match your JSON
    private String title;

    public Banner() {
    }

    public Banner(String id, String image, String title) {
        this.id = id;
        this.image = image;
        this.title = title;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
