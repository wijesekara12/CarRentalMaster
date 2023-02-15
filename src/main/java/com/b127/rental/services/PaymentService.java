package com.b127.rental.services;

import com.b127.rental.dao.BookingDao;
import com.b127.rental.dao.PaymentDao;
import com.b127.rental.dao.impl.BookingDaoImpl;
import com.b127.rental.dao.impl.PaymentDaoImpl;
import com.b127.rental.entity.Booking;
import com.b127.rental.entity.Payment;
import com.b127.rental.entity.User;
import com.b127.rental.util.BookingStates;

public class PaymentService extends AbstractService {

    private PaymentDao paymentDao;
    private BookingDao bookingDao;

    public PaymentService(){
        this.paymentDao = new PaymentDaoImpl();
        this.bookingDao = new BookingDaoImpl();
    }

    public boolean addPayment(String cardNo, String type, String expirationMonth, String expirationYear, String cvv, long userId, long bookingId) {
        Payment payment = new Payment(
                0,
                cardNo,
                type,
                cvv,
                expirationMonth + "/" + expirationYear,
                new User(userId),
                bookingDao.getTotalPriceByBookingId(bookingId),
                new Booking(bookingId)
        );

        return paymentDao.save(payment) && bookingDao.updateBookingsState(bookingId, BookingStates.PAID);
    }

}
