package com.water.water.pojo;

import java.io.Serializable;

/**
 * @author 
 * 
 */
public class TdAp implements Serializable {
    private Integer id;

    private String areaid;

    private String pipid;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public String getPipid() {
        return pipid;
    }

    public void setPipid(String pipid) {
        this.pipid = pipid;
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
        TdAp other = (TdAp) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAreaid() == null ? other.getAreaid() == null : this.getAreaid().equals(other.getAreaid()))
            && (this.getPipid() == null ? other.getPipid() == null : this.getPipid().equals(other.getPipid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAreaid() == null) ? 0 : getAreaid().hashCode());
        result = prime * result + ((getPipid() == null) ? 0 : getPipid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", areaid=").append(areaid);
        sb.append(", pipid=").append(pipid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}