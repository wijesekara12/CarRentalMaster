package com.b127.rental.modal;

import com.b127.rental.entity.Vehicle;

public class VehicleAndUserDetailsModel {

    private Vehicle vehicle;
    private String name;
    private String email;
    private String mobile;

    public VehicleAndUserDetailsModel(Vehicle vehicle, String name, String email, String mobile) {
        this.vehicle = vehicle;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "VehicleAndUserDetailsModel{" +
                "vehicle=" + vehicle +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
