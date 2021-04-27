package com.water.water.pojo;

import java.sql.Date;
//异常展示实体类
public class ErrordealRec {
    private Short ERDId;
    private Short ERId;
    private String Exception;
    private String Result;
    private Date C_t;
    private String User;
    private Long PackageId;
    private String TmnId;
    private String TmnName;
    private String PipId;
    private Integer PTid;
    private String if_deal;
    private String Error_Position;
    private Date Time;

    @Override
    public String toString() {
        return "ErrordealRec{" +
                "ERDId=" + ERDId +
                ", ERId=" + ERId +
                ", Exception='" + Exception + '\'' +
                ", Result='" + Result + '\'' +
                ", C_t=" + C_t +
                ", User='" + User + '\'' +
                ", PackageId=" + PackageId +
                ", TmnId='" + TmnId + '\'' +
                ", TmnName='" + TmnName + '\'' +
                ", PipId='" + PipId + '\'' +
                ", PTid=" + PTid +
                ", if_deal='" + if_deal + '\'' +
                ", Error_Position='" + Error_Position + '\'' +
                ", Time=" + Time +
                '}';
    }

    public String getIf_deal() {
        return if_deal;
    }

    public void setIf_deal(String if_deal) {
        this.if_deal = if_deal;
    }

    public String getError_Position() {
        return Error_Position;
    }

    public void setError_Position(String error_Position) {
        Error_Position = error_Position;
    }

    public Date getTime() {
        return Time;
    }

    public void setTime(Date time) {
        Time = time;
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

    public String getPipId() {
        return PipId;
    }

    public void setPipId(String pipId) {
        PipId = pipId;
    }

    public Integer getPTid() {
        return PTid;
    }

    public void setPTid(Integer PTid) {
        this.PTid = PTid;
    }

    public Short getERDId() {
        return ERDId;
    }

    public void setERDId(Short ERDId) {
        this.ERDId = ERDId;
    }

    public Short getERId() {
        return ERId;
    }

    public void setERId(Short ERId) {
        this.ERId = ERId;
    }

    public String getException() {
        return Exception;
    }

    public void setException(String exception) {
        Exception = exception;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    public Date getC_t() {
        return C_t;
    }

    public void setC_t(Date c_t) {
        C_t = c_t;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public Long getPackageId() {
        return PackageId;
    }

    public void setPackageId(Long packageId) {
        PackageId = packageId;
    }

}
