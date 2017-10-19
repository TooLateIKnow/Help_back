package com.help.pojo;

public class Phinfo {
    private int userId;
    private String prohelpInfo;
    private int AFHuserId;
    private String time;
    private String location;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAFHuserId() {
        return AFHuserId;
    }

    public void setAFHuserId(int AFHuserId) {
        this.AFHuserId = AFHuserId;
    }

    public String getProhelpInfo() {
        return prohelpInfo;
    }

    public void setProhelpInfo(String prohelpInfo) {
        this.prohelpInfo = prohelpInfo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
