package com.water.water.pojo;

import java.sql.Timestamp;
import java.util.List;


public class UserManageRight {
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
    private Integer Right_PP;
    private List AreaID;
    private List PipID;
    private List TmnID;

    public UserManageRight() {
    }

    public UserManageRight(Integer userID, String userName, String userPswd, String UClassID, Integer ustate, Timestamp regTime, String moPhone, String realName, String DPTName, Timestamp modTime, Integer right_PP, List areaID, List pipID, List tmnID) {
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
        Right_PP = right_PP;
        AreaID = areaID;
        PipID = pipID;
        TmnID = tmnID;
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

    public Timestamp getModTime() {
        return ModTime;
    }

    public void setModTime(Timestamp modTime) {
        ModTime = modTime;
    }

    public Integer getRight_PP() {
        return Right_PP;
    }

    public void setRight_PP(Integer right_PP) {
        Right_PP = right_PP;
    }

    public List getAreaID() {
        return AreaID;
    }

    public void setAreaID(List areaID) {
        AreaID = areaID;
    }

    public List getPipID() {
        return PipID;
    }

    public void setPipID(List pipID) {
        PipID = pipID;
    }

    public List getTmnID() {
        return TmnID;
    }

    public void setTmnID(List tmnID) {
        TmnID = tmnID;
    }

    @Override
    public String toString() {
        return "UserManageRight{" +
                "UserID=" + UserID +
                ", UserName='" + UserName + '\'' +
                ", UserPswd='" + UserPswd + '\'' +
                ", UClassID='" + UClassID + '\'' +
                ", Ustate=" + Ustate +
                ", RegTime=" + RegTime +
                ", MoPhone='" + MoPhone + '\'' +
                ", RealName='" + RealName + '\'' +
                ", DPTName='" + DPTName + '\'' +
                ", ModTime=" + ModTime +
                ", Right_PP=" + Right_PP +
                ", AreaID=" + AreaID +
                ", PipID=" + PipID +
                ", TmnID=" + TmnID +
                '}';
    }
}