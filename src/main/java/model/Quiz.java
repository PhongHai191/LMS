package model;

import java.sql.Timestamp;
import java.util.List;

public class Quiz {


    private int quizID;
    private String quizName;
    private Chapter chapter;
    private Subject subject;
    private String type;
    private int noQ;
    private Class cls;
    private double timeLimit;
    private int displayOrder;
    private Account createdBy;
    private Timestamp createdAt;
    private Account updatedBy;
    private Timestamp updatedAt;
    private String description;
    private boolean status;
    private List<Quiz_Question> questions;
    private double pass;


    public Quiz() {

    }

    public Quiz(int quizID, String quizName, Chapter chapter, Subject subject, String type, int noQ, double timeLimit, int displayOrder, Account createdBy, Timestamp createdAt, Account updatedBy, Timestamp updatedAt, String description, boolean status) {
        this.quizID = quizID;
        this.quizName = quizName;
        this.chapter = chapter;
        this.subject = subject;
        this.type = type;
        this.noQ = noQ;
        this.timeLimit = timeLimit;
        this.displayOrder = displayOrder;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
        this.description = description;
        this.status = status;
    }

    public Quiz(int quizID, String quizName, Chapter chapter, Subject subject, String type, int noQ, double timeLimit, int displayOrder, String description, boolean status, List<Quiz_Question> questions) {
        this.quizID = quizID;
        this.quizName = quizName;
        this.chapter = chapter;
        this.subject = subject;
        this.type = type;
        this.noQ = noQ;
        this.timeLimit = timeLimit;
        this.displayOrder = displayOrder;
        this.description = description;
        this.status = status;
        this.questions = questions;
    }

    public int getQuizID() {
        return quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNoQ() {
        return noQ;
    }

    public void setNoQ(int noQ) {
        this.noQ = noQ;
    }

    public double getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(double timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Account getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Account createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Account getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Account updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getPass() {
        return pass;
    }

    public void setPass(double pass) {
        this.pass = pass;
    }


}
