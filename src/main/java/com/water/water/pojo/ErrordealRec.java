package com.water.water.pojo;

import java.sql.Date;
import java.sql.Timestamp;

//异常展示实体类
public class ErrordealRec {
    private Long ERDId;
    private Long ERId;
    private String Exception;
    private String Result;
    private Timestamp C_t;
    private String User;
    private Long PackageId;
    private String TmnId;
    private String TmnName;
    private String PipId;
    private Integer PTid;
    private String If_deal;
    private String Error_Position;
    private Timestamp Time;

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
                ", if_deal='" + If_deal + '\'' +
                ", Error_Position='" + Error_Position + '\'' +
                ", Time=" + Time +
                '}';
    }

    public String getIf_deal() {
        return If_deal;
    }

    public void setIf_deal(String If_deal) {
        this.If_deal = If_deal;
    }

    public String getError_Position() {
        return Error_Position;
    }

    public void setError_Position(String error_Position) {
        Error_Position = error_Position;
    }

    public Timestamp getTime() {
        return Time;
    }

    public void setTime(Timestamp time) {
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

    public Long getERDId() {
        return ERDId;
    }

    public void setERDId(Long ERDId) {
        this.ERDId = ERDId;
    }

    public Long getERId() {
        return ERId;
    }

    public void setERId(Long ERId) {
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

    public Timestamp getC_t() {
        return C_t;
    }

    public void setC_t(Timestamp c_t) {
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
