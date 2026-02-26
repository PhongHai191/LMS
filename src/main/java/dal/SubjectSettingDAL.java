package dal;

import model.Chapter;
import model.Lesson;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class SubjectSettingDAL extends DBContext{
    public List<Chapter> getAllChapterFromClassAndSubject(int subjectID) {
        SubjectDAL sd = new SubjectDAL();
        AccountDAL ad = new AccountDAL();
        List<Chapter> list = new ArrayList<>();
        AsmDAL asd = new AsmDAL();
        try {


            String sql = "SELECT * FROM subject_setting LEFT JOIN\n"
                    + "					(SELECT subject_setting.setting_id, SUM(cnt) AS cnt FROM subject_setting LEFT JOIN \n"
                    + "                    (SELECT subject_setting.setting_id, COUNT(DISTINCT subject_setting.setting_id, lesson.lesson_id) AS cnt FROM subject_setting\n"
                    + "                     LEFT JOIN lesson ON subject_setting.setting_id = lesson.chapter_id\n"
                    + "                    LEFT JOIN video_tracking ON lesson.lesson_id = video_tracking.lesson_id\n"
                    + "                    LEFT JOIN quiz ON lesson.quiz_id = quiz.quiz_id\n"
                    + "                    LEFT JOIN grade_item ON quiz.quiz_id = grade_item.quiz_id\n"
                    + "                    WHERE subject_setting.subject_id = ? AND (grade_item.student_id > 0 OR video_tracking.status = 1)\n"
                    + "                    GROUP BY subject_setting.setting_id, lesson.lesson_id) AS T\n"
                    + "                    ON subject_setting.setting_id = T.setting_id\n"
                    + "                    GROUP BY subject_setting.setting_id) AS C\n"
                    + "                    ON subject_setting.setting_id = C.setting_id";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, subjectID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Chapter c = new Chapter();
                c.setChapterID(rs.getInt("setting_id"));
                c.setChapterName(rs.getString("setting_name"));
                c.setCreatedAt(rs.getTimestamp("created_at"));
                c.setCreatedBy(ad.getAccountByID(rs.getInt("created_by")));
                c.setUpdatedAt(rs.getTimestamp("updated_at"));
                c.setUpdatedBy(ad.getAccountByID(rs.getInt("updated_by")));
                c.setDescription(rs.getString("description"));
                c.setDisplayOrder(rs.getInt("display_order"));
                c.setSubject(sd.getSubjectByID(rs.getInt("subject_id")));
                c.setStatus(rs.getInt("status"));
                c.setAsm(asd.getAllAsmByChapter(rs.getInt("setting_id")));
                c.setNumAchieve(rs.getInt("cnt"));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    public Lesson getLessonByID(int ID) {
        AccountDAL ad = new AccountDAL();
        QuizDAL qd = new QuizDAL();
        AsmDAL asd = new AsmDAL();
        ClassDAL cd = new ClassDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        System.out.println("kdoa" + ID);
        try {
            String sql = "SELECT lesson.* FROM lesson WHERE lesson.lesson_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, ID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Lesson l = new Lesson();
                l.setChapter(getChapterByID(rs.getInt("chapter_id")));
                l.setCreatedAt(rs.getTimestamp("created_at"));
                l.setCreatedBy(ad.getAccountByID(rs.getInt("created_by")));
                l.setUpdatedAt(rs.getTimestamp("updated_at"));
                l.setUpdatedBy(ad.getAccountByID(rs.getInt("updated_by")));
                l.setDescription(rs.getString("description"));
                l.setDisplayOrder(rs.getInt("display_order"));
                l.setLessonID(rs.getInt("lesson_id"));
                l.setLessonName(rs.getString("lesson_name"));
                l.setStatus(rs.getInt("status"));
                l.setVideoLink(rs.getString("video_link"));
                l.setAttatchedFile(rs.getString("attatched_file"));
                if (rs.getInt("asm_id") != 0) {
                    l.setAsm(asd.getAsmByID(rs.getInt("asm_id")));
                }
                if (rs.getInt("class_id") != 0) {
                    l.setCls(cd.getClassByID(rs.getInt("class_id")));
                }
                l.setLessonType(ssd.getSettingByID(rs.getInt("lesson_type")));
                l.setDescription(rs.getString("description"));
                return l;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Chapter getChapterByID(int id) {
        SubjectDAL sd = new SubjectDAL();
        AccountDAL ad = new AccountDAL();
        try {
            String sql = "SELECT * FROM subject_setting WHERE setting_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Chapter c = new Chapter();
                c.setChapterID(rs.getInt("setting_id"));
                c.setChapterName(rs.getString("setting_name"));
                c.setCreatedAt(rs.getTimestamp("created_at"));
                c.setCreatedBy(ad.getAccountByID(rs.getInt("created_by")));
                c.setUpdatedAt(rs.getTimestamp("updated_at"));
                c.setUpdatedBy(ad.getAccountByID(rs.getInt("updated_by")));
                c.setDescription(rs.getString("description"));
                c.setDisplayOrder(rs.getInt("display_order"));
                c.setSubject(sd.getSubjectByID(rs.getInt("subject_id")));
                c.setStatus(rs.getInt("status"));
                return c;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    public List<Lesson> getAllLessonByChapter(int chapterID, int classID) {
        AccountDAL ad = new AccountDAL();
        List<Lesson> list = new ArrayList<>();
        QuizDAL qd = new QuizDAL();
        AsmDAL asd = new AsmDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        try {
            String sql = "SELECT lesson.*, grade_item.student_id, quiz.quiz_id, assignment.asm_id, grade, video_tracking.status AS vist FROM lesson LEFT JOIN quiz ON lesson.quiz_id = quiz.quiz_id "
                    + "LEFT JOIN video_tracking ON lesson.lesson_id = video_tracking.lesson_id"
                    + " LEFT JOIN assignment ON lesson.asm_id = assignment.asm_id"
                    + " LEFT JOIN grade_item ON quiz.quiz_id = grade_item.quiz_id WHERE lesson.chapter_id = ? AND lesson.status = 1 AND (lesson.class_id IS NULL || lesson.class_id = ?) AND (count = 1 || count IS NULL)\n";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, chapterID);
            st.setInt(2, classID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Lesson l = new Lesson();
                l.setLessonID(rs.getInt("lesson_id"));
                l.setChapter(getChapterByID(rs.getInt("chapter_id")));
                l.setCreatedAt(rs.getTimestamp("created_at"));
                l.setCreatedBy(ad.getAccountByID(rs.getInt("created_by")));
                l.setDescription(rs.getString("description"));
                l.setDisplayOrder(rs.getInt("display_order"));
                l.setLessonName(rs.getString("lesson_name"));
                l.setStatus(rs.getInt("status"));
                l.setUpdatedAt(rs.getTimestamp("updated_at"));
                l.setUpdatedBy(ad.getAccountByID(rs.getInt("updated_by")));
                l.setQuiz(qd.getQuizByLessonID(rs.getInt("lesson_id")));
                l.setAsm(asd.getAllAsmByLesson(rs.getInt("asm_id")));
                l.setVideoLink(rs.getString("video_link"));
                l.setLessonType(ssd.getSettingByID(rs.getInt("lesson_type")));
                l.setDescription(rs.getString("description"));
                if (rs.getInt("student_id") > 0 || rs.getInt("vist") == 1) {
                    l.setAchieve(true);
                } else {
                    l.setAchieve(false);
                }
                list.add(l);
            }
        } catch (Exception e) {
            System.out.println("2" + e);
        }
        return list;
    }
    public List<Lesson> getLessonByPage(int start, int end, List<Lesson> list) {
        List<Lesson> list1 = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            list1.add(list.get(i));
        }
        return list1;
    }
    public String getSortedLessonAsc(String id) {
        String order = "";
        switch (id) {
            case "ID": {
                order = "ORDER BY lesson_id ASC";
                break;
            }
            case "Name": {
                order = "ORDER BY lesson_name ASC";
                break;
            }
            case "Chapter": {
                order = "ORDER BY chapter_id ASC";
                break;
            }
            case "Type": {
                order = "ORDER BY lesson_type ASC";
                break;
            }
            case "Order": {
                order = "ORDER BY display_order ASC";
                break;
            }
            case "Status": {
                order = "ORDER BY status ASC";
                break;
            }
        }
        return order;
    }
    public void addLessonVideo(Lesson l) {
        try {
            String sql = "INSERT INTO lesson"
                    + " (chapter_id, lesson_name, lesson_type, display_order, video_link, description, class_id, quiz_id, asm_id, created_by, created_at, status, attatched_file)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, l.getChapter().getChapterID());
            System.out.println(1);
            st.setString(2, l.getLessonName());
            System.out.println(2);
            st.setInt(3, l.getLessonType().getSettingID());
            System.out.println(3);
            st.setInt(4, l.getDisplayOrder());
            System.out.println(4);
            st.setString(5, l.getVideoLink());
            st.setString(6, l.getDescription());
            if (l.getCls() != null) {
                st.setInt(7, l.getCls().getClassID());
            } else {
                st.setNull(7, java.sql.Types.INTEGER);
            }

            if (l.getQuiz() != null) {
                st.setInt(8, l.getQuiz().getQuizID());
            } else {
                st.setNull(8, java.sql.Types.INTEGER);
            }
            if (l.getAsm() != null) {
                st.setInt(9, l.getAsm().getAsmID());
            } else {
                st.setNull(9, java.sql.Types.INTEGER);
            }

            st.setInt(10, l.getCreatedBy().getAccountID());

            st.setTimestamp(11, l.getCreatedAt());
            st.setInt(12, l.getStatus());
            st.setString(13, null);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println("da" + e);
        }
    }
    public void actLesson(int n, int id) {

        try {
            String sql = "UPDATE Lesson SET status = ? WHERE lesson_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, n);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println("da" + e);
        }
    }
    public List<Chapter> getAllChapterBySubject(int subjectID) {
        List<Chapter> list = new ArrayList<>();
        SubjectDAL sd = new SubjectDAL();
        AccountDAL ad = new AccountDAL();
        try {
            String sql = "SELECT * FROM subject_setting WHERE setting_type = 'Chapter' AND subject_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, subjectID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Chapter c = new Chapter();
                c.setChapterID(rs.getInt("setting_id"));
                c.setChapterName(rs.getString("setting_name"));
                c.setCreatedAt(rs.getTimestamp("created_at"));
                c.setCreatedBy(ad.getAccountByID(rs.getInt("created_by")));
                c.setUpdatedAt(rs.getTimestamp("updated_at"));
                c.setUpdatedBy(ad.getAccountByID(rs.getInt("updated_by")));
                c.setDescription(rs.getString("description"));
                c.setDisplayOrder(rs.getInt("display_order"));
                c.setSubject(sd.getSubjectByID(rs.getInt("subject_id")));
                c.setStatus(rs.getInt("status"));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    public String getSortedLessonDesc(String id) {
        String order = "";
        switch (id) {
            case "ID": {
                order = "ORDER BY lesson_id DESC";
                break;
            }
            case "Name": {
                order = "ORDER BY lesson_name DESC";
                break;
            }
            case "Chapter": {
                order = "ORDER BY chapter_id DESC";
                break;
            }
            case "Type": {
                order = "ORDER BY lesson_type DESC";
                break;
            }
            case "Order": {
                order = "ORDER BY display_order DESC";
                break;
            }
            case "Status": {
                order = "ORDER BY status DESC";
                break;
            }
        }
        return order;
    }
    public List<Chapter> getAllChapter() {
        List<Chapter> list = new ArrayList<>();
        SubjectDAL sd = new SubjectDAL();
        AccountDAL ad = new AccountDAL();
        try {
            String sql = "SELECT * FROM subject_setting WHERE setting_type = 'Chapter'";
            PreparedStatement st = DBContext().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Chapter c = new Chapter();
                c.setChapterID(rs.getInt("setting_id"));
                c.setChapterName(rs.getString("setting_name"));
                c.setCreatedAt(rs.getTimestamp("created_at"));
                c.setCreatedBy(ad.getAccountByID(rs.getInt("created_by")));
                c.setUpdatedAt(rs.getTimestamp("updated_at"));
                c.setUpdatedBy(ad.getAccountByID(rs.getInt("updated_by")));
                c.setDescription(rs.getString("description"));
                c.setDisplayOrder(rs.getInt("display_order"));
                c.setSubject(sd.getSubjectByID(rs.getInt("subject_id")));
                c.setStatus(rs.getInt("status"));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    public List<Lesson> getLessonBySearch(String more, String order) {
        List<Lesson> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM lesson " + more
                    + order;
            PreparedStatement st = DBContext().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            SystemSettingDAL ssd = new SystemSettingDAL();
            AccountDAL ad = new AccountDAL();
            while (rs.next()) {
                Lesson l = new Lesson();
                l.setChapter(getChapterByID(rs.getInt("chapter_id")));
                l.setCreatedAt(rs.getTimestamp("created_at"));
                l.setCreatedBy(ad.getAccountByID(rs.getInt("created_by")));
                l.setDescription(rs.getString("description"));
                l.setDisplayOrder(rs.getInt("display_order"));
                l.setLessonID(rs.getInt("lesson_id"));
                l.setLessonName(rs.getString("lesson_name"));
                l.setLessonType(ssd.getSettingByID(rs.getInt("lesson_type")));
                l.setStatus(rs.getInt("status"));
                l.setUpdatedAt(rs.getTimestamp("updated_at"));
                l.setUpdatedBy(ad.getAccountByID(rs.getInt("updated_by")));
                list.add(l);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    public void updLessonVideo(Lesson l) {
        try {
            String sql = "UPDATE lesson SET"
                    + " chapter_id = ?, lesson_name = ?, lesson_type = ?, display_order = ?, video_link = ?, description = ?, class_id = ?, quiz_id = ?, asm_id = ?, created_by = ?, created_at = ?, status = ?, attatched_file = ?, updated_by = ?, updated_at = ?"
                    + " WHERE lesson_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, l.getChapter().getChapterID());
            System.out.println(1);
            st.setString(2, l.getLessonName());
            System.out.println(2);
            st.setInt(3, l.getLessonType().getSettingID());
            System.out.println(3);
            st.setInt(4, l.getDisplayOrder());
            System.out.println(4);
            st.setString(5, l.getVideoLink());
            System.out.println(5);
            st.setString(6, l.getDescription());
            System.out.println(6);
            if (l.getCls() != null) {
                st.setInt(7, l.getCls().getClassID());
            } else {
                st.setNull(7, java.sql.Types.INTEGER);
            }
            if (l.getQuiz() != null) {
                st.setInt(8, l.getQuiz().getQuizID());
            } else {
                st.setNull(8, java.sql.Types.INTEGER);
            }
            if (l.getAsm() != null) {
                st.setInt(9, l.getAsm().getAsmID());
            } else {
                st.setNull(9, java.sql.Types.INTEGER);
            }
            st.setInt(10, l.getCreatedBy().getAccountID());
            st.setTimestamp(11, l.getCreatedAt());
            st.setInt(12, l.getStatus());
            st.setString(13, null);
            st.setInt(14, l.getUpdatedBy().getAccountID());
            st.setTimestamp(15, l.getUpdatedAt());
            st.setInt(16, l.getLessonID());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println("da" + e);
        }
    }
    public void submitAssignment(int asmID,int accountID,String answer){
        AccountDAL ad = new AccountDAL();
        Instant instant = Instant.now();
        Timestamp now = Timestamp.from(instant);
        try{
            String sql = "INSERT INTO assignment_submit"
                    + " (asm_id, account_id,answer, date)"
                    + " VALUES (?, ?, ?, ?)";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1,asmID);
            st.setInt(2,accountID);
            st.setString(3,answer);
            st.setTimestamp(4,now);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
