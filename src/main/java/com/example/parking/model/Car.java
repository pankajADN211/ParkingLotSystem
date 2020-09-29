package com.example.parking.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * Contain all Car fields
 */
public class Car {
    // We don't want user know our real class attributes
    @JsonProperty("regNum")
    private String registrationNumber;

    @JsonProperty("color")
    private String color;

    public Car() {
        this.registrationNumber = "";
        this.color = "";
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "registrationNumber='" + registrationNumber + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
