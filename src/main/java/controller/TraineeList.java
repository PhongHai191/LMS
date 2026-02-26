package controller;

import dal.AccountinclassDAO;
import dal.ClassDAL;
import model.Accountinclass;
import utils.Paging;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "TraineeList", urlPatterns = {"/TraineeList"})
public class TraineeList extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TraineeList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TraineeList at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }




    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Paging p = new Paging();
        ClassDAL cd = new ClassDAL();
        AccountinclassDAO accountinclassDAO = new AccountinclassDAO();
        ArrayList<Accountinclass> list = null;

        String class_raw = request.getParameter("class");
        String subject = request.getParameter("subject");
        String subjectIDParam = request.getParameter("subject");
        int subjectID = 0;

        if (subjectIDParam != null && !subjectIDParam.isEmpty()) {
            try {
                subjectID = Integer.parseInt(subjectIDParam);
            } catch (NumberFormatException e) {
                System.out.println(e);
            }
        }
        int classSJ = 0;

        try {
            classSJ = Integer.parseInt(class_raw);
        } catch (Exception e) {
            System.out.println(e);
        }

        int page = 1;
        if (request.getParameter("page") == null) {
            page = 1;
        } else {
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (Exception e) {
                System.out.println(e);
            }
        }
//        System.out.println(classSJ + " " + subjectID);
        model.Class c = cd.getClassByIDASjASem(classSJ, subjectID);
        if (c != null) {
            list = accountinclassDAO.getAll(classSJ, subjectID);
            if (!list.isEmpty()) {
                int numPerPage = 5;
                int start = p.getStart(page, list.size(), numPerPage);
                int end = p.getEnd(page, list.size(), numPerPage);
                int totalPage = p.getTotalPage(page, list.size(), numPerPage);

                request.setAttribute("page", page);
                request.setAttribute("list", accountinclassDAO.getListByPage(start, end, list));
                request.setAttribute("totalPage", totalPage);

            } else {
                request.setAttribute("ms", "Chưa có học viên nào của lớp này");
            }
            request.setAttribute("c", c);
            request.getRequestDispatcher("views/TraineeList.jsp").forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
