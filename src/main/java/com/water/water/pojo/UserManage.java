package com.water.water.pojo;

import java.sql.Date;
import java.sql.Timestamp;


public class UserManage {
    private Integer UserID;
    private String UserName;
    private String UserPswd;
    private String UClassID;
    private Integer Ustate;
    private Timestamp RegTime;
    private String MoPhone;
    private String RealName;
    private String DPTName;
    private Timestamp ModTime;
    public UserManage() {
    }

    public UserManage(Integer userID, String userName, String userPswd, String UClassID, Integer ustate, Timestamp regTime, String moPhone, String realName, String DPTName, Timestamp modTime) {
        UserID = userID;
        UserName = userName;
        UserPswd = userPswd;
        this.UClassID = UClassID;
        Ustate = ustate;
        RegTime = regTime;
        MoPhone = moPhone;
        RealName = realName;
        this.DPTName = DPTName;
        ModTime = modTime;
    }

    public Timestamp getModTime() {
        return ModTime;
    }

    public void setModTime(Timestamp modTime) {
        ModTime = modTime;
    }

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer userID) {
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

    public Timestamp getRegTime() {
        return RegTime;
    }

    public void setRegTime(Timestamp regTime) {
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
}
