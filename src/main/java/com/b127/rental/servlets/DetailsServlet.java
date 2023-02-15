package com.b127.rental.servlets;

import com.b127.rental.modal.ErrorModel;
import com.b127.rental.modal.VehicleAndUserDetailsModel;
import com.b127.rental.services.DetailsService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/details")
public class DetailsServlet extends HttpServlet {

    private DetailsService detailsService;
    private Gson gson;

    public DetailsServlet() {
        this.detailsService = new DetailsService();
        this.gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        if(req.getParameter("vehicleId") != null && req.getParameter("userId") != null) {
            Optional<VehicleAndUserDetailsModel> optionalModal = detailsService.getVehicleAndUserDetails(Long.parseLong(req.getParameter("vehicleId")),
                    Long.parseLong(req.getParameter("userId")));

            if(optionalModal.isPresent()) {
                String detailsJson = this.gson.toJson( optionalModal.get());

                resp.setStatus(200);
                writer.print(detailsJson);

            } else {
                resp.setStatus(404);
                writer.print(this.gson.toJson(new ErrorModel("404 - Not found", "Failed to load vehicle or user details.")));
            }
        } else {
            resp.setStatus(400);
            writer.print(this.gson.toJson(new ErrorModel("400 - Bad Request", "Vehicle Id or user Id parameters is not provided in request.")));
        }

        writer.flush();
    }
}
