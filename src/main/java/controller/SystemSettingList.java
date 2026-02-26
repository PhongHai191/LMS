package controller;

import dal.SystemSettingDAL;
import model.SystemSetting;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListSystemSetting", urlPatterns = {"/listSystemSetting"})
public class SystemSettingList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SystemSettingDAL systemSettingDAL = new SystemSettingDAL();

        int pageNumber = 1;
        int pageSize = 5;
        String pageNumberParam = request.getParameter("pageNumber");
        if (pageNumberParam != null) {
            pageNumber = Integer.parseInt(pageNumberParam);
        }


        List<SystemSetting> listSystemSetting = systemSettingDAL.getSettings(pageNumber, pageSize);
        int totalSettings = systemSettingDAL.getTotalSetting();
        int totalPage = (int) Math.ceil((double) totalSettings / pageSize);


        request.setAttribute("listSystemSetting", listSystemSetting);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("currentPage", pageNumber);

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/SystemSettingList.jsp");
        dispatcher.forward(request, response);
    }
}
