package com.water.water.pojo;

public class CommRec {
    private Integer PackageID;
    private String TmnID;
    private String PeerAddress;
    private String D_ID;
    private Integer W_work;
    private Integer V_pre;
    private Integer OV_period;
    private Integer OV_waterline;
    private Integer OV_keeptime;
    private Integer CV_waterline;
    private Integer V_actiontime;
    private Integer CmdStatus;

    @Override
    public String toString() {
        return "CommRec{" +
                "PackageID=" + PackageID +
                ", TmnID='" + TmnID + '\'' +
                ", PeerAddress='" + PeerAddress + '\'' +
                ", D_ID='" + D_ID + '\'' +
                ", W_work=" + W_work +
                ", V_pre=" + V_pre +
                ", OV_period=" + OV_period +
                ", OV_waterline=" + OV_waterline +
                ", OV_keeptime=" + OV_keeptime +
                ", CV_waterline=" + CV_waterline +
                ", V_actiontime=" + V_actiontime +
                ", CmdStatus=" + CmdStatus +
                '}';
    }

    public Integer getPackageID() {
        return PackageID;
    }

    public void setPackageID(Integer packageID) {
        PackageID = packageID;
    }

    public String getTmnID() {
        return TmnID;
    }

    public void setTmnID(String tmnID) {
        TmnID = tmnID;
    }

    public String getPeerAddress() {
        return PeerAddress;
    }

    public void setPeerAddress(String peerAddress) {
        PeerAddress = peerAddress;
    }

    public String getD_ID() {
        return D_ID;
    }

    public void setD_ID(String d_ID) {
        D_ID = d_ID;
    }

    public Integer getW_work() {
        return W_work;
    }

    public void setW_work(Integer w_work) {
        W_work = w_work;
    }

    public Integer getV_pre() {
        return V_pre;
    }

    public void setV_pre(Integer v_pre) {
        V_pre = v_pre;
    }

    public Integer getOV_period() {
        return OV_period;
    }

    public void setOV_period(Integer OV_period) {
        this.OV_period = OV_period;
    }

    public Integer getOV_waterline() {
        return OV_waterline;
    }

    public void setOV_waterline(Integer OV_waterline) {
        this.OV_waterline = OV_waterline;
    }

    public Integer getOV_keeptime() {
        return OV_keeptime;
    }

    public void setOV_keeptime(Integer OV_keeptime) {
        this.OV_keeptime = OV_keeptime;
    }

    public Integer getCV_waterline() {
        return CV_waterline;
    }

    public void setCV_waterline(Integer CV_waterline) {
        this.CV_waterline = CV_waterline;
    }

    public Integer getV_actiontime() {
        return V_actiontime;
    }

    public void setV_actiontime(Integer v_actiontime) {
        V_actiontime = v_actiontime;
    }

    public Integer getCmdStatus() {
        return CmdStatus;
    }

    public void setCmdStatus(Integer cmdStatus) {
        CmdStatus = cmdStatus;
    }

}