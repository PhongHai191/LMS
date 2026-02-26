package dal;

import model.SystemSetting;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SystemSettingDAL extends DBContext{

    public ArrayList<SystemSetting> getAllSetting() {
        try {
            String sql = "SELECT * FROM system_setting";
            ArrayList<SystemSetting> list;
            try (PreparedStatement ps = DBContext().prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    SystemSetting ss = new SystemSetting();
                    ss.setSettingID(rs.getInt("setting_id"));
                    ss.setSettingName(rs.getString("setting_name"));
                    ss.setSettingType(rs.getString("setting_type"));
                    ss.setDisplayOrder(rs.getInt("display_order"));
                    ss.setStatus(rs.getInt("setting_status"));
                    list.add(ss);
                }

                rs.close();
            }
            return list;
        }catch (SQLException e) {
            Logger.getLogger(SystemSettingDAL.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public SystemSetting getSettingByID(int id) {
        try {
            String sql = "SELECT * FROM system_setting WHERE setting_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                SystemSetting ss = new SystemSetting();
                ss.setSettingID(rs.getInt("setting_id"));
                ss.setSettingName(rs.getString("setting_name"));
                ss.setSettingType(rs.getString("setting_type"));
                ss.setDisplayOrder(rs.getInt("display_order"));
                ss.setStatus(rs.getInt("setting_status"));
                return ss;
            }
        } catch (Exception e) {

        }
        return null;
    }

    public ArrayList<SystemSetting> getSettings(int pageNumber, int pageSize) {
        ArrayList<SystemSetting> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM system_setting ORDER BY setting_id LIMIT ? OFFSET ?";
            PreparedStatement ps = DBContext().prepareStatement(sql);
                ps.setInt(1, pageSize);
                ps.setInt(2, (pageNumber - 1) * pageSize);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    SystemSetting ss = new SystemSetting();
                    ss.setSettingID(rs.getInt("setting_id"));
                    ss.setSettingName(rs.getString("setting_name"));
                    ss.setSettingType(rs.getString("setting_type"));
                    ss.setDisplayOrder(rs.getInt("display_order"));
                    ss.setStatus(rs.getInt("setting_status"));
                    list.add(ss);
                }
        } catch (SQLException e) {
            Logger.getLogger(SystemSettingDAL.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    public int getTotalSetting() {
        try {
            String sql = "SELECT COUNT(*) FROM system_setting";
            PreparedStatement ps = DBContext().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(SystemSettingDAL.class.getName()).log(Level.SEVERE, null, e);
        }
        return 0;
    }
    public List<SystemSetting> getAllLessonType() {
        List<SystemSetting> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM system_setting WHERE setting_type = 'Lesson Type'";
            PreparedStatement st = DBContext().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                SystemSetting ss = new SystemSetting();
                ss.setSettingID(rs.getInt("setting_id"));
                ss.setSettingName(rs.getString("setting_name"));
                ss.setStatus(rs.getInt("setting_status"));
                ss.setSettingType(rs.getString("setting_type"));
                ss.setDisplayOrder(rs.getInt("display_order"));
                list.add(ss);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public SystemSetting getSetting(String s) {
        try {
            String sql = "SELECT * FROM system_setting WHERE setting_name = '" + s + "'";
            PreparedStatement st = DBContext().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                SystemSetting ss = new SystemSetting();
                ss.setSettingID(rs.getInt("setting_id"));
                ss.setSettingName(rs.getString("setting_name"));
                ss.setStatus(rs.getInt("setting_status"));
                ss.setSettingType(rs.getString("setting_type"));
                ss.setDisplayOrder(rs.getInt("display_order"));
                return ss;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<SystemSetting> getSettingByType(String settingType) {
        List<SystemSetting> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM system_setting WHERE setting_type = ?";
            try (PreparedStatement ps = DBContext().prepareStatement(sql)) {
                ps.setString(1, settingType);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    SystemSetting ss = new SystemSetting();
                    ss.setSettingID(rs.getInt("setting_id"));
                    ss.setSettingName(rs.getString("setting_name"));
                    ss.setSettingType(rs.getString("setting_type"));
                    ss.setDisplayOrder(rs.getInt("display_order"));
                    ss.setStatus(rs.getInt("setting_status"));
                    list.add(ss);

                }
                rs.close(); // Moved outside the while loop
            }
        } catch (SQLException e) {
            Logger.getLogger(SystemSettingDAL.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

}
