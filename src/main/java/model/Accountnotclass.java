package model;

public class Accountnotclass {
    private String username, fullname, email;
    private int studentid;
    public Accountnotclass() {
    }

    public Accountnotclass(String username, String fullname, String email) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
    }

    public Accountnotclass(int studentid,String username, String fullname, String email) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.studentid = studentid;

    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }
}