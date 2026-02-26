package controller;

import dal.SubjectDAL;
import model.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SubjectController", urlPatterns = {"/subjectController"})

public class SubjectController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SubjectDAL sd = new SubjectDAL();
        List<Subject> listS = new ArrayList<>();

        try {
            listS = sd.getAllSubjects();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        request.setAttribute("listS", listS);
        request.getRequestDispatcher("views/listSubject.jsp").forward(request,response);
    }



}