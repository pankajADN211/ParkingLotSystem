package com.example.parking.dao;

import com.example.parking.model.Parking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MongoDao extends MongoRepository<Parking,String> {

    @Override
    List<Parking> findAll();

    List<Parking> findByExitDate(String exitDate);

    Parking findByUniqueId(String uniqueId);

//    List<Parking> findByRegistrationNumber(String registrationNumber);
//
//    List<Parking> findByColor(String color);

}
