package com.example.JPAdemo.entity;

import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
public class PartTimeEmployee extends Employee{
    private BigDecimal hourlyWages;

    public PartTimeEmployee(String name, BigDecimal hourlyWages) {
        super(name);
        this.hourlyWages = hourlyWages;
    }

    public PartTimeEmployee() {
    }

    public BigDecimal getHourlyWages() {
        return hourlyWages;
    }

    public void setHourlyWages(BigDecimal hourlyWages) {
        this.hourlyWages = hourlyWages;
    }

    @Override
    public String toString() {
        return "PartTimeEmployee{" +
                "hourlyWages=" + hourlyWages +
                '}';
    }
}
