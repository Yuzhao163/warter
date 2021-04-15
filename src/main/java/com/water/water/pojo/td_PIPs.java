package com.water.water.pojo;

import java.sql.Date;

public class td_PIPs {
    private String PipID;
    private String PipName;
    private String PipDesc;
    private Date PipCreateDate;
    private String AreaID;
    private String PipLeader;
    private String PipLeadPhone;

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

    public Date getPipCreateDate() {
        return PipCreateDate;
    }

    public void setPipCreateDate(Date pipCreateDate) {
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
