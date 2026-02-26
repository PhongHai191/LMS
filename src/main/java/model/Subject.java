package model;
public class Subject {
    private int subjectID;
    private String subjectCode;
    private String subjectName;
    private String subjectDescription;
    private int status;
    private Account manager;
    private int displayOrder;
    public Subject() {
    }
    public Subject(String subjectID, String subjectName, String subjectDescription) {


    }
    public Subject(int subjectID, String subjectName, String subjectDescription) {

        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.subjectDescription = subjectDescription;
    }

    public Subject(int subjectID, String subjectName, String subjectDescription, int status) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.subjectDescription = subjectDescription;
        this.status = status;
    }

    public Subject(int subjectID, String subjectName, String subjectDescription, int status, Account manager) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.subjectDescription = subjectDescription;
        this.status = status;
        this.manager = manager;
    }

    public Account getManager() {
        return manager;
    }

    public void setManager(Account manager) {
        this.manager = manager;
    }

    public int getSubjectID() {
        return subjectID;
    }
    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }



    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectDescription() {
        return subjectDescription;
    }

    public void setSubjectDescription(String subjectDescription) {
        this.subjectDescription = subjectDescription;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }



    @Override
    public String toString() {
        return "Subject{" + "subjectID=" + subjectID + ", subjectCode=" + subjectCode + ", subjectName=" + subjectName + ", subjectDescription=" + subjectDescription + ", status=" + status + ", manager=" + manager + '}';
    }


}