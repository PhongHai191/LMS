package dal;

import model.Assignment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AsmDAL extends DBContext{
    public Assignment getAllAsmByLesson(int lesson) {
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        SubjectDAL sd = new SubjectDAL();
        AccountDAL ad = new AccountDAL();
        try {
            String sql = "SELECT * FROM assignment JOIN lesson ON lesson.asm_id = assignment.asm_id WHERE lesson_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, lesson);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Assignment a = new Assignment();


                a.setAsmDes(rs.getString("asm_des"));
                a.setAsmID(rs.getInt("asm_id"));
                a.setAsmName(rs.getString("asm_name"));
                a.setChapter(ssd.getChapterByID(rs.getInt("chapter_id")));
                a.setCreatedAt(rs.getTimestamp("created_at"));
                a.setCreatedBy(ad.getAccountByID(rs.getInt("created_by")));
                a.setUpdatedAt(rs.getTimestamp("updated_at"));
                a.setUpdatedBy(ad.getAccountByID(rs.getInt("updated_by")));
                a.setDl(rs.getTimestamp("deadline"));
                a.setSubject(sd.getSubjectByID(rs.getInt("subject_id")));
                return a;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Assignment> getAllAsmByChapter(int chapter) {
        List<Assignment> list = new ArrayList<>();
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        SubjectDAL sd = new SubjectDAL();
        AccountDAL ad = new AccountDAL();
        try {
            String sql = "SELECT * FROM assignment WHERE chapter_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, chapter);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Assignment a = new Assignment();
                a.setAsmDes(rs.getString("asm_des"));
                a.setAsmID(rs.getInt("asm_id"));
                a.setAsmName(rs.getString("asm_name"));
                a.setCreatedAt(rs.getTimestamp("created_at"));
                a.setCreatedBy(ad.getAccountByID(rs.getInt("created_by")));
                a.setUpdatedAt(rs.getTimestamp("updated_at"));
                a.setUpdatedBy(ad.getAccountByID(rs.getInt("updated_by")));
                a.setDl(rs.getTimestamp("deadline"));
                list.add(a);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    public Assignment getAsmByID(int asmID) {
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        SubjectDAL sd = new SubjectDAL();
        AccountDAL ad = new AccountDAL();
        try {
            String sql = "SELECT * FROM assignment WHERE asm_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, asmID);
            ResultSet rs = st.executeQuery();
            Assignment a = new Assignment();
            if (rs.next()) {
                a.setAsmDes(rs.getString("asm_des"));
                a.setAsmID(rs.getInt("asm_id"));
                a.setAsmName(rs.getString("asm_name"));
                a.setChapter(ssd.getChapterByID(rs.getInt("chapter_id")));
                a.setCreatedAt(rs.getTimestamp("created_at"));
                a.setCreatedBy(ad.getAccountByID(rs.getInt("created_by")));
                a.setUpdatedAt(rs.getTimestamp("updated_at"));
                a.setUpdatedBy(ad.getAccountByID(rs.getInt("updated_by")));
                a.setDl(rs.getTimestamp("deadline"));
                a.setSubject(sd.getSubjectByID(rs.getInt("subject_id")));
            }
            return a;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


}
