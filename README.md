# ParkingLotSystem  
  
#### To run the jar on docker
* after generating the JAR using intellij, open the terminal goto root directory of project and run following docker command  
* `docker-compose up`  
  
#### APIs to run on browser  
* `localhost:8080`
  * home page
* `localhost:8080/parking/generateTicket`  
  * It's a post method, accept json script  
  ```
  {
    "regNum":"cccc",
    "color":"red"
  }
  ```
* `localhost:8080/parking/exitTicket?id=uniqueId`
  * exit the ticket from parking lot
  
* `localhost:8080/parking/allParkedCars`
  * it's a get method, give all Available cars in the parking Lot

* `localhost:8080/parking/detailsFromRegistrationNumber?regNum=regNum`
  * it's a get method, give all details of given car
  
* `localhost:8080/parking/detailsFromColor?color=color`
  * it's a get method, give all cars' details of given color 


