package com.b127.rental.services;

import com.b127.rental.dao.BookingDao;
import com.b127.rental.dao.VehicleDao;
import com.b127.rental.dao.impl.BookingDaoImpl;
import com.b127.rental.dao.impl.VehicleDaoImpl;
import com.b127.rental.entity.Booking;
import com.b127.rental.entity.User;
import com.b127.rental.entity.Vehicle;
import com.b127.rental.modal.BookingModel;
import com.b127.rental.util.BookingStates;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

public class BookingService extends AbstractService{

    private BookingDao bookingDao;
    private VehicleDao vehicleDao;

    public BookingService(){
        this.bookingDao = new BookingDaoImpl();
        this.vehicleDao = new VehicleDaoImpl();
    }

    public boolean saveBooking(LocalDate bookedFrom, LocalDate bookedTo, long userId, long vehicleId) {

        double perDayPrice = vehicleDao.getVehiclePerDayPriceById(vehicleId);
        long days = ChronoUnit.DAYS.between(bookedFrom, bookedTo) + 1;

        Booking booking = new Booking(
                0,
                new User(userId),
                new Vehicle(vehicleId),
                bookedFrom,
                bookedTo,
                1,
                perDayPrice*days,
                BookingStates.PENDING
        );
        return bookingDao.save(booking);
    }

    public List<BookingModel> getBookingsByCustomerId(long customerId) {
        return bookingDao.getBookingsByUserId(customerId);
    }

    public List<BookingModel> getPendingBookings() {
        return bookingDao.getBookingsByState(BookingStates.PENDING);
    }

    public boolean updateBookingState(long bookingId, String bookingState) {
        Optional<Booking> optionalBooking = bookingDao.getById(bookingId);
        if(optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            if(booking.getBookingState().equals(BookingStates.CANCELLED)) booking.setBookingState(BookingStates.CANCELLED);
        };
        return bookingDao.updateBookingsState(bookingId, bookingState);
    }


}
