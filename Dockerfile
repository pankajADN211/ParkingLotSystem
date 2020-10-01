FROM openjdk:11
ADD target/parkingLotManager.jar parkingLotManager.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "parkingLotManager.jar"]