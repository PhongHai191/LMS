package controller;

import dal.*;
import dal.ClassDAL;
import model.Class;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ClassController", urlPatterns = {"/classController"})

public class ClassController extends HttpServlet {
    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClassDAL cd = new ClassDAL();
        List<Class> listC = new ArrayList<>();
        SubjectDAL sd = new SubjectDAL();
        try {
            listC = cd.getAllClasses();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        request.setAttribute("sd", sd);

        // Vòng lặp for để lặp qua danh sách các lớp
        for (Class c : listC) {

             System.out.println(c.toString());
        }


        request.setAttribute("listClass", listC);
        System.out.println(listC.size());
        request.getRequestDispatcher("views/listClass.jsp").forward(request, response);
    }

}
