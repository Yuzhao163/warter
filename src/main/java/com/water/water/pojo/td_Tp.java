package com.water.water.pojo;

public class td_Tp {
    private Integer Id;
    private String TmnID;
    private String PipID;
    private Integer PTid;

    @Override
    public String toString() {
        return "td_Tp{" +
                "Id=" + Id +
                ", TmnID='" + TmnID + '\'' +
                ", PipID='" + PipID + '\'' +
                ", PTid=" + PTid +
                '}';
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getTmnID() {
        return TmnID;
    }

    public void setTmnID(String tmnID) {
        TmnID = tmnID;
    }

    public String getPipID() {
        return PipID;
    }

    public void setPipID(String pipID) {
        PipID = pipID;
    }

    public Integer getPTid() {
        return PTid;
    }

    public void setPTid(Integer PTid) {
        this.PTid = PTid;
    }
}
