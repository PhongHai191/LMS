package model;

import java.sql.Date;

public class User {
    private String username,password,role,fullname,gender,email,mobile;
    private boolean status;
    private Date dob;

    public User() {
    }

    public User(String username, String password, String role, String fullname, String lname, String gender, String email, String mobile, boolean status, Date dob) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.fullname = fullname;
        this.gender = gender;
        this.email = email;
        this.mobile = mobile;
        this.status = status;
        this.dob = dob;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

}