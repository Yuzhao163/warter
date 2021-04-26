package com.water.water.pojo;

public class TmnData {

    private Integer id;
    private String TmnID;
    private String TmnName;
    private String U1TmnID;
    private String U2TmnID;
    private String D1TmnID;
    private String D2TmnID;
    private String ConPont1;
    private String ConPont2;
    private String ConPont3;
    private String TmnDesc;
    private String PipID;
    private String PipName;
    private String AreaID;
    private String AreaName;


    @Override
    public String toString() {
        return "TmnData{" +
                "id=" + id +
                ", TmnID='" + TmnID + '\'' +
                ", TmnName='" + TmnName + '\'' +
                ", U1TmnID='" + U1TmnID + '\'' +
                ", U2TmnID='" + U2TmnID + '\'' +
                ", D1TmnID='" + D1TmnID + '\'' +
                ", D2TmnID='" + D2TmnID + '\'' +
                ", ConPont1='" + ConPont1 + '\'' +
                ", ConPont2='" + ConPont2 + '\'' +
                ", ConPont3='" + ConPont3 + '\'' +
                ", TmnDesc='" + TmnDesc + '\'' +
                ", PipID='" + PipID + '\'' +
                ", PipName='" + PipName + '\'' +
                ", AreaID='" + AreaID + '\'' +
                ", AreaName='" + AreaName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTmnID() {
        return TmnID;
    }

    public void setTmnID(String tmnID) {
        TmnID = tmnID;
    }

    public String getTmnName() {
        return TmnName;
    }

    public void setTmnName(String tmnName) {
        TmnName = tmnName;
    }

    public String getU1TmnID() {
        return U1TmnID;
    }

    public void setU1TmnID(String u1TmnID) {
        U1TmnID = u1TmnID;
    }

    public String getU2TmnID() {
        return U2TmnID;
    }

    public void setU2TmnID(String u2TmnID) {
        U2TmnID = u2TmnID;
    }

    public String getD1TmnID() {
        return D1TmnID;
    }

    public void setD1TmnID(String d1TmnID) {
        D1TmnID = d1TmnID;
    }

    public String getD2TmnID() {
        return D2TmnID;
    }

    public void setD2TmnID(String d2TmnID) {
        D2TmnID = d2TmnID;
    }

    public String getConPont1() {
        return ConPont1;
    }

    public void setConPont1(String conPont1) {
        ConPont1 = conPont1;
    }

    public String getConPont2() {
        return ConPont2;
    }

    public void setConPont2(String conPont2) {
        ConPont2 = conPont2;
    }

    public String getConPont3() {
        return ConPont3;
    }

    public void setConPont3(String conPont3) {
        ConPont3 = conPont3;
    }

    public String getTmnDesc() {
        return TmnDesc;
    }

    public void setTmnDesc(String tmnDesc) {
        TmnDesc = tmnDesc;
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
}
