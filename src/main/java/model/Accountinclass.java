package model;

import java.sql.Time;
import java.util.Date;

public class Accountinclass {
    private int classid, userid;
    private String username, semester, subjectcode;
    private Date startdate, enddate;
    private Time starttime, endtime;

    public Accountinclass() {
    }


    public Accountinclass(int classid, String username, String semester, String subjectcode, Date startdate, Date enddate, Time starttime, Time endtime) {
        this.classid = classid;
        this.username = username;
        this.semester = semester;
        this.subjectcode = subjectcode;
        this.startdate = startdate;
        this.enddate = enddate;
        this.starttime = starttime;
        this.endtime = endtime;

    }

    public Accountinclass(int classid, String username) {
        this.classid = classid;
        this.username = username;
    }

    public Accountinclass(int classid, int userid, String username, String semester, String subjectcode, Date startdate, Date enddate, Time starttime, Time endtime) {
        this.classid = classid;
        this.userid = userid;
        this.username = username;
        this.semester = semester;
        this.subjectcode = subjectcode;
        this.startdate = startdate;
        this.enddate = enddate;
        this.starttime = starttime;
        this.endtime = endtime;
    }

    public int getClassid() {
        return classid;
    }

    public void setClassid(int classid) {
        this.classid = classid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getSubjectcode() {
        return subjectcode;
    }

    public void setSubjectcode(String subjectcode) {
        this.subjectcode = subjectcode;
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

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}