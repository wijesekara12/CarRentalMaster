package com.b127.rental.servlets;

import com.b127.rental.services.BookingService;
import com.b127.rental.util.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/add-booking")
public class AddBookingServlet extends HttpServlet {

    private BookingService bookingService;

    public AddBookingServlet() {
        this.bookingService = new BookingService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(bookingService.saveBooking(
                LocalDate.parse(req.getParameter("bookedFrom")),
                LocalDate.parse(req.getParameter("bookedTo")),
                (long)req.getSession().getAttribute("id"),
                Long.parseLong(req.getParameter("vehicleId"))
        )){
            resp.sendRedirect("get-bookings?code=" + ActionMessage.BOOKING_ADD_SUCCEED.getId());
        } else {
            resp.sendRedirect("user?code=" + ActionMessage.BOOKING_ADD_FAILED.getId());
        }
    }

}
