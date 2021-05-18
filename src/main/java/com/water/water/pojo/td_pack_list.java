package com.water.water.pojo;

import java.sql.Date;

/**
 * @author zhaoyu
 * @version 1.0
 * @date 2021/5/10 20:51
 */
public class td_pack_list {
    Long pId;
    Long PackageID;
    Integer PackagePP;
    Date RecDate;
    String TmnID;
    String PeerAddress;
    String PeerMacAdd;

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public Long getPackageID() {
        return PackageID;
    }

    public void setPackageID(Long packageID) {
        PackageID = packageID;
    }

    public Integer getPackagePP() {
        return PackagePP;
    }

    public void setPackagePP(Integer packagePP) {
        PackagePP = packagePP;
    }

    public Date getRecDate() {
        return RecDate;
    }

    public void setRecDate(Date recDate) {
        RecDate = recDate;
    }

    public String getTmnID() {
        return TmnID;
    }

    public void setTmnID(String tmnID) {
        TmnID = tmnID;
    }

    public String getPeerAddress() {
        return PeerAddress;
    }

    public void setPeerAddress(String peerAddress) {
        PeerAddress = peerAddress;
    }

    public String getPeerMacAdd() {
        return PeerMacAdd;
    }

    public void setPeerMacAdd(String peerMacAdd) {
        PeerMacAdd = peerMacAdd;
    }
}
