package com.example.parking.service.admin.adminclass;


import java.util.List;
import java.util.Map;

public interface adminservice {

    // Registration numbers of all cars of a Particular color
//    String carsFromColor(String color);

    // Slot number in which a car with a given registration number is parked
    List<Map<String,String>> detailsFromRegNum (String regNumber);

    // Slot numbers of all slots where a car of a particular color is parked
    List<Map<String,String>> detailsFromColor(String color);

    // Get all cars available in Parking LOT
    List<Map<String,String>> allParkedCars();
}
