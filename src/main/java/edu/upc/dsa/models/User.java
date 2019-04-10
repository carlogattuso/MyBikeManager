package edu.upc.dsa.models;

import java.util.LinkedList;
import java.util.List;

public class User {
    private String id;
    private String name;
    private List<Bike> bikeList = new LinkedList<>();

    public User() {
    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void removeBike(Bike b){
        this.bikeList.remove(b);
    }

    public String toString() {
        return "User [id="+id+", name=" + name + ", BikeListSize=" + bikeList.size() +"]";
    }
}
