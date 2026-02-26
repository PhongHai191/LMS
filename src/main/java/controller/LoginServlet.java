package controller;


import dal.AccountDAL;
import model.Account;
import utils.Encrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String u = request.getParameter("username");
        String p = request.getParameter("password");
        String allowedEmailPattern = "^[a-zA-Z]{3,10}he17\\d{4}@fpt.edu.vn$";
        if (u.matches(allowedEmailPattern)) {
            AccountDAL ad = new AccountDAL();
            Encrypt e = new Encrypt();
            String pass = e.encrypt(p);
            Account a = ad.check(u, pass);
            HttpSession session = request.getSession();
            if (a == null) {
                request.setAttribute("username", u);
                request.setAttribute("password", p);
                request.setAttribute("Error", "Username or Password Invalid!!");
                request.getRequestDispatcher("views/Login.jsp").forward(request, response);
            } else {
                session.setAttribute("account", a);

                if (a.getSystemSetting().getSettingName().equals("User")) {
                    response.sendRedirect("DashboardController");
                } else {

                    response.sendRedirect("DashboardController");
                }
            }



        } else {
            request.setAttribute("username", u);
            request.setAttribute("password", p);
            request.setAttribute("Error", "Email is not allowed to login");
            request.getRequestDispatcher("views/Login.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("views/Login.jsp").forward(request, response);
    }
}
