package com.example.parking;

import com.example.parking.model.Parking;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Scanner;

@SpringBootApplication
public class ParkingLotManager {

	/*
	 * Creating Array list of all parking till the time.
	 * parkingLot is 2d variable having 5 floors and 20 rows for car parking.
	 */
//	public static ArrayList<Parking> parkingList = new ArrayList<>();
	static int FLOOR = 5;
	static int ROW = 20;
	public static int[][] parkingLot=new int[FLOOR][ROW];
	public static String Database;
	public static BufferedReader br=null;
	public static Scanner sc=null;


	public static int getFLOOR() {
		return FLOOR;
	}

	public static void setFLOOR(int FLOOR) {
		ParkingLotManager.FLOOR = FLOOR;
	}

	public static int getROW() {
		return ROW;
	}

	public static void setROW(int ROW) {
		ParkingLotManager.ROW = ROW;
	}


	public static void main(String[] args) {

		SpringApplication.run(ParkingLotManager.class, args);

	}

}
