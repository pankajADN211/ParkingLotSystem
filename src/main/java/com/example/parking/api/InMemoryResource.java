package com.example.parking.api;

import com.example.parking.model.Car;

import com.example.parking.service.admin.InMemoryAdmin;
import com.example.parking.service.dbClients.InMemoryDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/in_memory")
public class InMemoryResource {

    @Autowired
    private InMemoryDB inMemoryDB = new InMemoryDB();

    @Autowired
    private InMemoryAdmin inMemoryAdmin = new InMemoryAdmin();


    @RequestMapping(value = "/generate_ticket",method = RequestMethod.POST)
    public Map<String, String> generateEntryTicket(@RequestBody Car car){

        return inMemoryDB.generateEntryTicket(car);
    }


    @RequestMapping(value = "/exit_ticket", method = RequestMethod.GET)
    public Map<String, String> exitTheTicket(@RequestParam(value = "id") String id){
        return inMemoryDB.exitTheTicket(id);
    }

    @RequestMapping(value = "/details_from_regnum", method = RequestMethod.GET)
    public List<Map<String, String>> detailsFromRegNum(@RequestParam String regNum){
        return inMemoryAdmin.detailsFromRegNum(regNum);
    }

    @RequestMapping(value = "/details_from_color", method = RequestMethod.GET)
    public List<Map<String, String>> detailsFromColor(@RequestParam String color){
        return inMemoryAdmin.detailsFromColor(color);
    }

    @RequestMapping(value = "/all_parked_cars", method = RequestMethod.GET)
    public List<Map<String,String>> allParkedCars(){
        return inMemoryAdmin.allParkedCars();
    }




}
