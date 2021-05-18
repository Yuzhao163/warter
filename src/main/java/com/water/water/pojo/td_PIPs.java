package com.water.water.pojo;

import java.sql.Date;
import java.sql.Timestamp;

public class td_PIPs {
    private String PipID;
    private String PipName;
    private String PipDesc;
    private Timestamp PipCreateDate;

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
}

