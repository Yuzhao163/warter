package com.water.water.pojo;

import java.sql.Date;
import java.sql.Timestamp;

public class Error_Connection {
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
    private String AreaID;
    private String AreaName;

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
