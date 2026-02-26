package dal;

import model.Account;
import model.SystemSetting;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountDAL extends DBContext {

    public ArrayList<Account> getAllAccount() {
        try {
            String sql = "SELECT * FROM account";
            ArrayList<Account> list;
            try (PreparedStatement ps = DBContext().prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    Account a = new Account();
                    a.setAccountID(rs.getInt("account_id"));
                    a.setUsername(rs.getString("username"));
                    a.setPassword(rs.getString("password"));
                    a.setEmail(rs.getString("email"));
                    a.setRole(rs.getInt("role"));
                    a.setStatus(rs.getInt("status"));
                    a.setFullName(rs.getString("full_name"));
                    a.setMobile(rs.getString("mobile"));
                    a.setDob(rs.getDate("enrolment_date"));
                    a.setDescription(rs.getString("description"));
                    list.add(a);

                }
                rs.close();
            }
            return list;
        } catch (SQLException e) {
            Logger.getLogger(AccountDAL.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        return null;
    }


    public Account getAccountByID(int id) {
        try {
            String sql = "SELECT * FROM account WHERE account_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setAccountID(rs.getInt("account_id"));
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("password"));
                a.setEmail(rs.getString("email"));
                a.setRole(rs.getInt("role"));
                a.setStatus(rs.getInt("status"));
                a.setFullName(rs.getString("full_name"));
                a.setMobile(rs.getString("mobile"));
                a.setDob(rs.getDate("enrolment_date"));
                a.setDescription(rs.getString("description"));
                return a;
            }
        } catch (Exception e) {

        }
        return null;
    }

    public Account getAccountByEmail(String email) {
        try {
            String sql = "SELECT * FROM account WHERE email = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setAccountID(rs.getInt("account_id"));
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("password"));
                a.setEmail(rs.getString("email"));
                a.setRole(rs.getInt("role"));
                a.setStatus(rs.getInt("status"));
                a.setFullName(rs.getString("full_name"));
                a.setMobile(rs.getString("mobile"));
                a.setDob(rs.getDate("enrolment_date"));
                a.setDescription(rs.getString("description"));
                return a;

            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean updateAccount(int id, String username, String password, String email, int role, int status, String fullName, String mobile, String dob, String description) {
        try {
            String sql = "UPDATE account SET username = ?, password = ?, email = ?, role = ?, status = ?, full_name = ?, mobile = ?, enrolment_date = ?, description = ? WHERE account_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            st.setString(3, email);
            st.setInt(4, role);
            st.setInt(5, status);
            st.setString(6, fullName);
            st.setString(7, mobile);
            st.setString(8, dob);
            st.setString(9, description);
            st.setInt(10, id);
            int rowsUpdated = st.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }


    public ArrayList<Account> searchAccount(String username) {
        ArrayList<Account> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM account WHERE username LIKE ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, "%" + username + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setAccountID(rs.getInt("account_id"));
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("password"));
                a.setEmail(rs.getString("email"));
                a.setRole(rs.getInt("role"));
                a.setStatus(rs.getInt("status"));
                a.setFullName(rs.getString("full_name"));
                a.setMobile(rs.getString("mobile"));
                a.setDob(rs.getDate("enrolment_date"));
                a.setDescription(rs.getString("description"));
                list.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Account> getAccounts(int pageNumber, int pageSize) {
        ArrayList<Account> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM account ORDER BY account_id LIMIT ? OFFSET ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, pageSize);
            st.setInt(2, (pageNumber - 1) * pageSize);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setAccountID(rs.getInt("account_id"));
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("password"));
                a.setEmail(rs.getString("email"));
                a.setRole(rs.getInt("role"));
                a.setStatus(rs.getInt("status"));
                a.setFullName(rs.getString("full_name"));
                a.setMobile(rs.getString("mobile"));
                a.setDob(rs.getDate("enrolment_date"));
                a.setDescription(rs.getString("description"));
                list.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int getTotalAccount() {
        try {
            String sql = "SELECT COUNT(*) FROM account";
            PreparedStatement st = DBContext().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public boolean deleteAccount(int id) {
        try {
            String sql = "DELETE FROM account WHERE account_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, id);
            int rowsAffected = st.executeUpdate();
            st.close();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Account authenticate(String username, String password) {
        try {
            String sql = "SELECT * FROM account WHERE username = ? AND password = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setAccountID(rs.getInt("account_id"));
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("password"));
                a.setEmail(rs.getString("email"));
                a.setRole(rs.getInt("role"));
                a.setStatus(rs.getInt("status"));
                a.setFullName(rs.getString("full_name"));
                a.setMobile(rs.getString("mobile"));
                a.setDob(rs.getDate("enrolment_date"));
                a.setDescription(rs.getString("description"));
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Account check(String u, String p) {
        SystemSettingDAL ssd = new SystemSettingDAL();
        try {
            String sql = "SELECT * FROM account\n"
                    + " JOIN system_setting ON account.role = setting_id "
                    + "WHERE (email = ? OR mobile = ?) AND password = ? AND status = 1;";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, u);
            st.setString(2, u); // Sử dụng giá trị u cho cả email và SĐT
            st.setString(3, p);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                SystemSetting ss = new SystemSetting();
                ss.setSettingID(rs.getInt("role"));
                Account a = new Account();
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("password"));
                a.setStatus(rs.getInt("status"));
                a.setFullName(rs.getString("full_name"));
                a.setEmail(rs.getString("email"));
                a.setMobile(rs.getString("mobile"));
                a.setAccountID(rs.getInt("account_id"));
                a.setSystemSetting(ssd.getSettingByID(rs.getInt("role")));
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void add(String username, String email, String password, String fullName, String mobile, String description) {
        try {
            String sql = "INSERT INTO account (username, email, password, full_name, mobile, description) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, email);
            st.setString(3, password);
            st.setString(4, fullName);
            st.setString(5, mobile);
            st.setString(6, description);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Account getAccountByUsername(String username) {
        List<Account> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM account WHERE username = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                SystemSetting ss = new SystemSetting();
                ss.setSettingID(rs.getInt("role"));
                Account a = new Account();
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("password"));
                a.setStatus(rs.getInt("status"));
                a.setFullName(rs.getString("full_name"));
                a.setEmail(rs.getString("email"));
                a.setMobile(rs.getString("mobile"));
                a.setAccountID(rs.getInt("account_id"));
                return a;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean updateAccountInfo(String fullName, String email, String mobile, int id)
            throws SQLException, ClassNotFoundException {
        try {
            String sql = "Update account "
                    + "SET full_name = ?"
                    + ", email= ?"
                    + ", mobile = ?"
                    + "WHERE account_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, fullName);
            st.setString(2, email);
            st.setString(3, mobile);
            st.setInt(4, id);
            int rowsUpdated = st.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public List<Account> getAllTraineeInClass(int classID) {
        List<Account> list = new ArrayList<>();
        try {
            String sql = "SELECT account.* FROM student_class JOIN account ON student_class.student_id = account.account_id WHERE class_id = ? ";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, classID);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                SystemSetting ss = new SystemSetting();
                ss.setSettingID(rs.getInt("role"));
                Account a = new Account();
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("password"));
                a.setStatus(rs.getInt("status"));
                a.setFullName(rs.getString("full_name"));
                a.setEmail(rs.getString("email"));
                a.setMobile(rs.getString("mobile"));
                a.setAccountID(rs.getInt("account_id"));
                list.add(a);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }



}
