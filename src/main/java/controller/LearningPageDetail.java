package controller;

import dal.*;
import model.*;
import utils.ValidInput;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LearningPageDetail", urlPatterns = {"/learn"})
public class LearningPageDetail extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
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
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        if (request.getParameter("subject") == null
                || request.getParameter("class") == null
                || request.getParameter("subject").equals("")
                || request.getParameter("class").equals("")) {
            response.sendRedirect("view/error.jsp");
        } else {
            HttpSession session = request.getSession();
            Account a = (Account) session.getAttribute("account");
            AsmDAL ad = new AsmDAL();
            ValidInput v = new ValidInput();
            SubjectSettingDAL ssd = new SubjectSettingDAL();
            ClassDAL cd = new ClassDAL();
            int subject = v.validInt(request.getParameter("subject"), request, response);
            int cl = v.validInt(request.getParameter("class"), request, response);
            List<Chapter> listC = ssd.getAllChapterFromClassAndSubject(subject);
            List<LessonIChapter> listLIC = new ArrayList<>();
            for (int i = 0; i < listC.size(); i++) {
                listLIC.add(new LessonIChapter(listC.get(i), ssd.getAllLessonByChapter(listC.get(i).getChapterID(), cl)));
            }
            request.setAttribute("list", listLIC);
            request.setAttribute("subject", subject);
            request.setAttribute("cl", cl);
            request.setAttribute("trainer", cd.getTrainerByClassID(cl));
            if (request.getParameter("lesson") == null || request.getParameter("lesson").equals("")) {
                request.setAttribute("lesson", 1);
                request.setAttribute("video", ssd.getLessonByID(1).getVideoLink());
                request.setAttribute("des", ssd.getLessonByID(1).getDescription());
                request.setAttribute("chapter", ssd.getChapterByID(1).getChapterID());
            } else {
                int lesson = 0;
                try {
                    lesson = Integer.parseInt(request.getParameter("lesson"));
                    if (ssd.getLessonByID(lesson) == null) {
                        throw new Exception("L");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                    response.sendRedirect("views/error.jsp");
                }
                request.setAttribute("lesson", lesson);
                request.setAttribute("chapter", ssd.getLessonByID(lesson).getChapter().getChapterID());
                if(ssd.getLessonByID(lesson).getVideoLink() == null){
                    request.setAttribute("asm", ad.getAsmByID(ssd.getLessonByID(lesson).getAsm().getAsmID()));
                }
                if (ssd.getLessonByID(lesson).getVideoLink() != null) {
                    request.setAttribute("video", ssd.getLessonByID(lesson).getVideoLink());
                    request.setAttribute("videoID", getVideoID(ssd.getLessonByID(lesson).getVideoLink()));
                }
            }
            request.getRequestDispatcher("views/LearningPageDetail.jsp").forward(request, response);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        try {
            String submit = request.getParameter("editordata");
            int asmID = Integer.parseInt(request.getParameter("asmID"));
            int subjectID = Integer.parseInt(request.getParameter("subjectID"));
            int classID = Integer.parseInt(request.getParameter("class"));
            Assignment asm = new Assignment();
            asm.setAsmID(asmID);
            ssd.submitAssignment(asmID,a.getAccountID(),submit);
            response.sendRedirect("learningPage?subject="+subjectID+"&class="+classID);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    protected String getVideoID(String videoLink) {
        String videoID = "";
        if (videoLink.contains("embed")) {
            for (int i = 0; i < videoLink.length(); i++) {
                if (i + 6 < videoLink.length()) {
                    if (videoLink.substring(i, i + 6).equals("embed/")) {

                        for (int j = 0; j < videoLink.substring(i + 6).length(); j++) {
                            if (videoLink.substring(i + 6).charAt(j) == '?') {
                                videoID = videoLink.substring(i + 6, i + 6 + j);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return videoID;
    }
}
