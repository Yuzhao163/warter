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
