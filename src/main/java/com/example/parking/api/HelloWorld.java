package com.example.parking.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;

@RestController
public class HelloWorld {

    @RequestMapping(value = "/hello")
    public String sayHello(){
        return "Hello!!! Welcome to Parking Lot System";
    }

    @RequestMapping
    public String home(){
        return "Home Page of Parking Lot Manager !!!  "+ new Date();
    }
}
