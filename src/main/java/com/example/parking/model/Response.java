package com.example.parking.model;

public class Response {

    private Parking parking;
    private String message;

    public Response() {
        this.parking = new Parking();
        this.message = "Success";
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
