package controller;

import dal.AccountinclassDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TraineeDelete", urlPatterns = {"/TraineeDelete"})
public class TraineeDelete extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userid = Integer.parseInt(request.getParameter("userid"));
        int class_id = Integer.parseInt(request.getParameter("class"));
        AccountinclassDAO accountinclassDAO = new AccountinclassDAO();
        accountinclassDAO.delete(userid, class_id);
        String class_raw = request.getParameter("class");
        String subject = request.getParameter("subject");

//        int classSJ = 0;
//
//        try {
//            classSJ = Integer.parseInt(class_raw);
//        } catch (Exception e) {
//            System.out.println(e);
//        }



        response.sendRedirect("TraineeList?subject="+subject+"&class="+class_raw);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userid = Integer.parseInt(request.getParameter("userid"));
        int class_id = Integer.parseInt(request.getParameter("class"));
        String subject = request.getParameter("subject");
        AccountinclassDAO accountinclassDAO = new AccountinclassDAO();
        accountinclassDAO.delete(userid, class_id);
        response.sendRedirect("TraineeList");


    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}