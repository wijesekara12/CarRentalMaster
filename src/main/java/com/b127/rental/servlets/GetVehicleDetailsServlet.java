package com.b127.rental.servlets;

import com.b127.rental.modal.ErrorModel;
import com.b127.rental.services.VehicleService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/get-vehicle")
public class GetVehicleDetailsServlet extends HttpServlet {

    private VehicleService vehicleService;
    private Gson gson;

    public GetVehicleDetailsServlet(){
        this.vehicleService = new VehicleService();
        this.gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        if(req.getParameter("vehicleId") != null) {
            String vehicleJson = this.gson.toJson(vehicleService.getVehicleById(Long.parseLong(req.getParameter("vehicleId"))));
            resp.setStatus(200);
            writer.print(vehicleJson);
        } else {
            resp.setStatus(404);
            writer.print(this.gson.toJson(new ErrorModel("404 - Not found", "Failed to load vehicle or user details.")));
        }
    }
}
