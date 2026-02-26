package model;

import java.sql.Timestamp;

public class Assignment {


    private int asmID;
    private String asmName;
    private String asmDes;
    private Subject subject;
    private Chapter chapter;
    private Lesson lesson;
    private Timestamp dl;
    private Account createdBy;
    private Timestamp createdAt;
    private Account updatedBy;
    private Timestamp updatedAt;
    private int status;
    private int totalStudents;
    private int submittedStudents;
    private int remainingStudents;


    public int getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(int totalStudents) {
        this.totalStudents = totalStudents;
    }

    public int getSubmittedStudents() {
        return submittedStudents;
    }

    public void setSubmittedStudents(int submittedStudents) {
        this.submittedStudents = submittedStudents;
    }

    public int getRemainingStudents() {
        return remainingStudents;
    }

    public void setRemainingStudents(int remainingStudents) {
        this.remainingStudents = remainingStudents;
    }

    public Assignment() {
    }

    public int getAsmID() {
        return asmID;
    }

    public void setAsmID(int asmID) {
        this.asmID = asmID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }



    public String getAsmName() {
        return asmName;
    }

    public void setAsmName(String asmName) {
        this.asmName = asmName;
    }

    public String getAsmDes() {
        return asmDes;
    }

    public void setAsmDes(String asmDes) {
        this.asmDes = asmDes;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject s) {
        this.subject = s;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter c) {
        this.chapter = c;
    }

    public Timestamp getDl() {
        return dl;
    }

    public void setDl(Timestamp dl) {
        this.dl = dl;
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

    @Override
    public String toString() {
        return "Assignment{" + "asmID=" + asmID + ", asmName=" + asmName + ", asmDes=" + asmDes + ", subject=" + subject + ", chapter=" + chapter + ", lesson=" + lesson + ", dl=" + dl + ", createdBy=" + createdBy + ", createdAt=" + createdAt + ", updatedBy=" + updatedBy + ", updatedAt=" + updatedAt + ", status=" + status + ", total_students=" + totalStudents + ", submitted_students=" + submittedStudents + ", remaining_students=" + remainingStudents + '}';
    }




}
