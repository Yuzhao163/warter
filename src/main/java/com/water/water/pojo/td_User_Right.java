package com.water.water.pojo;

public class td_User_Right {
    private Short RId;
    private Integer UserID;
    private Integer Right_PP;

    public td_User_Right(Short RId, Integer userID, Integer right_PP) {
        this.RId = RId;
        UserID = userID;
        Right_PP = right_PP;
    }

    public td_User_Right() {
    }

    @Override
    public String toString() {
        return "td_User_Right{" +
                "RId=" + RId +
                ", UserID='" + UserID + '\'' +
                ", Right_PP=" + Right_PP +
                '}';
    }

    public Short getRId() {
        return RId;
    }

    public void setRId(Short RId) {
        this.RId = RId;
    }

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }

    public Integer getRight_PP() {
        return Right_PP;
    }

    public void setRight_PP(Integer right_PP) {
        Right_PP = right_PP;
    }
}
