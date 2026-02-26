package controller;

import dal.SubjectDAL;
import model.Account;
import model.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DetailSubjectController", urlPatterns = {"/DetailSubject"})

public class DetailSubjectController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SubjectDAL sd = new SubjectDAL();
        Subject sFound = null;
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            sFound = sd.getById(id);
            request.setAttribute("subject", sFound);
        }catch(Exception e){
            response.sendRedirect("subjectController");
        }
        request.getRequestDispatcher("views/DetailSubject.jsp").forward(request, response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String subjectCode = request.getParameter("subjectCode");
        String subjectName = request.getParameter("subjectName");
        String subjectDescription = request.getParameter("subjectDescription");
        String status = request.getParameter("status");
        int displayOrder = Integer.parseInt(request.getParameter("displayOrder"));

//        Account a = (Account) request.getSession().getAttribute("Account");

        Account aTest = new Account();
        aTest.setAccountID(1);

        Subject s = new Subject();
        s.setStatus(Integer.parseInt(status));
        s.setSubjectCode(subjectCode);
        s.setSubjectName(subjectName);
        s.setSubjectDescription(subjectDescription);
        s.setManager(aTest);
        s.setDisplayOrder(displayOrder);

        SubjectDAL sd = new SubjectDAL();
        try {
            sd.create(s);
            response.sendRedirect("subjectController");
        } catch (SQLException e) {
            request.setAttribute("messError", "Subject code does exist!");
            request.getRequestDispatcher("views/AddSubject.jsp").forward(request, response);
        }
    }

}