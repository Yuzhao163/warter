package com.water.water.pojo;

import java.sql.Date;
import java.sql.Timestamp;

public class td_error_rec {
    private Long ERId;
    private String Error_Position;
    private String if_deal;
    private Timestamp Time;
    private String TmnID;

    public Long getERId() {
        return ERId;
    }

    public void setERId(Long ERId) {
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

    public Timestamp getTime() {
        return Time;
    }

    public void setTime(Timestamp time) {
        Time = time;
    }

    public String getTmnID() {
        return TmnID;
    }

    public void setTmnID(String tmnID) {
        TmnID = tmnID;
    }

    @Override
    public String toString() {
        return "td_error_rec{" +
                "ERId=" + ERId +
                ", Error_Position='" + Error_Position + '\'' +
                ", if_deal='" + if_deal + '\'' +
                ", Time=" + Time +
                ", TmnID='" + TmnID + '\'' +
                '}';
    }
}
