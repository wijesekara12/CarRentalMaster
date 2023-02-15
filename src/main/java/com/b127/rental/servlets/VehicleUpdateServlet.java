package com.b127.rental.servlets;

import com.b127.rental.entity.Vehicle;
import com.b127.rental.services.VehicleService;
import com.b127.rental.util.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update-vehicle")
public class VehicleUpdateServlet extends HttpServlet {

    private VehicleService vehicleService;

    public VehicleUpdateServlet(){
        this.vehicleService = new VehicleService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Vehicle vehicle = new Vehicle(
                0,
                req.getParameter("name"),
                req.getParameter("regNo"),
                Integer.parseInt(req.getParameter("capacity")),
                Double.parseDouble(req.getParameter("price")),
                "",
                req.getParameter("specs")
        );

        if(vehicleService.updateVehicle(vehicle, Long.parseLong(req.getParameter("vehicleId")))) {
            resp.sendRedirect("admin?code="+ ActionMessage.VEHICLE_UPDATE_SUCCEED.getId());
        } else {
            resp.sendRedirect("admin?code="+ ActionMessage.VEHICLE_UPDATE_FAILED.getId());
        }
    }
}
