package com.water.water.pojo;

import java.sql.Date;

public class td_Areas {
    private String AreaID;
    private String AreaName;
    private String AreaLeader;
    private Date AreaCreateDate;
    private String AreaLeadPhone;

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

    public String getAreaLeader() {
        return AreaLeader;
    }

    public void setAreaLeader(String areaLeader) {
        AreaLeader = areaLeader;
    }

    public Date getAreaCreateDate() {
        return AreaCreateDate;
    }

    public void setAreaCreateDate(Date areaCreateDate) {
        AreaCreateDate = areaCreateDate;
    }

    public String getAreaLeadPhone() {
        return AreaLeadPhone;
    }

    public void setAreaLeadPhone(String areaLeadPhone) {
        AreaLeadPhone = areaLeadPhone;
    }
}
