package controller;

import dal.AccountDAL;
import model.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "AccountDetail", urlPatterns = {"/accountDetail"})
public class AccountDetail extends HttpServlet {
    @Override



    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("accountID");
        int id;
        if (idParam != null) {
            id = Integer.parseInt(idParam);
        } else {
            throw new ServletException("Missing parameter: accountID");
        }


        AccountDAL accountDAL = new AccountDAL();
        Account account = accountDAL.getAccountByID(id);

        request.setAttribute("account", account);
        request.getRequestDispatcher("views/AccountDetails.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("accountID");
        int id;
        if (idParam != null) {
            id = Integer.parseInt(idParam);
        } else {
            throw new ServletException("Missing parameter: accountID");
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        String roleParam = request.getParameter("role");
        int role;
        if (roleParam != null && roleParam.matches("\\d+")) {
            role = Integer.parseInt(roleParam);
        } else {
            throw new ServletException("Invalid or missing parameter: role");
        }

        String statusParam = request.getParameter("status");
        int status;
        if (statusParam != null && statusParam.matches("\\d+")) {
            status = Integer.parseInt(statusParam);
        } else {
            throw new ServletException("Invalid or missing parameter: status");
        }

        String fullName = request.getParameter("fullName");
        String mobile = request.getParameter("mobile");
        String dob = request.getParameter("dob");
        String description = request.getParameter("description");

        AccountDAL accountDAL = new AccountDAL();
        boolean isUpdated = accountDAL.updateAccount(id, username, password, email, role, status, fullName, mobile, dob, description);

        if (isUpdated) {
            response.sendRedirect("listAccount");
        } else {
            request.setAttribute("errorMessage", "Could not update account");
            request.getRequestDispatcher("views/AccountDetails.jsp").forward(request, response);
        }

        HttpSession session = request.getSession();
        session.setAttribute("msg", "UPDATE SUCCESSFULLY !");


    }


}
