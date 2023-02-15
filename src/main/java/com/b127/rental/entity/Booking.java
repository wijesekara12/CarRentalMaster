package com.b127.rental.entity;

import java.time.LocalDate;

public class Booking {

    private long id;
    private User bookingOwner;
    private Vehicle bookedVehicle;
    private LocalDate bookedFrom;
    private LocalDate bookedTo;
    private int noOfVehicles;
    private double totalPrice;
    private String bookingState;

    public Booking() {
    }

    public Booking(long bookingId) {
        this.id = bookingId;
    }

    public Booking(long id, User bookingOwner, Vehicle bookedVehicle, LocalDate bookedFrom, LocalDate bookedTo, int noOfVehicles, double totalPrice, String bookingState) {
        this.id = id;
        this.bookingOwner = bookingOwner;
        this.bookedVehicle = bookedVehicle;
        this.bookedFrom = bookedFrom;
        this.bookedTo = bookedTo;
        this.noOfVehicles = noOfVehicles;
        this.totalPrice = totalPrice;
        this.bookingState = bookingState;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getBookingOwner() {
        return bookingOwner;
    }

    public void setBookingOwner(User bookingOwner) {
        this.bookingOwner = bookingOwner;
    }

    public Vehicle getBookedVehicle() {
        return bookedVehicle;
    }

    public void setBookedVehicle(Vehicle bookedVehicle) {
        this.bookedVehicle = bookedVehicle;
    }

    public LocalDate getBookedFrom() {
        return bookedFrom;
    }

    public void setBookedFrom(LocalDate bookedFrom) {
        this.bookedFrom = bookedFrom;
    }

    public LocalDate getBookedTo() {
        return bookedTo;
    }

    public void setBookedTo(LocalDate bookedTo) {
        this.bookedTo = bookedTo;
    }

    public int getNoOfVehicles() {
        return noOfVehicles;
    }

    public void setNoOfVehicles(int noOfVehicles) {
        this.noOfVehicles = noOfVehicles;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getBookingState() {
        return bookingState;
    }

    public void setBookingState(String bookingState) {
        this.bookingState = bookingState;
    }
}
