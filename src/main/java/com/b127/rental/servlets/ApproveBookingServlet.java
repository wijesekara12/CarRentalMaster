package com.b127.rental.servlets;

import com.b127.rental.services.BookingService;
import com.b127.rental.util.ActionMessage;
import com.b127.rental.util.BookingStates;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/approve-booking")
public class ApproveBookingServlet extends HttpServlet {

    private BookingService bookingService;

    public ApproveBookingServlet(){
        this.bookingService = new BookingService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(bookingService.updateBookingState(Long.parseLong(req.getParameter("bookingId")),BookingStates.APPROVED)){
            resp.sendRedirect("to?code=" + ActionMessage.BOOKING_STATE_UPDATED.getId());
        } else {
            resp.sendRedirect("to?code=" + ActionMessage.BOOKING_STATE_UPDATING_FAILED.getId());
        }
    }
}
