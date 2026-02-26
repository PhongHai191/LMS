package model;

import java.sql.Timestamp;
import java.util.List;

public class Chapter {
    private int chapterID;
    private Subject subject;
    private String chapterName;
    private int displayOrder;
    private String description;
    private Account createdBy;
    private Timestamp createdAt;
    private Account updatedBy;
    private Timestamp updatedAt;
    private int status;
    private int numAchieve;
    private boolean achieve;
    private List<Assignment> asm;


    public Chapter() {

    }

    public Chapter(int chapterID, Subject subject, String chapterName, int displayOrder, String description, Account createdBy, Timestamp createdAt, Account updatedBy, Timestamp updatedAt, int status, List<Assignment> asm) {
        this.chapterID = chapterID;
        this.subject = subject;
        this.chapterName = chapterName;
        this.displayOrder = displayOrder;
        this.description = description;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
        this.status = status;
        this.asm = asm;
    }

    public Chapter(int chapterID, Subject subject, String chapterName, int displayOrder, String description, Account createdBy, Timestamp createdAt, Account updatedBy, Timestamp updatedAt, int status) {
        this.chapterID = chapterID;
        this.subject = subject;
        this.chapterName = chapterName;
        this.displayOrder = displayOrder;
        this.description = description;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public int getNumAchieve() {
        return numAchieve;
    }

    public void setNumAchieve(int numAchieve) {
        this.numAchieve = numAchieve;
    }

    public boolean isAchieve() {
        return achieve;
    }

    public void setAchieve(boolean achieve) {
        this.achieve = achieve;
    }



    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Assignment> getAsm() {
        return asm;
    }

    public void setAsm(List<Assignment> asm) {
        this.asm = asm;
    }



    public int getChapterID() {
        return chapterID;
    }

    public void setChapterID(int chapterID) {
        this.chapterID = chapterID;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return "Chapter{" + "chapterID=" + chapterID + ", subject=" + subject + ", chapterName=" + chapterName + ", displayOrder=" + displayOrder + ", description=" + description + ", createdBy=" + createdBy + ", createdAt=" + createdAt + ", updatedBy=" + updatedBy + ", updatedAt=" + updatedAt + ", status=" + status + ", numAchieve=" + numAchieve + ", achieve=" + achieve + ", asm=" + asm + '}';
    }


}
