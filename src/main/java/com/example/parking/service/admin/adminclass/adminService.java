package com.example.parking.service.admin.adminclass;

import com.example.parking.model.Response;
import java.util.List;

public interface adminService {

    // Registration numbers of all cars of a Particular color
//    String carsFromColor(String color);

    // Slot number in which a car with a given registration number is parked
    List<Response> detailsFromRegNum (String regNumber);

    // Slot numbers of all slots where a car of a particular color is parked
    List<Response> detailsFromColor(String color);

    // Get all cars available in Parking LOT
    List<Response> allParkedCars();
}
