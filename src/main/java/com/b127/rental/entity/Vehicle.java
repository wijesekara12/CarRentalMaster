package com.b127.rental.entity;

public class Vehicle {

    private long id;
    private String name;
    private String registrationNumber;
    private int capacity;
    private double price;
    private String image;
    private String specifications;

    public Vehicle(){}

    public Vehicle(long id){
        this.id = id;
    }

    public Vehicle(long id, String name, String registrationNumber, int capacity, double price, String image, String specifications) {
        this.id = id;
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.capacity = capacity;
        this.price = price;
        this.specifications = specifications;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", capacity=" + capacity +
                ", price=" + price +
                ", specifications='" + specifications + '\'' +
                '}';
    }
}

