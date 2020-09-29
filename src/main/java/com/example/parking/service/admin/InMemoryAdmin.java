package com.example.parking.service.admin;

import com.example.parking.model.Parking;
import com.example.parking.model.Response;
import com.example.parking.service.admin.adminclass.adminService;
import com.example.parking.service.dbClients.InMemoryDB;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class InMemoryAdmin implements adminService {


    @Override
    public List<Response> detailsFromRegNum(String regNumber) {
        Iterator<Parking> parkIt= InMemoryDB.parkingList.iterator();
        Parking park;
        List<Response> resList = new ArrayList<>();
        Response response;

        // Iterate the List
        while(parkIt.hasNext()){
            park = parkIt.next();
            if(regNumber.equalsIgnoreCase(park.getCar().getRegistrationNumber())){
                response = new Response();
                response.setParking(park);
                resList.add(response);

            }
        }
        if(resList.size()<1)
        {
            response = new Response();
            response.setMessage("No car with Registration Number "+regNumber+" is available in our system");
            resList.add(response);
        }

        return resList;
    }

    @Override
    public List<Response> detailsFromColor(String color) {
        Iterator<Parking> parkIt = InMemoryDB.parkingList.iterator();
        Parking park;
        List<Response> resList = new ArrayList<>();
        Response response;
       // Iterate the List
        while(parkIt.hasNext()){
            park = parkIt.next();
            if(color.equalsIgnoreCase(park.getCar().getColor())){
                response = new Response();
                response.setParking(park);
                resList.add(response);
            }
        }
        if(resList.size()<1)
        {
            response = new Response();
            response.setMessage("No car with Color "+color+" is available in our system");
            resList.add(response);
        }

        return resList;
    }

    @Override
    public List<Response> allParkedCars() {
        Iterator<Parking> parkIt = InMemoryDB.parkingList.iterator();
        Parking park;
        List<Response> resList = new ArrayList<>();
        Response response;
        // Iterate the List
        while(parkIt.hasNext()){
            park = parkIt.next();
            if(park.getExitDate().equalsIgnoreCase("")){
                response = new Response();
                response.setParking(park);
                resList.add(response);

            }
        }
        if(resList.size()<1)
        {
            response = new Response();
            response.setMessage("No car is available in our ParkingLot");
            resList.add(response);
        }

        return resList;

    }
}
