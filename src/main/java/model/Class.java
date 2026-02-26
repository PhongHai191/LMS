package model;

import java.sql.Date;
import java.sql.Time;

public class Class {

    private int classID;
    private String className;
    private int activate;
    private Subject s;
    private SystemSetting semester;
    private Date startDate;
    private Date endDate;
    private Time startTime;

    private Time endTime;
    private Account trainer;

    private String startS;
    private String endS;
    private String startT;
    private String endT;
    private int displayOrder;
    private String description;

    public Class(int classID, String className, String majorID) {
        this.classID = classID;
        this.className = className;
    }

    public Class(int classID, String className, String majorID, int activate, SystemSetting semester, String majorName, Subject s) {
        this.classID = classID;
        this.className = className;

        this.s = s;
        this.activate = activate;
        this.semester = semester;
    }

    public Class() {
    }

    public Class(int classID, String className, Subject s) {
        this.classID = classID;
        this.className = className;
        this.s = s;
    }

    public Class(int classID, String className, String majorID, String majorName) {
        this.classID = classID;
        this.className = className;
    }

    public Class(int classID, String className, String majorID, String majorName, int activate, Subject s, SystemSetting semester, Date startDate, Date endDate, Time startTime, Time endTime) {
        this.classID = classID;
        this.className = className;

        this.activate = activate;
        this.s = s;
        this.semester = semester;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Class(int classID, String className, int activate, Subject s, SystemSetting semester, Date startDate, Date endDate, Time startTime, Time endTime, Account trainer, int displayOrder, String description) {
        this.classID = classID;
        this.className = className;
        this.activate = activate;
        this.s = s;
        this.semester = semester;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.trainer = trainer;
        this.displayOrder = displayOrder;
        this.description = description;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }



    public Account getTrainer() {
        return trainer;
    }

    public void setTrainer(Account trainer) {
        this.trainer = trainer;
    }



    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }



    public Subject getS() {
        return s;
    }

    public void setS(Subject s) {
        this.s = s;
    }

    public int getActivate() {
        return activate;
    }

    public void setActivate(int activate) {
        this.activate = activate;
    }

    public SystemSetting getSemester() {
        return semester;
    }

    public void setSemester(SystemSetting semester) {
        this.semester = semester;
    }



    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStartS() {
        return startS;
    }

    public void setStartS(String startS) {
        this.startS = startS;
    }

    public String getEndS() {
        return endS;
    }

    public void setEndS(String endS) {
        this.endS = endS;
    }

    public String getStartT() {
        return startT;
    }

    public void setStartT(String startT) {
        this.startT = startT;
    }

    public String getEndT() {
        return endT;
    }

    public void setEndT(String endT) {
        this.endT = endT;
    }



    @Override
    public String toString() {
        return "Class{" + "classID=" + classID + ", className=" + className + ", activate=" + activate + ", s=" + s + ", semester=" + semester + ", startDate=" + startDate + ", endDate=" + endDate + ", startTime=" + startTime + ", endTime=" + endTime + ", trainer=" + trainer + '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}