package com.b127.rental.dao;

import com.b127.rental.entity.Booking;
import com.b127.rental.modal.BookingModel;

import java.util.List;

public interface BookingDao extends SuperDao<Booking> {

    List<BookingModel> getBookingsByUserId(long userId);

    boolean updateBookingsState(long bookingId, String state);

    double getTotalPriceByBookingId(long bookingId);

    List<BookingModel> getBookingsByState(String state);
}
