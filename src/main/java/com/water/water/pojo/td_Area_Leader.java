package com.water.water.pojo;

public class td_Area_Leader {

    private Integer ID;
    private Integer Leader;
    private String AreaID;

    public td_Area_Leader() {
    }

    public td_Area_Leader(Integer ID, Integer leader, String areaID) {
        this.ID = ID;
        Leader = leader;
        AreaID = areaID;
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

    public String getAreaID() {
        return AreaID;
    }

    public void setAreaID(String areaID) {
        AreaID = areaID;
    }

    @Override
    public String toString() {
        return "td_Area_Leader{" +
                "ID=" + ID +
                ", Leader='" + Leader + '\'' +
                ", AreaID='" + AreaID + '\'' +
                '}';
    }
}
