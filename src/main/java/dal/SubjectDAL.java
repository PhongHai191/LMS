package dal;

import model.Account;
import model.Subject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAL extends DBContext {
    // Create method
    public void create(Subject newSubject) throws SQLException {
        String query = "INSERT INTO subject (subject_code, subject_name, subject_des, manager, status, display_order) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newSubject.getSubjectCode());
            statement.setString(2, newSubject.getSubjectName());
            statement.setString(3, newSubject.getSubjectDescription());
            statement.setInt(4, newSubject.getManager().getAccountID());
            statement.setInt(5, newSubject.getStatus());
            statement.setInt(6, newSubject.getDisplayOrder());


            statement.executeUpdate();
        }
    }

    // Read method
    public List<Subject> getAllSubjects() throws SQLException {
        List<Subject> subjects = new ArrayList<>();
        String query = "SELECT * FROM subject";
        SystemSettingDAL sd = new SystemSettingDAL();
        AccountDAL ad = new AccountDAL();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Subject subject = new Subject();
                subject.setSubjectID(resultSet.getInt("subject_id"));
                subject.setSubjectCode(resultSet.getString("subject_code"));
                subject.setSubjectName(resultSet.getString("subject_name"));
                subject.setSubjectDescription(resultSet.getString("subject_des"));
                Account account = ad.getAccountByID(resultSet.getInt("manager"));
                subject.setManager(account);
                subject.setStatus(resultSet.getInt("status"));
                subject.setDisplayOrder(resultSet.getInt("display_order"));

                subjects.add(subject);
            }
        }

        return subjects;
    }

    public Subject getById(int id) throws SQLException {
        Subject subject = null;
        SystemSettingDAL sd = new SystemSettingDAL();
        AccountDAL ad = new AccountDAL();
        String query = "SELECT * FROM subject WHERE subject_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    subject = new Subject();
                    subject.setSubjectID(resultSet.getInt("subject_id"));
                    subject.setSubjectCode(resultSet.getString("subject_code"));
                    subject.setSubjectName(resultSet.getString("subject_name"));
                    subject.setSubjectDescription(resultSet.getString("subject_des"));
                    Account account = ad.getAccountByID(resultSet.getInt("manager"));
                    subject.setManager(account);
                    subject.setStatus(resultSet.getInt("status"));
                    subject.setDisplayOrder(resultSet.getInt("display_order"));
                }
            }
        }
        return subject;
    }

    // Update method
    public void update(Subject updatedSubject) throws SQLException {
        String query = "UPDATE subject SET subject_code=?, subject_name=?, subject_des=?, " +
                "manager=?, status=?, display_order=? WHERE subject_id=?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, updatedSubject.getSubjectCode());
            statement.setString(2, updatedSubject.getSubjectName());
            statement.setString(3, updatedSubject.getSubjectDescription());
            statement.setInt(4, updatedSubject.getManager().getAccountID());
            statement.setInt(5, updatedSubject.getStatus());
            statement.setInt(6, updatedSubject.getDisplayOrder());
            statement.setInt(7, updatedSubject.getSubjectID());

            statement.executeUpdate();
        }
    }

    // Delete method
    public void delete(int subjectId) throws SQLException {
        String query = "DELETE FROM subject WHERE subject_id=?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, subjectId);
            statement.executeUpdate();
        }
    }


//    public static void main(String[] args) throws SQLException {
//        SubjectDAL sd = new SubjectDAL();
//        System.out.println(sd.getById(1));
//    }

    public Subject getSubjectByID(int id) {
        try {
            AccountDAL ad = new AccountDAL();
            String sql = "SELECT * FROM subject WHERE subject_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setStatus(rs.getInt("status"));
                s.setSubjectID(rs.getInt("subject_id"));
                s.setSubjectCode(rs.getString("subject_code"));
                s.setSubjectName(rs.getString("subject_name"));
                s.setSubjectDescription(rs.getString("subject_des"));
                return s;

            }
        } catch (Exception e) {
        }
        return null;
    }

    public Subject getSubjectByCode(String id) {
        try {
            AccountDAL ad = new AccountDAL();
            String sql = "SELECT * FROM subject WHERE subject_code = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Subject s = new Subject(rs.getInt("subject_id"), rs.getString("subject_name"), rs.getString("subject_des"), rs.getInt("status"), ad.getAccountByID(rs.getInt("manager")));
                s.setSubjectCode(rs.getString("subject_code"));
                return s;
            }
        } catch (Exception e) {
        }
        return null;
    }


}
