package com.example.parking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParkingLotManager {
	/*
	 * Creating Array list of all parking till the time.
	 * parkingLot is 2d variable having 5 floors and 20 rows for car parking.
	 */
	public static int FLOOR = 5;
	public static int ROW = 20;
	public static int[][] parkingLot=new int[FLOOR][ROW];


	public static void main(String[] args) {

		SpringApplication.run(ParkingLotManager.class, args);

	}

}
