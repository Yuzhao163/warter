package com.water.water.pojo;

public class Terminals {
    Integer id;
    String TmnId;
    String TmnName;
    String U1TmnID;
    String U2TmnID;
    String D1TmnID;
    String D2TmnID;
    String ConPont1;
    String ConPont2;
    String ConPont3;
    String TmnDesc;

    public Terminals(Integer id, String tmnId, String tmnName, String u1TmnID, String u2TmnID, String d1TmnID, String d2TmnID, String conPont1, String conPont2, String conPont3, String tmnDesc) {
        this.id = id;
        TmnId = tmnId;
        TmnName = tmnName;
        U1TmnID = u1TmnID;
        U2TmnID = u2TmnID;
        D1TmnID = d1TmnID;
        D2TmnID = d2TmnID;
        ConPont1 = conPont1;
        ConPont2 = conPont2;
        ConPont3 = conPont3;
        TmnDesc = tmnDesc;
    }

    public Terminals() {
    }

    @Override
    public String toString() {
        return "Terminals{" +
                "id=" + id +
                ", TmnId='" + TmnId + '\'' +
                ", TmnName='" + TmnName + '\'' +
                ", U1TmnID='" + U1TmnID + '\'' +
                ", U2TmnID='" + U2TmnID + '\'' +
                ", D1TmnID='" + D1TmnID + '\'' +
                ", D2TmnID='" + D2TmnID + '\'' +
                ", ConPont1='" + ConPont1 + '\'' +
                ", ConPont2='" + ConPont2 + '\'' +
                ", ConPont3='" + ConPont3 + '\'' +
                ", TmnDesc='" + TmnDesc + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTmnId() {
        return TmnId;
    }

    public void setTmnId(String tmnId) {
        TmnId = tmnId;
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
}
