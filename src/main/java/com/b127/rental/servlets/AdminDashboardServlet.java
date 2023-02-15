package com.b127.rental.servlets;

import com.b127.rental.services.VehicleService;
import com.b127.rental.util.ActionBinder;
import com.b127.rental.util.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin")
public class AdminDashboardServlet extends HttpServlet {

    private VehicleService vehicleService;

    public AdminDashboardServlet(){
        this.vehicleService = new VehicleService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ActionBinder.bindActionMessages(req);

        req.setAttribute("vehicles", vehicleService.getAllVehicles());
        getServletContext().getRequestDispatcher("/admin-dashboard.jsp").forward(req, resp);
    }
}
