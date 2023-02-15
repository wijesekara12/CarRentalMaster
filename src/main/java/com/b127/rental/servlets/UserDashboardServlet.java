package com.b127.rental.servlets;

import com.b127.rental.entity.Vehicle;
import com.b127.rental.services.UserDashboardService;
import com.b127.rental.util.ActionBinder;
import com.b127.rental.util.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/user")
public class UserDashboardServlet extends HttpServlet {

    private UserDashboardService userDashboardService;

    public UserDashboardServlet(){
        userDashboardService = new UserDashboardService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ActionBinder.bindActionMessages(req);

        req.setAttribute("vehicles", userDashboardService.getAllVehicles());
        getServletContext().getRequestDispatcher("/user-dashboard.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Vehicle> filteredVehicles = userDashboardService.searchVehicles(LocalDate.parse(req.getParameter("from")),
                LocalDate.parse(req.getParameter("to")),
                Integer.parseInt(req.getParameter("capacity")));

        req.setAttribute("vehicles", filteredVehicles);
        if(filteredVehicles.size() == 0) req.setAttribute("error", ActionMessage.NO_VEHICLE_AVAILABLE.getMessage());
        getServletContext().getRequestDispatcher("/user-dashboard.jsp").forward(req, resp);
    }
}
