package controller;

import dal.DashboardDAO;
import model.Account;
import model.Dashboard;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "DashboardController", urlPatterns = {"/DashboardController"})
public class DashboardController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DashboardDAO dashboardDAO = new DashboardDAO();
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");

//        Menu m = new Menu();
//        m.getNotification(request, response);





        if (a.getSystemSetting().getSettingName().equals("Subject Manager")) {
            request.getRequestDispatcher("views/DashboardSM.jsp").forward(request, response);
        } else if (a.getSystemSetting().getSettingName().equals("Trainer")) {
            ArrayList<Dashboard> list = dashboardDAO.getSubjectForTrainer(a);
            request.setAttribute("list", list);
            request.getRequestDispatcher("views/DashboardTrainer.jsp").forward(request, response);
        } else if (a.getSystemSetting().getSettingName().equals("Administrator")) {
            request.getRequestDispatcher("views/DashboardAdmin.jsp").forward(request, response);
        } else if (a.getSystemSetting().getSettingName().equals("User")) {
            ArrayList<Dashboard> list = dashboardDAO.getAll(a);
            request.setAttribute("list", list);
            request.getRequestDispatcher("views/DashboardTrainee.jsp").forward(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
