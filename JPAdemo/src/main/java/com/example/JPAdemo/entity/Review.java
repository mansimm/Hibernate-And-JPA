package com.example.JPAdemo.entity;

import jakarta.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private Rating rating;
    private String description;

    @ManyToOne
    private Course course;

    public Review() {
    }

    public Review(Rating rating, String description) {
        this.rating = rating;
        this.description =description;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Review{" +
                "description='" + description + '\'' +
                ", rating=" + rating +
                '}';
    }
}
