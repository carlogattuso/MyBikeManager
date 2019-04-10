package edu.upc.dsa.models;

public class GetBikeTO {
    String bikeId;
    String stationId;
    String userId;

    public GetBikeTO() {
    }

    public GetBikeTO(String bikeId, String stationId, String userId) {
        this.bikeId = bikeId;
        this.stationId = stationId;
        this.userId = userId;
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
}
