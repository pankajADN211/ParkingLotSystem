package com.example.parking.service.dbClients;

import com.example.parking.ParkingLotManager;
import com.example.parking.dao.MongoDao;
import com.example.parking.model.Car;
import com.example.parking.model.Parking;
import com.example.parking.model.Response;
import com.example.parking.service.dbClients.dbClass.dbBaseClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Component
public class MongoDB implements dbBaseClass {

    SimpleDateFormat time = new SimpleDateFormat ("HH:mm:ss");
    SimpleDateFormat date = new SimpleDateFormat ("dd/MM/yyyy");
    static boolean updateParkingLot = true;

    @Autowired
    private MongoDao mongoDao;

    @Override
    public void updateParkingLot() {

         List<Parking>  parkList= mongoDao.findByExitDate("");
        for (Parking p : parkList) {
            ParkingLotManager.parkingLot[p.getFloor() - 1][p.getRow() - 1] = 1;
        }
    }

    @Override
    public boolean checkCarInParkingLot(String str) {
        List<Parking>  parkList= mongoDao.findByExitDate("");
        for (Parking p : parkList) {
            if(p.getCar().getRegistrationNumber().equalsIgnoreCase(str)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Response generateEntryTicket(Car car) {
        try
        {
            Response response = new Response();
            if(updateParkingLot){
                updateParkingLot();
                updateParkingLot = false;
            }
            /*
             * CHECK IF THIS CAR ALREADY IN OUR PARKING LOT.
             */
            Parking park = new Parking();
            if(checkCarInParkingLot(car.getRegistrationNumber())){
                response.setMessage("The Car with the Registration Number '"+car.getRegistrationNumber()+"' already in the Parking Lot");
                return response;
            }

            park.setUniqueId("WLPK"  + mongoDao.count());
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

            mongoDao.save(park);
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
            if(updateParkingLot){
                updateParkingLot();
                updateParkingLot = false;
            }
            id = id.toUpperCase();
            Parking park = mongoDao.findByUniqueId(id);
            boolean flag = true;

            if(park!=null){
                if(park.getExitDate().equalsIgnoreCase("")){
                    park.setExitDate(date.format(new Date()));
                    park.setExitTime(time.format(new Date()));
                    // freeing the space in parking lot
                    ParkingLotManager.parkingLot[park.getFloor()-1][park.getRow()-1]=0;
                    mongoDao.save(park);
                    response.setParking(park);
                }
                // Expired Ticket is used
                else {
                    response.setMessage("Ticket Is Already Expired");
                    return response;
                }

                flag = false;
            }

//             Ticket does not exist.
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
