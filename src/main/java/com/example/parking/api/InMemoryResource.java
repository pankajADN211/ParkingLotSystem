package com.example.parking.api;

import com.example.parking.model.Car;
import com.example.parking.model.Response;
import com.example.parking.service.admin.InMemoryAdmin;
import com.example.parking.service.dbClients.InMemoryDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/inMemory")
public class InMemoryResource {

    @Autowired
    private final InMemoryDB inMemoryDB = new InMemoryDB();

    @Autowired
    private final InMemoryAdmin inMemoryAdmin = new InMemoryAdmin();

    @RequestMapping(value = "/generateTicket",method = RequestMethod.POST)
    public Response generateEntryTicket(@RequestBody Car car){
        return inMemoryDB.generateEntryTicket(car);
    }

    @RequestMapping(value = "/exitTicket")
    public Response exitTheTicket(@RequestParam(value = "id") String id){
        return inMemoryDB.exitTheTicket(id);
    }

    @RequestMapping(value = "/detailsFromRegistrationNumber", method = RequestMethod.GET)
    public List<Response> detailsFromRegNum(@RequestParam String regNum){
        return inMemoryAdmin.detailsFromRegNum(regNum);
    }

    @RequestMapping(value = "/detailsFromColor", method = RequestMethod.GET)
    public List<Response> detailsFromColor(@RequestParam String color){
        return inMemoryAdmin.detailsFromColor(color);
    }

    @RequestMapping(value = "/allParkedCars", method = RequestMethod.GET)
    public List<Response> allParkedCars(){
        return inMemoryAdmin.allParkedCars();
    }

}
