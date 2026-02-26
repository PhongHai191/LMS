/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountnotclassDAO;
import dal.ClassDAL;
import model.Accountnotclass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@WebServlet(name = "AddTrainee", urlPatterns = {"/AddTrainee"})
public class AddTrainee extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountnotclassDAO accountnotclassDAO = new AccountnotclassDAO();
        ClassDAL cd = new ClassDAL();



        String class_raw = request.getParameter("class");
        String subject_raw = request.getParameter("subject");

        int classSJ = 0, subject = 0;
        try {
            subject = Integer.parseInt(subject_raw);
            classSJ = Integer.parseInt(class_raw);
        } catch (Exception e) {

            System.out.println(e);
        }
        if (cd.getClassByIDASjASem(classSJ, subject) == null) {
            request.getRequestDispatcher("view/error.jsp").forward(request, response);
        } else {
            ArrayList<Accountnotclass> list = accountnotclassDAO.getAl(classSJ, subject);
            request.setAttribute("classSJ", classSJ);
            request.setAttribute("subject", subject);
            request.setAttribute("list", list);
            request.getRequestDispatcher("views/TraineeAdd.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountnotclassDAO accountnotclassDAO = new AccountnotclassDAO();
        ClassDAL cd = new ClassDAL();

        String class_raw = request.getParameter("classSJ");
        String subject_raw = request.getParameter("subject");

        int classSJ = 0, subject = 0;
        try {
            subject = Integer.parseInt(subject_raw);
            classSJ = Integer.parseInt(class_raw);
        } catch (Exception e) {
            System.out.println(e);
        }

        int studentid = Integer.parseInt(request.getParameter("studentid"));

        String isAdd[] = request.getParameterValues("isAdd");

        int semester = cd.getClassByIDASjASem(classSJ, subject).getSemester().getSettingID();;


        ArrayList<model.Class> listClasses = accountnotclassDAO.getClassBySubjectCode(subject);

        LocalDate startdate = LocalDate.of(2024, 1, 1);

        LocalDate enddate = LocalDate.of(2024, 12, 31);

        LocalTime starttime = LocalTime.of(0, 0, 0);

        LocalTime endtime = LocalTime.of(23, 59, 59);

        accountnotclassDAO.add(studentid, 1, semester, 1, startdate, enddate, starttime, endtime);

        response.sendRedirect("TraineeList?class=" + classSJ + "&subject=" + subject);
        //processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}