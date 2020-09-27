package com.example.parking.service.dbClients;

import com.example.parking.ParkingLotManager;
import com.example.parking.model.Car;
import com.example.parking.model.Parking;

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
    public Map<String,String> generateEntryTicket(Car car) {
        try {

            Map<String,String> mp = new LinkedHashMap<>();
            /*
             * CHECK IF THIS CAR ALREADY IN OUR PARKING LOT.
             */
            Parking park = new Parking();
            if(checkCarInParkingLot(car.getRegistrationNumber())){

                mp.put("Message","The Car with the Registration Number '"+car.getRegistrationNumber()+"' already in the Parking Lot");

                return mp;
            }


            // Assigning Parking Variables for our Database.

//            SimpleDateFormat d = new SimpleDateFormat("yyMM");
            // Set Parking UniqueID
            park.setUniqueId("WLPK"  + parkingList.size());

            int floor = 0, row = 0;
            boolean flag=true;
            for (int f = 0; f < ParkingLotManager.getFLOOR(); f++) {
                for (int r = 0; r < ParkingLotManager.getROW(); r++) {
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
                mp.put("Message","SORRY OUR PARKING IS FULL");

                return mp;
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

            mp.put("TicketId",park.getUniqueId());
            mp.put("Floor",Integer.toString(park.getFloor()));
            mp.put("Row",Integer.toString(park.getRow()));
            mp.put("Registration Number",park.getCar().getRegistrationNumber().toUpperCase());
            mp.put("Color", park.getCar().getColor().toUpperCase());
            mp.put("Entry Date",park.getEntryDate());
            mp.put("Entry Time", park.getEntryTime());
            mp.put("Exit Date",park.getExitDate());
            mp.put("Exit Time",park.getExitTime());

            return mp;
        }
        catch(Exception e) {

            e.printStackTrace();
            return null;

        }

    }

    @Override
    public Map<String, String > exitTheTicket(String id) {
        try{
            id = id.toUpperCase();
            Parking park;
            Iterator<Parking> parkIt= parkingList.iterator();
            Map<String,String> mp = new LinkedHashMap<>();

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
                        // Generating Ticket

                        mp.put("TicketId",park.getUniqueId());
                        mp.put("Floor",Integer.toString(park.getFloor()));
                        mp.put("Row",Integer.toString(park.getRow()));
                        mp.put("Registration Number",park.getCar().getRegistrationNumber().toUpperCase());
                        mp.put("Color", park.getCar().getColor().toUpperCase());
                        mp.put("Entry Date",park.getEntryDate());
                        mp.put("Entry Time", park.getEntryTime());
                        mp.put("Exit Date",park.getExitDate());
                        mp.put("Exit Time",park.getExitTime());

                    }
                    // Expired Ticket is used
                    else {
                        mp.put("Message","Ticket Is Already Expired");
                        return mp;
                    }
                    flag = false;
                    break;
                }
            }
            // Ticket does not exist.
            if(flag){
                mp.put("Message","Ticket ID is Not Valid");
                return mp;

            }
            return mp;


        }
        catch(Exception e) {
            e.printStackTrace();
            return null;

        }

    }


}
