package com.b127.rental.servlets;

import com.b127.rental.services.VehicleService;
import com.b127.rental.util.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-vehicle")
public class DeleteVehicleServlet extends HttpServlet {

    private VehicleService vehicleService;

    public DeleteVehicleServlet(){
        this.vehicleService = new VehicleService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(vehicleService.deleteVehicle(Long.parseLong(req.getParameter("vehicleId")))) {
            resp.sendRedirect("admin?code=" + ActionMessage.VEHICLE_DELETE_SUCCEED.getId());
        } else {
            resp.sendRedirect("admin?code=" + ActionMessage.VEHICLE_DELETE_FAILED.getId());
        }
    }
}
