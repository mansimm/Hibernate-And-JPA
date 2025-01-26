package com.example.JPAdemo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "course")
    List<Review> reviews = new ArrayList<>();

    @ManyToMany(fetch=FetchType.LAZY)
    private List<Student> students = new ArrayList<>();

    public Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReviews(Review review) {
        this.reviews.add(review);
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
