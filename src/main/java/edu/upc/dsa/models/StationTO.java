package edu.upc.dsa.models;

public class StationTO {
    private String id;
    private String place;

    public StationTO() {
    }

    public StationTO(String id, String place) {
        this.id = id;
        this.place = place;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
