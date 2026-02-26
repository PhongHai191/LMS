package controller;

import dal.AccountDAL;
import model.Account;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListAccount", urlPatterns = {"/listAccount"})

public class AccountList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountDAL accountDAL = new AccountDAL();



        int pageNumber = 1;
        int pageSize = 5;
        String pageNumberParam = request.getParameter("pageNumber");
        if (pageNumberParam != null) {
            pageNumber = Integer.parseInt(pageNumberParam);
        }

        String searchUsername = request.getParameter("searchUsername");
        List<Account> listAccount;
        if (searchUsername != null && !searchUsername.isEmpty()) {
            listAccount = accountDAL.searchAccount(searchUsername);
        } else {
            listAccount = accountDAL.getAccounts(pageNumber, pageSize);
        }

        int totalAccounts = accountDAL.getTotalAccount();
        int totalPage = (int) Math.ceil((double) totalAccounts / pageSize);
        request.setAttribute("listAccount", listAccount);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("currentPage", pageNumber);

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/listAccount.jsp");
        dispatcher.forward(request, response);
    }
}
