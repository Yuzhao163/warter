package com.water.water.pojo;

public class td_Ap {

    private Integer id;
    private String PipID;
    private String AreaID;

    @Override
    public String toString() {
        return "td_Ap{" +
                "id=" + id +
                ", PipID='" + PipID + '\'' +
                ", AreaID='" + AreaID + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPipID() {
        return PipID;
    }

    public void setPipID(String pipID) {
        PipID = pipID;
    }

    public String getAreaID() {
        return AreaID;
    }

    public void setAreaID(String areaID) {
        AreaID = areaID;
    }

}
