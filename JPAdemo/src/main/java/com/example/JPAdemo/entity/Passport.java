package com.example.JPAdemo.entity;

import jakarta.persistence.*;

@Entity
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer passportNumber;
    @OneToOne
    private Student student;

    public Passport() {
    }

    public Passport(Integer passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Integer getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(Integer passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "id=" + id +
                ", passportNumber=" + passportNumber +
                '}';
    }
}
