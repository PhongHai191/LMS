package model;

import java.sql.Time;
import java.util.Date;

public class Dashboard {
    private int classid, status, subject;
    private String classname, majorid, subjectcode, semester, subjectname;
    private Time starttime, endtime;
    private Date startdate, enddate;

    private String startT, endT;
    private String startS, endS;

    public Dashboard() {
    }



    public Dashboard(int classid, int status, String classname, String majorid, String subjectcode, String semester, Time starttime, Time endtime, Date startdate, Date enddate) {
        this.classid = classid;
        this.status = status;
        this.classname = classname;
        this.majorid = majorid;
        this.subjectcode = subjectcode;
        this.semester = semester;
        this.starttime = starttime;
        this.endtime = endtime;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public Dashboard(int classid, int status, String classname, String majorid, String subjectcode, String semester, String subjectname, Time starttime, Time endtime, Date startdate, Date enddate) {
        this.classid = classid;
        this.status = status;
        this.classname = classname;
        this.majorid = majorid;
        this.subjectcode = subjectcode;
        this.semester = semester;
        this.subjectname = subjectname;
        this.starttime = starttime;
        this.endtime = endtime;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public int getSubject() {
        return subject;
    }

    public void setSubject(int subject) {
        this.subject = subject;
    }



    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
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



    public int getClassid() {
        return classid;
    }

    public void setClassid(int classid) {
        this.classid = classid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getMajorid() {
        return majorid;
    }

    public void setMajorid(String majorid) {
        this.majorid = majorid;
    }

    public String getSubjectcode() {
        return subjectcode;
    }

    public void setSubjectcode(String subjectcode) {
        this.subjectcode = subjectcode;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Time getStarttime() {
        return starttime;
    }

    public void setStarttime(Time starttime) {
        this.starttime = starttime;
    }

    public Time getEndtime() {
        return endtime;
    }

    public void setEndtime(Time endtime) {
        this.endtime = endtime;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }



}
