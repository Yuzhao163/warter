package com.water.water.pojo;

public class td_Ap {
    private Integer id;
    private String AreaID;
    private String PipID;


    public td_Ap(Integer id, String areaID, String pipID) {
        this.id = id;
        AreaID = areaID;
        PipID = pipID;
    }


    public td_Ap() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaID() {
        return AreaID;
    }

    public void setAreaID(String areaID) {
        AreaID = areaID;
    }

    public String getPipID() {
        return PipID;
    }

    public void setPipID(String pipID) {
        PipID = pipID;
    }


    @Override
    public String toString() {
        return "td_ap{" +
                "id=" + id +
                ", AreaID='" + AreaID + '\'' +
                ", PipID='" + PipID + '\'' +
                '}';
    }
}
