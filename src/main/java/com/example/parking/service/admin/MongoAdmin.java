package com.example.parking.service.admin;

import com.example.parking.dao.MongoDao;
import com.example.parking.model.Parking;
import com.example.parking.service.admin.adminclass.adminservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MongoAdmin implements adminservice {

    @Autowired
    private MongoDao mongoDao;

    @Override
    public List<Map<String, String>> detailsFromRegNum(String regNumber) {

        List<Parking> parkIt = mongoDao.findAll();
        List<Map<String,String>> mapList = new ArrayList<>();
        Map<String,String> mp;

        for(Parking park : parkIt ){
            if(park.getCar().getRegistrationNumber().equalsIgnoreCase(regNumber)){
                mp = new LinkedHashMap<>();
                mp.put("TicketId",park.getUniqueId());
                mp.put("Floor",Integer.toString(park.getFloor()));
                mp.put("Row",Integer.toString(park.getRow()));
                mp.put("Registration Number",park.getCar().getRegistrationNumber().toUpperCase());
                mp.put("Color", park.getCar().getColor().toUpperCase());
                mp.put("Entry Date",park.getEntryDate());
                mp.put("Entry Time", park.getEntryTime());
                mp.put("Exit Date",park.getExitDate());
                mp.put("Exit Time",park.getExitTime());

                mapList.add(mp);
            }

        }
        if(mapList.size()<1)
        {
            mp = new LinkedHashMap<>();
            mp.put("message","No car with Registration Number "+regNumber+" is available in our system");
            mapList.add(mp);
        }

        return mapList;
    }

    @Override
    public List<Map<String, String>> detailsFromColor(String color) {

        List<Parking> parkIt = mongoDao.findAll();
        List<Map<String,String>> mapList = new ArrayList<>();
        Map<String,String> mp;

        for(Parking park : parkIt ){
            if(park.getCar().getColor().equalsIgnoreCase(color)){
                mp = new LinkedHashMap<>();
                mp.put("TicketId",park.getUniqueId());
                mp.put("Floor",Integer.toString(park.getFloor()));
                mp.put("Row",Integer.toString(park.getRow()));
                mp.put("Registration Number",park.getCar().getRegistrationNumber().toUpperCase());
                mp.put("Color", park.getCar().getColor().toUpperCase());
                mp.put("Entry Date",park.getEntryDate());
                mp.put("Entry Time", park.getEntryTime());
                mp.put("Exit Date",park.getExitDate());
                mp.put("Exit Time",park.getExitTime());

                mapList.add(mp);
            }
        }
        if(mapList.size()<1)
        {
            mp = new LinkedHashMap<>();
            mp.put("message","No car with Color "+color+" is available in our system");
            mapList.add(mp);
        }

        return mapList;
    }

    @Override
    public List<Map<String,String >> allParkedCars() {
        List<Parking> parkIt = mongoDao.findByExitDate("");
        List<Map<String,String>> mapList = new ArrayList<>();
        Map<String,String> mp;

        for(Parking park : parkIt ){
            mp = new LinkedHashMap<>();
            mp.put("TicketId",park.getUniqueId());
            mp.put("Floor",Integer.toString(park.getFloor()));
            mp.put("Row",Integer.toString(park.getRow()));
            mp.put("Registration Number",park.getCar().getRegistrationNumber().toUpperCase());
            mp.put("Color", park.getCar().getColor().toUpperCase());
            mp.put("Entry Date",park.getEntryDate());
            mp.put("Entry Time", park.getEntryTime());
            mp.put("Exit Date",park.getExitDate());
            mp.put("Exit Time",park.getExitTime());

            mapList.add(mp);
        }
        if(mapList.size()<1)
        {
            mp = new LinkedHashMap<>();
            mp.put("message","No car is available in our ParkingLot");
            mapList.add(mp);
        }

        return mapList;

    }

}
