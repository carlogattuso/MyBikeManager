package edu.upc.dsa.models;

public class UserTO {
    private String id;
    private String name;

    public UserTO() {
    }

    public UserTO(String id, String name) {
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
}
