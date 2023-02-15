package com.b127.rental.servlets;

import com.b127.rental.entity.Vehicle;
import com.b127.rental.services.VehicleService;
import com.b127.rental.util.ActionBinder;
import com.b127.rental.util.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-vehicle")
public class AddVehicleServlet extends HttpServlet {

    private VehicleService vehicleService;

    public AddVehicleServlet(){
        vehicleService = new VehicleService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ActionBinder.bindActionMessages(req);
        getServletContext().getRequestDispatcher("/add-vehicle.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Vehicle vehicle = new Vehicle(
                0,
                req.getParameter("name"),
                req.getParameter("regNo"),
                Integer.parseInt(req.getParameter("capacity")),
                Double.parseDouble(req.getParameter("price")),
                "img/vehicles/car_3.jpg",
                req.getParameter("specs")
        );

        if(vehicleService.addVehicle(vehicle)) {
            resp.sendRedirect("admin?code=" + ActionMessage.VEHICLE_ADD_SUCCEED.getId());
        } else {
            resp.sendRedirect("add-vehicle?code=" + ActionMessage.VEHICLE_ADD_FAILED.getId());
        }
    }
}
