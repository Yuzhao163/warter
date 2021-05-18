package com.water.water.pojo;

import java.sql.Timestamp;

public class td_Areas {
    private String AreaID;
    private String AreaName;
    private Timestamp AreaCreateDate;

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

    public Timestamp getAreaCreateDate() {
        return AreaCreateDate;
    }

    public void setAreaCreateDate(Timestamp areaCreateDate) {
        AreaCreateDate = areaCreateDate;
    }
}
