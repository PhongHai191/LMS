package dal;

import model.Quiz;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class QuizDAL extends DBContext{

    public Quiz getQuizByLessonID(int lesson) {
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        AccountDAL ad = new AccountDAL();
        try {
            String sql = "SELECT quiz.* FROM lesson JOIN quiz ON lesson.quiz_id = quiz.quiz_id WHERE lesson_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, lesson);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {


                Quiz q = new Quiz();
                q.setChapter(ssd.getChapterByID(rs.getInt("chapter_id")));
                q.setCreatedAt((rs.getTimestamp("created_at")));
                q.setCreatedBy(ad.getAccountByID(rs.getInt("created_by")));
                q.setUpdatedAt((rs.getTimestamp("updated_at")));
                q.setUpdatedBy(ad.getAccountByID(rs.getInt("updated_by")));
                q.setDisplayOrder(rs.getInt("display_order"));
                q.setNoQ(rs.getInt("noQ"));
                q.setQuizID(rs.getInt("quiz_id"));
                q.setQuizName(rs.getString("quiz_name"));
                q.setTimeLimit(rs.getDouble("time_limit"));
                q.setDescription(rs.getString("description"));
                return q;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
