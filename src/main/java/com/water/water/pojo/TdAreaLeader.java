package com.water.water.pojo;

import java.io.Serializable;

/**
 * @author 
 * 
 */
public class TdAreaLeader implements Serializable {
    private Integer id;

    private String leader;

    private String areaid;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
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
        TdAreaLeader other = (TdAreaLeader) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLeader() == null ? other.getLeader() == null : this.getLeader().equals(other.getLeader()))
            && (this.getAreaid() == null ? other.getAreaid() == null : this.getAreaid().equals(other.getAreaid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLeader() == null) ? 0 : getLeader().hashCode());
        result = prime * result + ((getAreaid() == null) ? 0 : getAreaid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", leader=").append(leader);
        sb.append(", areaid=").append(areaid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}