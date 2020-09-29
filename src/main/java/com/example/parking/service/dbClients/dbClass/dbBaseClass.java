package com.example.parking.service.dbClients.dbClass;

import com.example.parking.model.Car;
import com.example.parking.model.Response;

public interface dbBaseClass {
    void updateParkingLot();

    boolean checkCarInParkingLot(String str);

    Response generateEntryTicket(Car car);

    Response exitTheTicket(String id);
}
