package edu.upc.dsa.models;

public class AddBikeTO {
    String bikeId;
    String stationId;
    String userId;
    double kms;

    public AddBikeTO() {
    }

    public AddBikeTO(String bikeId, String stationId, String userId, double kms) {
        this.bikeId = bikeId;
        this.stationId = stationId;
        this.userId = userId;
        this.kms = kms;
    }

    public String getBikeId() {
        return bikeId;
    }

    public void setBikeId(String bikeId) {
        this.bikeId = bikeId;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getKms() {
        return kms;
    }

    public void setKms(double kms) {
        this.kms = kms;
    }
}
