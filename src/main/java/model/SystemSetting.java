package model;

public class SystemSetting {
    int settingID;
    String settingName;
    String settingType;
    int displayOrder;
    int status;

    public SystemSetting() {
    }

    public SystemSetting(int settingID, String settingName, String settingType, int displayOrder, int status) {
        this.settingID = settingID;
        this.settingName = settingName;
        this.settingType = settingType;
        this.displayOrder = displayOrder;
        this.status = status;
    }




    public int getSettingID() {
        return settingID;
    }

    public void setSettingID(int settingID) {
        this.settingID = settingID;
    }

    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    public String getSettingType() {
        return settingType;
    }

    public void setSettingType(String settingType) {
        this.settingType = settingType;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SystemSetting{" +
                "settingID=" + settingID +
                ", settingName='" + settingName + '\'' +
                ", settingType='" + settingType + '\'' +
                ", displayOrder=" + displayOrder +
                ", status=" + status +
                '}';
    }
}
