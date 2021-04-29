package com.water.water.pojo;

import java.sql.Date;
import java.sql.Timestamp;

public class td_error_rec {
    private Short ERId;
    private String Error_Position;
    private String if_deal;
    private Date Time;
    private String TmnID;

    public Short getERId() {
        return ERId;
    }

    public void setERId(Short ERId) {
        this.ERId = ERId;
    }

    public String getError_Position() {
        return Error_Position;
    }

    public void setError_Position(String error_Position) {
        Error_Position = error_Position;
    }

    public String getIf_deal() {
        return if_deal;
    }

    public void setIf_deal(String if_deal) {
        this.if_deal = if_deal;
    }

    public Date getTime() {
        return Time;
    }

    public void setTime(Date time) {
        Time = time;
    }

    public String getTmnID() {
        return TmnID;
    }

    public void setTmnID(String tmnID) {
        TmnID = tmnID;
    }
}
