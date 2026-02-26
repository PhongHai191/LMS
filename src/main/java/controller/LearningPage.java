package controller;

import dal.*;
import model.Account;
import model.Chapter;
import model.LessonIChapter;
import utils.ValidInput;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
@WebServlet(name = "LearningPage", urlPatterns = {"/learningPage"})
public class LearningPage extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out = response.getWriter()){
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LearningPage</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LearningPage at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }





    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("subject") == null
                || request.getParameter("class") == null
                || request.getParameter("subject").equals("")
                || request.getParameter("class").equals("")) {
            response.sendRedirect("view/error.jsp");
        }  else{
            HttpSession session = request.getSession();
            Account a = (Account) session.getAttribute("account");
            ValidInput v = new ValidInput();
            SubjectDAL sd = new SubjectDAL();
            SubjectSettingDAL ssd = new SubjectSettingDAL();
            ClassDAL cd = new ClassDAL();
            int subject = v.validInt(request.getParameter("subject"), request, response);
            int cl = v.validInt(request.getParameter("class"), request, response);
            List<Chapter> listC = ssd.getAllChapterFromClassAndSubject(subject);
            List<LessonIChapter> listLIC = new ArrayList<>();
            for (int i = 0; i < listC.size(); i++) {

                listLIC.add(new LessonIChapter(listC.get(i), ssd.getAllLessonByChapter(listC.get(i).getChapterID(), cl)));
            }
            AccountDAL ad = new AccountDAL();
            if(cd.getClassByID(cl) != null) {
                request.setAttribute("cin4", cd.getClassByID(cl));
                request.setAttribute("listAcc", ad.getAllTraineeInClass(cl));
            }

            request.setAttribute("cls", cl);
            request.setAttribute("list", listLIC);
            request.setAttribute("subject", subject);
            request.setAttribute("cl", cl);
            Date now = Date.valueOf((Calendar.getInstance().getTime().getYear() + 1900) + "-" + (Calendar.getInstance().getTime().getMonth() + 1) + "-" + Calendar.getInstance().getTime().getDate());
            List<model.Class> listCL = cd.getAllClassThatTraineeIn(a.getAccountID(), now);
            request.setAttribute("listCL", listCL);
            request.getRequestDispatcher("views/LearningPage.jsp").forward(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
