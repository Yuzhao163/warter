package com.water.water.pojo;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

public class Rec_Detail {
    private Long PackageID;
    private Integer Id;
    private String TmnID;
    private String D_ID;
    private Short V_status;
    private Short V_per;
    private Short W_line;
    private Short B_status;
    private Short O_temp;
    private Short E_temp;
    private Short D_doorsta;
    private Short W_work;
    private Short F_Volume;
    private Short OV_period;
    private Short OV_waterline;
    private Short OV_keeptime;
    private Short CV_waterline;
    private Short V_actiontime;

    @Override
    public String toString() {
        return "Rec_Detail{" +
                "PackageID=" + PackageID +
                ", Id=" + Id +
                ", TmnID='" + TmnID + '\'' +
                ", D_ID='" + D_ID + '\'' +
                ", V_status=" + V_status +
                ", V_per=" + V_per +
                ", W_line=" + W_line +
                ", B_status=" + B_status +
                ", O_temp=" + O_temp +
                ", E_temp=" + E_temp +
                ", D_doorsta=" + D_doorsta +
                ", W_work=" + W_work +
                ", F_Volume=" + F_Volume +
                ", OV_period=" + OV_period +
                ", OV_waterline=" + OV_waterline +
                ", OV_keeptime=" + OV_keeptime +
                ", CV_waterline=" + CV_waterline +
                ", V_actiontime=" + V_actiontime +
                ", UD_period=" + UD_period +
                ", C_times=" + C_times +
                ", Send_error=" + Send_error +
                ", Rece_error=" + Rece_error +
                ", Create_time=" + Create_time +
                ", Update_time=" + Update_time +
                '}';
    }

    private Short UD_period;

    public Long getPackageID() {
        return PackageID;
    }

    public void setPackageID(Long packageID) {
        PackageID = packageID;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Short getV_actiontime() {
        return V_actiontime;
    }

    public void setV_actiontime(Short v_actiontime) {
        V_actiontime = v_actiontime;
    }

    public Short getRece_error() {
        return Rece_error;
    }

    public void setRece_error(Short rece_error) {
        Rece_error = rece_error;
    }

    public Date getCreate_time() {
        return Create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        Create_time = create_time;
    }

    public Date getUpdate_time() {
        return Update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        Update_time = update_time;
    }

    private Short C_times;

    public String getTmnID() {
        return TmnID;
    }

    public void setTmnID(String tmnID) {
        TmnID = tmnID;
    }

    public String getD_ID() {
        return D_ID;
    }

    public void setD_ID(String d_ID) {
        D_ID = d_ID;
    }

    public Short getV_status() {
        return V_status;
    }

    public void setV_status(Short v_status) {
        V_status = v_status;
    }

    public Short getV_per() {
        return V_per;
    }

    public void setV_per(Short v_per) {
        V_per = v_per;
    }

    public Short getW_line() {
        return W_line;
    }

    public void setW_line(Short w_line) {
        W_line = w_line;
    }

    public Short getB_status() {
        return B_status;
    }

    public void setB_status(Short b_status) {
        B_status = b_status;
    }

    public Short getO_temp() {
        return O_temp;
    }

    public void setO_temp(Short o_temp) {
        O_temp = o_temp;
    }

    public Short getE_temp() {
        return E_temp;
    }

    public void setE_temp(Short e_temp) {
        E_temp = e_temp;
    }

    public Short getD_doorsta() {
        return D_doorsta;
    }

    public void setD_doorsta(Short d_doorsta) {
        D_doorsta = d_doorsta;
    }

    public Short getW_work() {
        return W_work;
    }

    public void setW_work(Short w_work) {
        W_work = w_work;
    }

    public Short getF_Volume() {
        return F_Volume;
    }

    public void setF_Volume(Short f_Volume) {
        F_Volume = f_Volume;
    }

    public Short getOV_period() {
        return OV_period;
    }

    public void setOV_period(Short OV_period) {
        this.OV_period = OV_period;
    }

    public Short getOV_waterline() {
        return OV_waterline;
    }

    public void setOV_waterline(Short OV_waterline) {
        this.OV_waterline = OV_waterline;
    }

    public Short getOV_keeptime() {
        return OV_keeptime;
    }

    public void setOV_keeptime(Short OV_keeptime) {
        this.OV_keeptime = OV_keeptime;
    }

    public Short getCV_waterline() {
        return CV_waterline;
    }

    public void setCV_waterline(Short CV_waterline) {
        this.CV_waterline = CV_waterline;
    }

    public Short getUD_period() {
        return UD_period;
    }

    public void setUD_period(Short UD_period) {
        this.UD_period = UD_period;
    }

    public Short getC_times() {
        return C_times;
    }

    public void setC_times(Short c_times) {
        C_times = c_times;
    }

    public Short getSend_error() {
        return Send_error;
    }

    public void setSend_error(Short send_error) {
        Send_error = send_error;
    }

    private Short Send_error;
    private Short Rece_error;
    private Timestamp Create_time;
    private Timestamp Update_time;

}
