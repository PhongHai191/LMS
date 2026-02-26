package dal;

import model.Account;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends DBContext implements Serializable {

    public boolean checkEmailExist(String email, int id)
            throws SQLException, ClassNotFoundException {
        DBContext connect = new DBContext();
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Connect DB
            con = connect.DBContext();
            if (con != null) {
                //2. Create SQL String
                String sql = "Select *"
                        + "FROM account "
                        + "WHERE email = ? "
                        + "AND account_id != ?";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setInt(2, id);
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
                    return true;

                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public Account getAccountDetails(String username)
            throws SQLException, ClassNotFoundException {
        Account account = new Account();
        DBContext connect = new DBContext();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            //2. Create SQL String
            String sql = "Select *"
                    + "FROM account "
                    + "WHERE username = ? ";
            //3. Create Statement
            stm = DBContext().prepareStatement(sql);
            stm.setString(1, username);
            //4. Excute Query
            rs = stm.executeQuery();
            //5. Process Result
            if (rs.next()) {
                account.setAccountID(rs.getInt("account_id"));
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setRole(rs.getInt("role"));
                account.setStatus(rs.getInt("status"));
                account.setFullName(rs.getString("full_name"));
                account.setEmail(rs.getString("email"));
                account.setMobile(rs.getString("mobile"));
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return account;
    }

    public Account getAccountDetailsByEmail(String email)
            throws SQLException, ClassNotFoundException {
        Account account = new Account();
        DBContext connect = new DBContext();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            //2. Create SQL String
            String sql = "Select *"
                    + "FROM account "
                    + "WHERE email = ? ";
            //3. Create Statement
            stm = DBContext().prepareStatement(sql);
            stm.setString(1, email);
            //4. Excute Query
            rs = stm.executeQuery();
            //5. Process Result
            if (rs.next()) {
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setRole(rs.getInt("role"));
                account.setStatus(rs.getInt("status"));
                account.setFullName(rs.getString("full_name"));
                account.setEmail(rs.getString("email"));
                account.setMobile(rs.getString("mobile"));
                account.setAccountID(rs.getInt("account_id"));
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return account;
    }

    public boolean updateAccountInfo(String fullname, String email, String mobile, int id)
            throws SQLException, ClassNotFoundException {
        DBContext connect = new DBContext();
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Connect DB
            con = connect.DBContext();
            if (con != null) {
                //2. Create SQL String
                String sql = "Update account "
                        + "SET full_name = ?"
                        + ", email= ?"
                        + ", mobile = ?"
                        + "WHERE account_id = ?";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, fullname);
                stm.setString(2, email);
                stm.setString(3, mobile);
                stm.setInt(4, id);
                //4. Excute Query
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updateAccountPassword(String password, String username)
            throws SQLException, ClassNotFoundException {
        DBContext connect = new DBContext();
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Connect DB
            con = connect.DBContext();
            if (con != null) {
                //2. Create SQL String
                String sql = "Update account "
                        + "SET password = ?"
                        + "WHERE username = ?";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setString(2, username);
                //4. Excute Query
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean checkUsernameExist(String username)
            throws SQLException, ClassNotFoundException {
        DBContext connect = new DBContext();
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Connect DB
            con = connect.DBContext();
            if (con != null) {
                //2. Create SQL String
                String sql = "Select *"
                        + "FROM account "
                        + "WHERE username = ? ";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean checkEmailExist(String email)
            throws SQLException, ClassNotFoundException {
        DBContext connect = new DBContext();
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Connect DB
            con = connect.DBContext();
            if (con != null) {
                //2. Create SQL String
                String sql = "Select *"
                        + "FROM account "
                        + "WHERE email = ? ";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}