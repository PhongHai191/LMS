package controller;

import dal.UserDAO;
import model.Account;
import utils.Encrypt;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ChangePassword", urlPatterns = {"/ChangePassword"})
public class ChangePasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        String username = a.getUsername();
        request.setAttribute("username", username);
        request.getRequestDispatcher("views/ChangePassword.jsp").forward(request, response);
    }




    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            UserDAO util = new UserDAO();
            Encrypt e = new Encrypt();
            String username = request.getParameter("username");
            request.setAttribute("username", username);
            Account account = util.getAccountDetails(username);
            String oPassword = e.encrypt(request.getParameter("old_password"));
            String cPassword = account.getPassword();
            String nPassword = e.encrypt(request.getParameter("new_password"));
            String nPassword_confirm = e.encrypt(request.getParameter("confirm_new_password"));

            if (oPassword == null || oPassword.isEmpty()
                    || nPassword == null || nPassword.isEmpty()
                    || nPassword_confirm == null || nPassword_confirm.isEmpty()) {
                request.setAttribute("message", "Vui lòng không để trống bất kỳ trường nào.");
            } else {
                if (oPassword.equals(cPassword)) {
                    if (nPassword.equals(nPassword_confirm)) {
                        if (util.updateAccountPassword(nPassword, username)) {
                            request.setAttribute("message", "Mật khẩu của bạn đã được thay đổi thành công!");
//                            util.EmailChangePassSender(account.getEmail());
                        } else {
                            request.setAttribute("message", "Có lỗi xảy ra trong quá trình đổi mật khẩu.");
                        }
                    } else {
                        request.setAttribute("message", "Mật khẩu mới không khớp. Vui lòng thử lại.");
                    }
                } else {
                    request.setAttribute("message", "Mật khẩu hiện tại không chính xác. Vui lòng thử lại.");
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher("views/ChangePassword.jsp");
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ChangePasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChangePasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}