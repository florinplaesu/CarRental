package com.example.carrental.Car;

public class Car {

    private String mark, model;
    private int price;
    private String city;
    //private int iconId;

    public Car(){}

    public Car(String mark, String model, int price, String city) {
        this.mark=mark;
        this.model=model;
        this.price=price;
        this.city=city;
    }
    //public Car(String mark, String model, int price, String city){}

    public String getMark() {
        return mark;
    }

    public String getModel() {
        return model;
    }

    public int getPrice() {
        return price;
    }

    public String getCity() { return city; }
    //public int getIconId() { return iconId; }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCity(String city) { this.city = city; }
}
