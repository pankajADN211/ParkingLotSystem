package com.example.parking.api;

import com.example.parking.model.Car;
import com.example.parking.model.Response;
import com.example.parking.service.admin.MongoAdmin;
import com.example.parking.service.dbClients.MongoDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/parking")
public class MongoResource {

    @Autowired
    private final MongoDB mongoDB = new MongoDB();

    @Autowired
    private final MongoAdmin mongoAdmin = new MongoAdmin();

    @RequestMapping(value = "/generateTicket",method = RequestMethod.POST)
    public Response generateEntryTicket(@RequestBody Car car){
        return mongoDB.generateEntryTicket(car);
    }

    @RequestMapping(value = "/exitTicket")
    public Response exitTheTicket(@RequestParam(value = "id") String id){
        return mongoDB.exitTheTicket(id);
    }

    @RequestMapping(value = "/detailsFromRegistrationNumber", method = RequestMethod.GET)
    public List<Response> detailsFromRegNum(@RequestParam String regNum){
        return mongoAdmin.detailsFromRegNum(regNum);
    }

    @RequestMapping(value = "/detailsFromColor", method = RequestMethod.GET)
    public List<Response> detailsFromColor(@RequestParam String color){
        return mongoAdmin.detailsFromColor(color);
    }

    @RequestMapping(value = "/allParkedCars", method = RequestMethod.GET)
    public List<Response> allParkedCars(){
        return mongoAdmin.allParkedCars();
    }
}
