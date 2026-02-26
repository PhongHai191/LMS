package controller;

import dal.AccountDAL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "AccountDelete", urlPatterns = {"/accountDelete"})
public class AccountDelete extends HttpServlet {


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("accountID");
        int id;
        if (idParam != null) {
            id = Integer.parseInt(idParam);
        } else {
            throw new ServletException("Missing parameter: accountID");
        }

        AccountDAL accountDAL = new AccountDAL();
        accountDAL.deleteAccount(id);

        HttpSession session = request.getSession();
        session.setAttribute("msg", "DELETE SUCCESSFULLY !");

        response.sendRedirect("listAccount");

    }


}
