package com.b127.rental.servlets;

import com.b127.rental.services.PaymentService;
import com.b127.rental.util.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-payment")
public class AddPaymentServlet extends HttpServlet {

    private PaymentService paymentService;

    public AddPaymentServlet(){
        this.paymentService = new PaymentService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(paymentService.addPayment(
                req.getParameter("cardNo"),
                req.getParameter("type"),
                req.getParameter("expMonth"),
                req.getParameter("expYear"),
                req.getParameter("cvv"),
                (long)req.getSession().getAttribute("id"),
                Long.parseLong(req.getParameter("bookingId"))
        )) {
            resp.sendRedirect("get-bookings?code=" + ActionMessage.PAYMENT_SUCCEED.getId());
        } else {
            resp.sendRedirect("get-bookings?code=" + ActionMessage.PAYMENT_FAILED.getId());
        }
    }
}
