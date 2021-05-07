package com.water.water.pojo;

public class td_Pip_Leader {

    private Integer ID;
    private String Leader;
    private String PipID;

    public td_Pip_Leader() {
    }

    public td_Pip_Leader(Integer ID, String leader, String pipID) {
        this.ID = ID;
        Leader = leader;
        PipID = pipID;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getLeader() {
        return Leader;
    }

    public void setLeader(String leader) {
        Leader = leader;
    }

    public String getPipID() {
        return PipID;
    }

    public void setPipID(String pipID) {
        PipID = pipID;
    }

    @Override
    public String toString() {
        return "td_Pip_Leader{" +
                "ID=" + ID +
                ", Leader='" + Leader + '\'' +
                ", PipID='" + PipID + '\'' +
                '}';
    }
}
