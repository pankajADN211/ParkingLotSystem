package com.example.parking.service.dbClients.dbClass;

import com.example.parking.model.Car;


import java.util.Map;

public interface dbBaseClass {
    void updateParkingLot();

    boolean checkCarInParkingLot(String str);

    Map<String,String> generateEntryTicket(Car car);

    Map<String,String> exitTheTicket(String id);
}
