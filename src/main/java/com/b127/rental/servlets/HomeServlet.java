package com.b127.rental.servlets;

import com.b127.rental.services.HomeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("")
public class HomeServlet extends HttpServlet {

    private HomeService homeService;

    public HomeServlet() {
        homeService = new HomeService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("vehicles", homeService.getVehiclesRandomlyForHomeScreen());
        getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);
    }
}
