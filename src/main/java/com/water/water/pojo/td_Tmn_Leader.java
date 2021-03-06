package com.water.water.pojo;

public class td_Tmn_Leader {

    private Integer ID;
    private Integer Leader;
    private String TmnID;

    public td_Tmn_Leader() {
    }

    public td_Tmn_Leader(Integer ID, Integer leader, String tmnID) {
        this.ID = ID;
        Leader = leader;
        TmnID = tmnID;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getLeader() {
        return Leader;
    }

    public void setLeader(Integer leader) {
        Leader = leader;
    }

    public String getTmnID() {
        return TmnID;
    }

    public void setTmnID(String tmnID) {
        TmnID = tmnID;
    }

    @Override
    public String toString() {
        return "td_Tmn_Leader{" +
                "ID=" + ID +
                ", Leader='" + Leader + '\'' +
                ", TmnID='" + TmnID + '\'' +
                '}';
    }
}
