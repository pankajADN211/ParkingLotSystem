package com.example.parking.service.admin;

import com.example.parking.model.Parking;
import com.example.parking.service.admin.adminclass.adminservice;
import com.example.parking.service.dbClients.InMemoryDB;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class InMemoryAdmin implements adminservice {


    @Override
    public List<Map<String, String>> detailsFromRegNum(String regNumber) {
        Iterator<Parking> parkIt= InMemoryDB.parkingList.iterator();
        Parking park;
        List<Map<String,String>> mapList = new ArrayList<>();
        Map<String,String> mp;

        // Iterate the List
        while(parkIt.hasNext()){
            park = parkIt.next();

            if(regNumber.equalsIgnoreCase(park.getCar().getRegistrationNumber())){
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
        Iterator<Parking> parkIt = InMemoryDB.parkingList.iterator();
        Parking park;
        List<Map<String,String>> mapList = new ArrayList<>();
        Map<String,String> mp;
       // Iterate the List
        while(parkIt.hasNext()){
            park = parkIt.next();

            if(color.equalsIgnoreCase(park.getCar().getColor())){
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
        Iterator<Parking> parkIt = InMemoryDB.parkingList.iterator();
        Parking park;
        List<Map<String,String >> mapList = new ArrayList<>();
        Map<String,String> mp;
        // Iterate the List
        while(parkIt.hasNext()){
            park = parkIt.next();

            if(park.getExitDate().equalsIgnoreCase("")){
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
            mp.put("message","No car is available in our system");
            mapList.add(mp);
        }

        return mapList;

    }
}
