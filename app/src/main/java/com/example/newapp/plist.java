package com.example.newapp;

public class plist {

    String placeid;
    String address;
    String  price;
    public plist(){

    }

    public plist(String placeid, String address, String price) {
        this.placeid = placeid;
        this.address = address;
        this.price = price;
    }

    public String getPlaceid() {
        return placeid;
    }

    public String getAddress() {
        return address;
    }

    public String getPrice() {
        return price;
    }
}


