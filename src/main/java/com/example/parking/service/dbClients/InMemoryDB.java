package com.example.parking.service.dbClients;

import com.example.parking.ParkingLotManager;
import com.example.parking.model.Car;
import com.example.parking.model.Parking;

import com.example.parking.model.Response;
import org.springframework.stereotype.Component;
import com.example.parking.service.dbClients.dbClass.dbBaseClass;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class InMemoryDB implements dbBaseClass{

    SimpleDateFormat time = new SimpleDateFormat ("HH:mm:ss");
    SimpleDateFormat date = new SimpleDateFormat ("dd/MM/yyyy");
    public static List<Parking> parkingList = new CopyOnWriteArrayList<>();
    @Override
    public void updateParkingLot() {
            System.out.println("Updated");
    }

    @Override
    public boolean checkCarInParkingLot(String regNum) {
        for (Parking parking : parkingList) {
            if (parking.getCar().getRegistrationNumber().equalsIgnoreCase(regNum) && parking.getExitDate().equalsIgnoreCase("")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Response generateEntryTicket(Car car) {
        try {
            Response response = new Response();
            /*
             * CHECK IF THIS CAR ALREADY IN OUR PARKING LOT.
             */
            Parking park = new Parking();
            if(checkCarInParkingLot(car.getRegistrationNumber())){
                response.setMessage("The Car with the Registration Number '"+car.getRegistrationNumber()+"' already in the Parking Lot");
                return response;
            }
            // Set Parking UniqueID
            park.setUniqueId("WLPK"  + parkingList.size());

            int floor = 0, row = 0;
            boolean flag=true;
            for (int f = 0; f < ParkingLotManager.FLOOR; f++) {
                for (int r = 0; r < ParkingLotManager.ROW; r++) {
                    if (ParkingLotManager.parkingLot[f][r] == 0) {
                        ParkingLotManager.parkingLot[f][r] = 1;
                        floor = f;
                        row = r;
                        flag = false;
                        break;
                    }
                }
                if (!flag) { break; }
            }

            if (flag) {
                response.setMessage("SORRY OUR PARKING IS FULL");
                return response;
            }

            // Setting allotted parking floor and row.
            park.setFloor(floor+1);
            park.setRow(row+1);

            //Setting core.Car core.Parking Entry date and time.
            car.setRegistrationNumber(car.getRegistrationNumber().toUpperCase());
            car.setColor(car.getColor().toUpperCase());
            park.setCar(car);
            park.setEntryDate(date.format(new Date()));
            park.setEntryTime(time.format(new Date()));

            parkingList.add(park);
            // Generating Ticket
            response.setParking(park);
            return response;
        }
        catch(Exception e) {

            e.printStackTrace();
            return null;

        }

    }

    @Override
    public Response exitTheTicket(String id) {
        try
        {
            Response response = new Response();
            id = id.toUpperCase();
            Parking park;
            Iterator<Parking> parkIt= parkingList.iterator();
            //Exit the car entering Ticket ID
            boolean flag = true;
            // Iterate the List to check ticket is exist or not
            while(parkIt.hasNext()){
                park = parkIt.next();
                String unq = park.getUniqueId();
                if(id.equalsIgnoreCase(unq)) {
                    if(park.getExitDate().equalsIgnoreCase("")){
                        park.setExitDate(date.format(new Date()));
                        park.setExitTime(time.format(new Date()));
                        // freeing the space in parking lot
                        ParkingLotManager.parkingLot[park.getFloor()-1][park.getRow()-1]=0;
                        response.setParking(park);
                    }
                    // Expired Ticket is used
                    else {
                        response.setMessage("Ticket Is Already Expired");
                        return response;
                    }
                    flag = false;
                    break;
                }
            }
            // Ticket does not exist.
            if(flag){
                response.setMessage("Ticket ID is Not Valid");
                return response;

            }
            return response;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;

        }

    }


}
