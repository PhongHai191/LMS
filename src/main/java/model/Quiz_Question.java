package model;

import java.util.List;

public class Quiz_Question {

    private int questionId, subjectId, chapterId;
    private String topic;
    private int displayOrder; //từ bảng quiz_question
    private boolean status;
    private List<Question_Answer> answer;

    public Quiz_Question() {
    }



    public Quiz_Question(int questionId, int subjectId, int chapterId, String topic, int displayOrder, boolean status, List<Question_Answer> answer) {
        this.questionId = questionId;
        this.subjectId = subjectId;
        this.chapterId = chapterId;
        this.topic = topic;
        this.displayOrder = displayOrder;
        this.status = status;
        this.answer = answer;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Question_Answer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Question_Answer> answer) {
        this.answer = answer;
    }

}
