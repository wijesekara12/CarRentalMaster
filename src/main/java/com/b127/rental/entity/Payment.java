package com.b127.rental.entity;

import java.time.LocalDateTime;

public class Payment {

    private long id;
    private String cardNo;
    private String cardType;
    private String cvv;
    private String expiration;
    private User user;
    private LocalDateTime time;
    private double amount;
    private Booking booking;

    public Payment(){}

    public Payment(long id, String cardNo, String cardType, String cvv, String expiration, User user, double amount, Booking booking) {
        this.id = id;
        this.cardNo = cardNo;
        this.cardType = cardType;
        this.cvv = cvv;
        this.expiration = expiration;
        this.user = user;
        this.amount = amount;
        this.booking = booking;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
