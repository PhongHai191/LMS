package utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ValidInput {

    public int validInt(String s, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int n = 0;
        while(true) {
            try {
                n = Integer.parseInt(s);
                break;
            } catch (Exception e) {
                System.out.println(e);
                response.sendRedirect("view/error.jsp");
            }
        }
        return n;

    }
    public double validDouble(String s, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        double n = 0;
        while(true) {
            try {
                n = Double.parseDouble(s);
                break;
            } catch (Exception e) {
                System.out.println(e);
                response.sendRedirect("view/error.jsp");
            }
        }
        return n;
    }
}
