package com.water.water.pojo;

import java.sql.Array;
import java.sql.Timestamp;
import java.util.List;

public class Area_Pip_Tmn_Leader {
    private Integer UserID;
    private String UserName;
    private String UserPswd;
    private String UClassID;
    private Integer Ustate;
    private Timestamp RegTime;
    private String MoPhone;
    private String RealName;
    private String DPTName;
    private String AreaID;
    private String AreaName;
    private String PipID;
    private String PipName;
    private String TmnId;
    private String TmnName;
    private String Area_Pip_Tmn;

    public Area_Pip_Tmn_Leader(Integer userID, String userName, String userPswd, String UClassID, Integer ustate, Timestamp regTime, String moPhone, String realName, String DPTName, String areaID, String areaName, String pipID, String pipName, String tmnId, String tmnName, String area_Pip_Tmn) {
        UserID = userID;
        UserName = userName;
        UserPswd = userPswd;
        this.UClassID = UClassID;
        Ustate = ustate;
        RegTime = regTime;
        MoPhone = moPhone;
        RealName = realName;
        this.DPTName = DPTName;
        AreaID = areaID;
        AreaName = areaName;
        PipID = pipID;
        PipName = pipName;
        TmnId = tmnId;
        TmnName = tmnName;
        Area_Pip_Tmn = area_Pip_Tmn;
    }

    public String getArea_Pip_Tmn() {
        return Area_Pip_Tmn;
    }

    public void setArea_Pip_Tmn(String area_Pip_Tmn) {
        Area_Pip_Tmn = area_Pip_Tmn;
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

    public String getAreaID() {
        return AreaID;
    }

    public void setAreaID(String areaID) {
        AreaID = areaID;
    }

    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String areaName) {
        AreaName = areaName;
    }

    public String getPipID() {
        return PipID;
    }

    public void setPipID(String pipID) {
        PipID = pipID;
    }

    public String getPipName() {
        return PipName;
    }

    public void setPipName(String pipName) {
        PipName = pipName;
    }

    public String getTmnId() {
        return TmnId;
    }

    public void setTmnId(String tmnId) {
        TmnId = tmnId;
    }

    public String getTmnName() {
        return TmnName;
    }

    public void setTmnName(String tmnName) {
        TmnName = tmnName;
    }

    @Override
    public String toString() {
        return "Area_Pip_Tmn_Leader{" +
                "UserID='" + UserID + '\'' +
                ", UserName='" + UserName + '\'' +
                ", UserPswd='" + UserPswd + '\'' +
                ", UClassID='" + UClassID + '\'' +
                ", Ustate=" + Ustate +
                ", RegTime=" + RegTime +
                ", MoPhone='" + MoPhone + '\'' +
                ", RealName='" + RealName + '\'' +
                ", DPTName='" + DPTName + '\'' +
                ", AreaID='" + AreaID + '\'' +
                ", AreaName='" + AreaName + '\'' +
                ", PipID='" + PipID + '\'' +
                ", PipName='" + PipName + '\'' +
                ", TmnId='" + TmnId + '\'' +
                ", TmnName='" + TmnName + '\'' +
                '}';
    }
}
