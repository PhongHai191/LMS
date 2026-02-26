package model;

public class Question_Answer {

    private int questionId;
    private String answerOption;
    private String answerContent;
    private boolean isKey;

    public Question_Answer() {
    }

    public Question_Answer(int questionId, String answerOption, String answerContent, boolean isKey) {
        this.questionId = questionId;
        this.answerOption = answerOption;
        this.answerContent = answerContent;
        this.isKey = isKey;
    }



    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getAnswerOption() {
        return answerOption;
    }

    public void setAnswerOption(String answerOption) {
        this.answerOption = answerOption;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public boolean isKey() {
        return isKey;
    }

    public void setKey(boolean key) {
        this.isKey = key;
    }


}
