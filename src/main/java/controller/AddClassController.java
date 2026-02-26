package controller;

import dal.ClassDAL;
import dal.SubjectDAL;
import dal.SystemSettingDAL;
import model.Account;
import model.Subject;
import model.SystemSetting;
import model.Class;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

@WebServlet(name = "AddClassController", urlPatterns = {"/AddClass"})

public class AddClassController
        extends HttpServlet {
    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SubjectDAL sd = new SubjectDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        try {
            request.setAttribute("listS", sd.getAllSubjects());
            request.setAttribute("Selist", ssd.getSettingByType("Semester"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("views/AddClass.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameter values from the request
        String className = request.getParameter("className");
        String subjectId = request.getParameter("subjectId");
        String semester = request.getParameter("semester");
        String status = request.getParameter("status");
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String trainer = request.getParameter("trainer");
        String displayOrderStr = request.getParameter("displayOrder");
        String description = request.getParameter("description");

        // sua user -> ten luu tren session
        Account a = (Account) request.getSession().getAttribute("account");

        SystemSettingDAL sd = new SystemSettingDAL();
        SystemSetting ss = sd.getSettingByID(Integer.parseInt(semester));
        SubjectDAL sjd = new SubjectDAL();

// Convert appropriate data types and set them in the Class object
        Class c = new Class();
        c.setClassName(className);
        try {
            Subject sj = sjd.getById(Integer.parseInt(subjectId));
            c.setS(sj);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        c.setSemester(ss);
        c.setActivate(Integer.parseInt(status));
        c.setTrainer(a);
        c.setDescription(description);
        c.setDisplayOrder(Integer.parseInt(displayOrderStr));
        c.setStartDate(Date.valueOf(startDateStr));
        c.setEndDate(Date.valueOf(endDateStr));
        c.setStartTime(Time.valueOf(startTime + ":00"));
        c.setEndTime(Time.valueOf(endTime + ":00"));
        ClassDAL cd = new ClassDAL();
        try {
            cd.create(c);
            response.sendRedirect("classController");
        } catch (SQLException e) {
            request.setAttribute("messError", "Subject code does exist!");
            request.getRequestDispatcher("views/AddClass.jsp").forward(request, response);
        }
    }

}