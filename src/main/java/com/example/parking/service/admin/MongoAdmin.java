package com.example.parking.service.admin;

import com.example.parking.dao.MongoDao;
import com.example.parking.model.Parking;
import com.example.parking.model.Response;
import com.example.parking.service.admin.adminclass.adminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MongoAdmin implements adminService {

    @Autowired
    private MongoDao mongoDao;

    @Override
    public List<Response> detailsFromRegNum(String regNumber) {
        List<Parking> parkIt = mongoDao.findAll();
        List<Response> resList = new ArrayList<>();
        Response response;

        for(Parking park : parkIt ){
            if(park.getCar().getRegistrationNumber().equalsIgnoreCase(regNumber)){
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
        List<Parking> parkIt = mongoDao.findAll();
        List<Response> resList = new ArrayList<>();
        Response response;

        for(Parking park : parkIt ){
            if(park.getCar().getColor().equalsIgnoreCase(color)){
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
        List<Parking> parkIt = mongoDao.findByExitDate("");
        List<Response> resList = new ArrayList<>();
        Response response;

        for(Parking park : parkIt ){
            response = new Response();
            response.setParking(park);
            resList.add(response);
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
