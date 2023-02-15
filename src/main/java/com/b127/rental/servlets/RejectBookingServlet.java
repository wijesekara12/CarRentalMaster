package com.b127.rental.servlets;

import com.b127.rental.services.BookingService;
import com.b127.rental.util.ActionMessage;
import com.b127.rental.util.BookingStates;
import com.b127.rental.util.UserRoles;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/reject-booking")
public class RejectBookingServlet extends HttpServlet {

    private BookingService bookingService;

    public RejectBookingServlet(){
        this.bookingService = new BookingService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String role = (String)req.getSession().getAttribute("role");

        if(bookingService.updateBookingState(Long.parseLong(req.getParameter("bookingId")), BookingStates.CANCELLED)){
            resp.sendRedirect(getPathByRole(role) + "?code=" + ActionMessage.BOOKING_STATE_UPDATED.getId());

        } else {
            resp.sendRedirect(getPathByRole(role) + "?code=" + ActionMessage.BOOKING_STATE_UPDATING_FAILED.getId());
        }
    }

    private String getPathByRole(String role) {
        String path = "";
        switch (role) {
            case UserRoles.TO:
                path = "to";
                break;

            case UserRoles.USER:
                path = "get-bookings";
                break;
        }
        return path;
    }
}
