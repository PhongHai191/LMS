package controller;

import dal.AccountDAL;
import dal.UserDAO;
import model.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "accountController", urlPatterns = {"/accountController"})
public class AccountController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AccountController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AccountController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        if (a == null) {
            response.sendRedirect("views/Login.jsp");
            return;
        }
        String username = a.getUsername();
        String action = request.getParameter("action") != null ? request.getParameter("action") : "";
        System.out.println(username + " " + action + " " + a.getPassword());
        AccountDAL util = new AccountDAL();
        Account account = util.getAccountByUsername(username);
        request.setAttribute("accountDetail", account);
        if ("edit".equalsIgnoreCase(action)) {
            request.getRequestDispatcher("views/EditAccount.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("views/EditAccount.jsp").forward(request, response);

        }
        System.out.print(account);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            AccountDAL util = new AccountDAL();
            UserDAO ud = new UserDAO();
            HttpSession session = request.getSession();
            Account ab = (Account) session.getAttribute("account");
            String username = request.getParameter("username");
            Account a = util.getAccountByUsername(username);
            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            String mobile = request.getParameter("mobileNumber");
            int accountId = a.getAccountID();

            if (fullName == null || email == null || mobile == null) {
                request.setAttribute("errorMessage", "Vui lòng không để trống bất kỳ trường nào.");
                Account account = util.getAccountByUsername(username);
                request.setAttribute("accountDetail", account);
                request.getRequestDispatcher("views/EditAccount.jsp").forward(request, response);
                return;
            }

            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                request.setAttribute("errorMessage", "Email không hợp lệ.");
                Account account = util.getAccountByUsername(username);
                request.setAttribute("accountDetail", account);
                request.getRequestDispatcher("views/EditAccount.jsp").forward(request, response);
                return;
            }
            if (ud.checkEmailExist(email, accountId)) {
                request.setAttribute("errorMessage", "Email đã tồn tại.");
                Account account = util.getAccountByUsername(username);
                request.setAttribute("accountDetail", account);
                request.getRequestDispatcher("views/EditAccount.jsp").forward(request, response);
                return;
            }

            boolean result = util.updateAccountInfo(fullName, email, mobile, accountId);
            System.out.println(result + "doPost");
            if (result) {
                Account account = util.getAccountByUsername(username);
                if (account != null) {
                    request.setAttribute("accountDetail", account);
                    request.setAttribute("successMessage", "Cập nhật thông tin tài khoản thành công.");
                    //request.getRequestDispatcher("view/AccountDetail.jsp").forward(request, response);
                    request.getRequestDispatcher("views/EditAccount.jsp").forward(request, response);
                } else {
                    //request.getRequestDispatcher("view/AccountDetail.jsp").forward(request, response);
                    request.getRequestDispatcher("views/EditAccount.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("errorMessage", "Có lỗi xảy ra khi cập nhật thông tin tài khoản. Vui lòng thử lại.");
                Account account = util.getAccountByUsername(username);
                if (account != null) {
                    request.setAttribute("accountDetail", account);
                    request.getRequestDispatcher("views/EditAccount.jsp").forward(request, response);
                } else {
                    //request.getRequestDispatcher("view/AccountDetail.jsp").forward(request, response);
                    request.getRequestDispatcher("views/EditAccount.jsp").forward(request, response);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}