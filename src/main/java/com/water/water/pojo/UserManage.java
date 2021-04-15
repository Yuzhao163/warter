package com.water.water.pojo;

import java.sql.Date;


public class UserManage {
    private String UserID;
    private String UserName;
    private String UserPswd;
    private String UClassID;
    private Integer Ustate;
    private Date RegTime;
    private String MoPhone;
    private String RealName;
    private String DPTName;
    private Date ModTime;



    public UserManage() {
    }

    public UserManage(String userID, String userName, String userPswd, String UClassID, Integer ustate, Date regTime, String moPhone, String realName, String DPTName) {
        UserID = userID;
        UserName = userName;
        UserPswd = userPswd;
        this.UClassID = UClassID;
        Ustate = ustate;
        RegTime = regTime;
        MoPhone = moPhone;
        RealName = realName;
        this.DPTName = DPTName;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserPswd() {
        return UserPswd;
    }

    public void setUserPswd(String userPswd) {
        UserPswd = userPswd;
    }

    public String getUClassID() {
        return UClassID;
    }

    public void setUClassID(String UClassID) {
        this.UClassID = UClassID;
    }

    public Integer getUstate() {
        return Ustate;
    }

    public void setUstate(Integer ustate) {
        Ustate = ustate;
    }

    public Date getRegTime() {
        return RegTime;
    }

    public void setRegTime(Date regTime) {
        RegTime = regTime;
    }

    public String getMoPhone() {
        return MoPhone;
    }

    public void setMoPhone(String moPhone) {
        MoPhone = moPhone;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

    public String getDPTName() {
        return DPTName;
    }

    public void setDPTName(String DPTName) {
        this.DPTName = DPTName;
    }

    public Date getModTime() {
        return ModTime;
    }

    public void setModTime(Date modTime) {
        ModTime = modTime;
    }
}
