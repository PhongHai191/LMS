package model;

import java.sql.Date;

public class Account {
    int accountID;
    String username;
    String password;
    String email;
    int role;
    int status;
    String fullName;
    String mobile;
    Date dob;
    String description;
    private SystemSetting systemSetting;

    public Account() {

    }


    public Account(int accountID, String username, String password, String email, int role, int status, String fullName, String mobile, Date dob, String description, SystemSetting systemSetting) {
        this.accountID = accountID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.status = status;
        this.fullName = fullName;
        this.mobile = mobile;
        this.dob = dob;
        this.description = description;
        this.systemSetting = systemSetting;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SystemSetting getSystemSetting() {
        return systemSetting;
    }

    public void setSystemSetting(SystemSetting systemSetting) {
        this.systemSetting = systemSetting;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountID=" + accountID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", status=" + status +
                ", fullName='" + fullName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", dob=" + dob +
                ", description='" + description + '\'' +
                ", systemSetting=" + systemSetting +
                '}';
    }
}
