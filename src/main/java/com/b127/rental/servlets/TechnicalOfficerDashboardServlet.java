package com.b127.rental.servlets;

import com.b127.rental.services.BookingService;
import com.b127.rental.util.ActionBinder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/to")
public class TechnicalOfficerDashboardServlet extends HttpServlet {

    private BookingService bookingService;

    public TechnicalOfficerDashboardServlet(){
        this.bookingService = new BookingService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ActionBinder.bindActionMessages(req);
        
        req.setAttribute("bookings", bookingService.getPendingBookings());
        getServletContext().getRequestDispatcher("/to-dashboard.jsp").forward(req, resp);
    }
}
