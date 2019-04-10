package edu.upc.dsa.models;

import java.util.LinkedList;
import java.util.List;

public class Station {
    private String id;
    private String place;
    private List<Bike> bikeList = new LinkedList<>();

    public Station() {
    }

    public Station(String id, String place) {
        this.id = id;
        this.place = place;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public List<Bike> getBikeList() {
        return bikeList;
    }

    public void setBikeList(List<Bike> bikeList) {
        this.bikeList = bikeList;
    }

    public void addBike(Bike b){
        this.bikeList.add(b);
    }

    public String toString() {
        return "Station [id="+id+", place=" + place + ", bikeListSize=" + bikeList.size() +"]";
    }
}
