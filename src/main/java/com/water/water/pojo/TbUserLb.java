package com.water.water.pojo;

import java.io.Serializable;

/**
 * @author 
 * 
 */
public class TbUserLb implements Serializable {
    private String uclassid;

    private String uclassname;

    private String notememe;

    private static final long serialVersionUID = 1L;

    public String getUclassid() {
        return uclassid;
    }

    public void setUclassid(String uclassid) {
        this.uclassid = uclassid;
    }

    public String getUclassname() {
        return uclassname;
    }

    public void setUclassname(String uclassname) {
        this.uclassname = uclassname;
    }

    public String getNotememe() {
        return notememe;
    }

    public void setNotememe(String notememe) {
        this.notememe = notememe;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TbUserLb other = (TbUserLb) that;
        return (this.getUclassid() == null ? other.getUclassid() == null : this.getUclassid().equals(other.getUclassid()))
            && (this.getUclassname() == null ? other.getUclassname() == null : this.getUclassname().equals(other.getUclassname()))
            && (this.getNotememe() == null ? other.getNotememe() == null : this.getNotememe().equals(other.getNotememe()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUclassid() == null) ? 0 : getUclassid().hashCode());
        result = prime * result + ((getUclassname() == null) ? 0 : getUclassname().hashCode());
        result = prime * result + ((getNotememe() == null) ? 0 : getNotememe().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uclassid=").append(uclassid);
        sb.append(", uclassname=").append(uclassname);
        sb.append(", notememe=").append(notememe);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}