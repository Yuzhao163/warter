package com.water.water.pojo;

import java.sql.Date;
import java.sql.Timestamp;

public class td_PIPs {
    private String PipID;
    private String PipName;
    private String PipDesc;
    private Timestamp PipCreateDate;
    private String AreaID;
    private String PipLeader;
    private String PipLeadPhone;

    @Override
    public String toString() {
        return "td_PIPs{" +
                "PipID='" + PipID + '\'' +
                ", PipName='" + PipName + '\'' +
                ", PipDesc='" + PipDesc + '\'' +
                ", PipCreateDate=" + PipCreateDate +
                ", AreaID='" + AreaID + '\'' +
                ", PipLeader='" + PipLeader + '\'' +
                ", PipLeadPhone='" + PipLeadPhone + '\'' +
                '}';
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

    public String getPipDesc() {
        return PipDesc;
    }

    public void setPipDesc(String pipDesc) {
        PipDesc = pipDesc;
    }

    public Timestamp getPipCreateDate() {
        return PipCreateDate;
    }

    public void setPipCreateDate(Timestamp pipCreateDate) {
        PipCreateDate = pipCreateDate;
    }

    public String getAreaID() {
        return AreaID;
    }

    public void setAreaID(String areaID) {
        AreaID = areaID;
    }

    public String getPipLeadPhone() {
        return PipLeadPhone;
    }

    public void setPipLeadPhone(String pipLeadPhone) {
        PipLeadPhone = pipLeadPhone;
    }

    public String getPipLeader() {
        return PipLeader;
    }

    public void setPipLeader(String pipLeader) {
        PipLeader = pipLeader;
    }

}
