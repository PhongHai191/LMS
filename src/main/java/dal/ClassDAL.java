package dal;

import model.Account;
import utils.FormatDate;
import utils.FormatTime;
import model.Class;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassDAL extends DBContext {
    public void create(Class newClass) throws SQLException {
        String query = "INSERT INTO class (class_name, subject_id, semester, status, start_date, end_date, " +
                "start_time, end_time, trainer, display_order, description) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newClass.getClassName());
            statement.setInt(2, newClass.getS().getSubjectID());
            statement.setInt(3, newClass.getSemester().getSettingID());
            statement.setInt(4, newClass.getActivate());
            statement.setDate(5, new Date(newClass.getStartDate().getTime()));
            statement.setDate(6, new Date(newClass.getEndDate().getTime()));
            statement.setTime(7, newClass.getStartTime());
            statement.setTime(8, newClass.getEndTime());
            statement.setInt(9, newClass.getTrainer().getAccountID());
            statement.setInt(10, newClass.getDisplayOrder());
            statement.setString(11, newClass.getDescription());


            statement.executeUpdate();

        }
    }

    // Read method
    public List<Class> getAllClasses() throws SQLException {
        List<Class> classes = new ArrayList<>();
        String query = "SELECT * FROM class";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Class newClass = new Class();
                SubjectDAL sDAO = new SubjectDAL();
                SystemSettingDAL sysDAO = new SystemSettingDAL();
                AccountDAL aDAO = new AccountDAL();
                newClass.setClassID(resultSet.getInt("class_id"));
                newClass.setClassName(resultSet.getString("class_name"));
                newClass.setS(sDAO.getSubjectByID(resultSet.getInt("subject_id")));
                newClass.setSemester(sysDAO.getSettingByID(resultSet.getInt("semester")));
                newClass.setActivate(resultSet.getInt("status"));
                newClass.setStartDate(resultSet.getDate("start_date"));
                newClass.setEndDate(resultSet.getDate("end_date"));
                newClass.setStartTime(resultSet.getTime("start_time"));
                newClass.setEndTime(resultSet.getTime("end_time"));
                newClass.setTrainer(aDAO.getAccountByID(resultSet.getInt("trainer")));
                newClass.setDisplayOrder(resultSet.getInt("display_order"));
                newClass.setDescription(resultSet.getString("description"));

                classes.add(newClass);
            }
        }


        return classes;
    }
    //    public Class getById(int id) throws SQLException {
//        Class clazz = null;
//        String query = "SELECT * FROM class WHERE class_id = ?";
//
//        try (PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setInt(1, id);
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    clazz = new Class();
//                    clazz.setClassID(resultSet.getInt("class_id"));
//                    clazz.setClassName(resultSet.getString("class_name"));
//                    clazz.setSubjectId(resultSet.getInt("subject_id"));
//                    clazz.setSemester(resultSet.getString("semester"));
//                    clazz.setActivate(resultSet.getInt("status"));
//                    clazz.setStartDate(resultSet.getDate("start_date"));
//                    clazz.setEndDate(resultSet.getDate("end_date"));
//                    clazz.setStartTime(resultSet.getTime("start_time"));
//                    clazz.setEndTime(resultSet.getTime("end_time"));
//                    clazz.setTrainer(resultSet.getInt("trainer"));
//                    clazz.setDisplayOrder(resultSet.getInt("display_order"));
//                    clazz.setDescription(resultSet.getString("description"));
//                }
//            }
//        }
//        return clazz;
//    }
    // Update method
    public void update(Class updatedClass) throws SQLException {
        String query = "UPDATE class SET class_name=?, subject_id=?, semester=?, status=?, start_date=?, " +
                "end_date=?, start_time=?, end_time=?, trainer=?, display_order=?, description=? " +
                "WHERE class_id=?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, updatedClass.getClassName());
            statement.setInt(2, updatedClass.getS().getSubjectID());
            statement.setInt(3, updatedClass.getSemester().getSettingID());
            statement.setInt(4, updatedClass.getActivate());
            statement.setDate(5, new Date(updatedClass.getStartDate().getTime()));
            statement.setDate(6, new Date(updatedClass.getEndDate().getTime()));
            statement.setTime(7, updatedClass.getStartTime());
            statement.setTime(8, updatedClass.getEndTime());
            statement.setInt(9, updatedClass.getTrainer().getAccountID());
            statement.setInt(10, updatedClass.getDisplayOrder());
            statement.setString(11, updatedClass.getDescription());
            statement.setInt(12, updatedClass.getClassID());

            statement.executeUpdate();
        }
    }

    // Delete method
    public void delete(int classId) throws SQLException {
        String query = "DELETE FROM class WHERE class_id=?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, classId);
            statement.executeUpdate();
        }
    }

    public model.Class getClassByIDASjASem(int classID, int subjectID) {
        SubjectDAL sd = new SubjectDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        AccountDAL ad = new AccountDAL();
        FormatDate fd = new FormatDate();
        FormatTime ft = new FormatTime();
        try {
            String sql = "SELECT * FROM class  WHERE class_id= ? AND subject_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, classID);
            st.setInt(2, subjectID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                model.Class c = new model.Class();
                c.setClassID(rs.getInt("class_id"));
                c.setClassName(rs.getString("class_name"));
                c.setS(sd.getSubjectByID(rs.getInt("subject_id")));
                c.setSemester(ssd.getSettingByID(rs.getInt("semester")));
                c.setActivate(rs.getInt("status"));
                c.setStartDate(rs.getDate("start_date"));
                c.setEndDate(rs.getDate("end_date"));
                if (rs.getTime("start_time") != null) {
                    c.setStartTime(rs.getTime("start_time"));
                }
                if (rs.getTime("end_time") != null) {
                    c.setEndTime(rs.getTime("end_time"));
                }
                c.setTrainer(ad.getAccountByID(rs.getInt("trainer")));
                c.setDisplayOrder(rs.getInt("display_order"));
                c.setDescription(rs.getString("description"));
                return c;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public model.Class getClassByID(int id) {
        SystemSettingDAL ssd = new SystemSettingDAL();
        SubjectDAL sd = new SubjectDAL();
        FormatDate fd = new FormatDate();
        FormatTime ft = new FormatTime();
        AccountDAL ad = new AccountDAL();
        try {
            String sql = "SELECT * FROM class WHERE class.class_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                model.Class c = new model.Class();
                c.setActivate(rs.getInt("status"));
                c.setClassID(rs.getInt("class_id"));
                c.setClassName(rs.getString("class_name"));
                c.setEndDate(rs.getDate("end_date"));
                if (rs.getTime("end_time") != null) {
                    c.setEndTime(rs.getTime("end_time"));
                }
                c.setS(sd.getSubjectByID(rs.getInt("subject_id")));
                c.setSemester(ssd.getSettingByID(rs.getInt("semester")));

                c.setStartDate(rs.getDate("start_date"));
                if (rs.getTime("start_time") != null) {
                    c.setStartTime(rs.getTime("start_time"));
                }
                c.setTrainer(ad.getAccountByID(rs.getInt("trainer")));
                c.setStartS(fd.formatDateSQL(c.getStartDate()));
                c.setEndS(fd.formatDateSQL(c.getEndDate()));
                c.setDisplayOrder(rs.getInt("display_order"));
                c.setDescription(rs.getString("description"));
                return c;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Class> getAllClassThatTraineeIn(int student_id, Date d) {
        List<model.Class> list = new ArrayList<>();
        SubjectDAL sd = new SubjectDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        FormatDate fd = new FormatDate();
        FormatTime ft = new FormatTime();
        AccountDAL ad = new AccountDAL();
        try {
            String sql = "SELECT class.* FROM student_class JOIN class ON student_class.class_id = class.class_id "
                    + "AND student_class.subject_Id = class.subject_id "
                    + "WHERE student_id = ? "
                    + "AND (class.start_date <= ? AND ? <= class.end_date "
                    + ")";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, student_id);
            st.setDate(2, d);
            st.setDate(3, d);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                model.Class c = new model.Class();
                c.setActivate(rs.getInt("status"));
                c.setClassID(rs.getInt("class_id"));
                c.setClassName(rs.getString("class_name"));
                c.setEndDate(rs.getDate("end_date"));
                if (rs.getTime("end_time") != null) {
                    c.setEndTime(rs.getTime("end_time"));
                }
                c.setS(sd.getSubjectByID(rs.getInt("subject_id")));
                c.setSemester(ssd.getSettingByID(rs.getInt("semester")));

                c.setStartDate(rs.getDate("start_date"));
                if (rs.getTime("start_time") != null) {
                    c.setStartTime(rs.getTime("start_time"));
                }
                c.setTrainer(ad.getAccountByID(rs.getInt("trainer")));
                c.setStartS(fd.formatDateSQL(c.getStartDate()));
                c.setEndS(fd.formatDateSQL(c.getEndDate()));
                c.setDisplayOrder(rs.getInt("display_order"));
                list.add(c);
            }
        } catch (Exception e) {

            System.out.println(e);
        }
        return list;
    }

    public Account getTrainerByClassID(int classID) {

        AccountDAL ad = new AccountDAL();
        try {
            String sql = "SELECT trainer FROM class WHERE class_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, classID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Account a = ad.getAccountByID(rs.getInt("trainer"));
                return a;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


}