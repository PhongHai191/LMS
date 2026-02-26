package controller;

import dal.AccountDAL;
import model.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegistrationController", urlPatterns = {"/registration"})
public class RegistrationController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullname");
        String mobile = request.getParameter("mobile");
        String description = request.getParameter("description");
        String confirmPassword = request.getParameter("confirm_password");


        String allowedEmailPattern = "^[a-zA-Z]{3,10}he17\\d{4}@fpt.edu.vn$";

        if (!email.matches(allowedEmailPattern)) {
            request.setAttribute("Error", "Email is not allowed to register");
            request.getRequestDispatcher("views/Registration.jsp").forward(request, response);
            return;


        }

        AccountDAL ad = new AccountDAL();
        Account existingAccount = ad.getAccountByEmail(email);

        if (existingAccount != null) {
            request.setAttribute("Error", "Account already exists. Please Login");
            request.getRequestDispatcher("views/Login.jsp").forward(request, response);
            return;
        }

        if (!confirmPassword.equals(password)) {
            request.setAttribute("Error", "Confirm password does not match. Try again!");
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.setAttribute("mobile", mobile);
            request.setAttribute("description", description);
            request.getRequestDispatcher("views/Registration.jsp").forward(request, response);
            return;
        }

        ad.add(username, email, password, fullName, mobile, description);
        request.setAttribute("Error", "Register account successfully");
        request.getRequestDispatcher("views/Login.jsp").forward(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("views/Registration.jsp").forward(request, response);
    }
}
