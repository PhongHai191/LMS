package model;

import java.sql.Timestamp;

public class Dimension {

    private int dimensionID;
    private Subject subject;
    private String dimensionName;
    private String type;
    private int displayOrder;
    private String description;
    private Account createdBy;
    private Timestamp createdAt;
    private Account updatedBy;
    private Timestamp updatedAt;
    private int status;
    private SystemSetting ssType;



    public Dimension() {
    }

    public Dimension(int dimensionID, Subject subject, String dimensionName, String type, int displayOrder, String description, Account createdBy, Timestamp createdAt, Account updatedBy, Timestamp updatedAt, int status) {
        this.dimensionID = dimensionID;
        this.subject = subject;
        this.dimensionName = dimensionName;
        this.type = type;
        this.displayOrder = displayOrder;
        this.description = description;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public Dimension(int aInt, Subject subject, String string, int aInt0, String string0, Account creater, Timestamp timestamp, Account updater, Timestamp timestamp0, int aInt1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getDimensionID() {
        return dimensionID;
    }

    public void setDimensionID(int dimensionID) {
        this.dimensionID = dimensionID;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getDimensionName() {
        return dimensionName;
    }

    public void setDimensionName(String dimensionName) {
        this.dimensionName = dimensionName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public SystemSetting getSsType() {
        return ssType;
    }

    public void setSsType(SystemSetting ssType) {
        this.ssType = ssType;
    }


}
