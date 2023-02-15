package com.b127.rental.modal;

import java.time.LocalDate;

public class BookingModel {

    private long bookingId;
    private LocalDate bookedFrom;
    private LocalDate bookedTo;
    private String regNo;
    private double price;
    private String state;

    public BookingModel(long bookingId, LocalDate bookedFrom, LocalDate bookedTo, String regNo, double price, String state) {
        this.bookingId = bookingId;
        this.bookedFrom = bookedFrom;
        this.bookedTo = bookedTo;
        this.regNo = regNo;
        this.price = price;
        this.state = state;
    }

    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
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

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
