package com.example.parking.api;


import com.example.parking.model.Car;
import com.example.parking.service.admin.MongoAdmin;
import com.example.parking.service.dbClients.MongoDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/mongo")
public class MongoResource {

    @Autowired
    private MongoDB mongoDB = new MongoDB();

    @Autowired
    private MongoAdmin mongoAdmin = new MongoAdmin();

    @RequestMapping(value = "/generate_ticket",method = RequestMethod.POST)
    public Map<String, String> generateEntryTicket(@RequestBody Car car){

        return mongoDB.generateEntryTicket(car);
    }


    @RequestMapping(value = "/exit_ticket", method = RequestMethod.GET)
    public Map<String, String> exitTheTicket(@RequestParam(value = "id") String id){
        return mongoDB.exitTheTicket(id);
    }

    @RequestMapping(value = "/details_from_regnum", method = RequestMethod.GET)
    public List<Map<String, String>> detailsFromRegNum(@RequestParam String regNum){
        return mongoAdmin.detailsFromRegNum(regNum);
    }

    @RequestMapping(value = "/details_from_color", method = RequestMethod.GET)
    public List<Map<String, String>> detailsFromColor(@RequestParam String color){
        return mongoAdmin.detailsFromColor(color);
    }

    @RequestMapping(value = "/all_parked_cars", method = RequestMethod.GET)
    public List<Map<String,String>> allParkedCars(){
        return mongoAdmin.allParkedCars();
    }
}
