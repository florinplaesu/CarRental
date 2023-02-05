package com.example.carrental.Model;

public class Location {
    private String city;
    private int postcode;

    public Location() {}
    public Location(String city){}

    public Location(String city, int postcode) {
        this.city = city;
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }
}

