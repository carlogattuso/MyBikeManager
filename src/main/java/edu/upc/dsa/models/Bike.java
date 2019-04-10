package edu.upc.dsa.models;

import java.util.LinkedList;
import java.util.List;

public class Bike {
    private String id;
    private String model;
    private String stationId;
    private double kms;

    public Bike() {
    }

    public Bike(String id, String model, double kms) {
        this.id = id;
        this.model = model;
        this.kms = kms;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getKms() {
        return kms;
    }

    public void setKms(double kms) {
        this.kms = kms;
    }

    public String toString() {
        return "Bike [id="+id+", model=" + model +"]";
    }
}
