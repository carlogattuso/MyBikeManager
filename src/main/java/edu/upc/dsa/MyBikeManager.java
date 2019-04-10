package edu.upc.dsa;

import edu.upc.dsa.models.*;

import java.util.List;

public interface MyBikeManager {
    User addUser(String id, String name) throws ExistantUserException;
    Station addStation(String id, String place) throws ExistantStationException;
    Bike addBike(String bikeId, String stationId, String userId, double kms) throws StationNotFoundException, UserNotFoundException, BikeNotFoundException, WrongStationException;

    Station getStation(String stationId) throws StationNotFoundException;
    User getUser(String userId) throws UserNotFoundException;

    Bike getBike(String bikeId,String userId, String stationId) throws StationNotFoundException, EmptyStationException, UserNotFoundException, BikeNotFoundException;
    List<Bike> bikesByUser (String userId) throws EmptyUserException, UserNotFoundException;
    Bike findBikeByUser (String bikeId, String userId) throws UserNotFoundException, BikeNotFoundException;
    List<Bike> bikesByStationOrderByKms (String stationId) throws StationNotFoundException, EmptyStationException;

    int userSize();
    int stationSize();
}
