package com.b127.rental.servlets;

import com.b127.rental.entity.User;
import com.b127.rental.services.AuthService;
import com.b127.rental.util.ActionBinder;
import com.b127.rental.util.ActionMessage;
import com.b127.rental.util.UserRoles;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit-profile")
public class EditProfileServlet extends HttpServlet {

    private AuthService authService;

    public EditProfileServlet(){
        this.authService = new AuthService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ActionBinder.bindActionMessages(req);

        String role = (String) req.getSession().getAttribute("role");
        req.setAttribute("user", authService.getUser((long)req.getSession().getAttribute("id")));

        if(role.equals(UserRoles.USER)) {
            getServletContext().getRequestDispatcher("/user-profile.jsp").forward(req, resp);
        } else if(role.equals(UserRoles.TO)) {
            getServletContext().getRequestDispatcher("/to-profile.jsp").forward(req, resp);
        } else if(role.equals(UserRoles.ADMIN)) {
            getServletContext().getRequestDispatcher("/admin-profile.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("logout");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(
                (long)req.getSession().getAttribute("id"),
                req.getParameter("name"),
                req.getParameter("email"),
                req.getParameter("mobile"),
                "",""
        );
        if(authService.editProfile(user)){
            resp.sendRedirect("edit-profile?code=" + ActionMessage.PROFILE_UPDATE_SUCCEED.getId());
        } else {
            resp.sendRedirect("edit-profile?code=" + ActionMessage.PROFILE_UPDATE_FAILED.getId() );
        }
    }
}
