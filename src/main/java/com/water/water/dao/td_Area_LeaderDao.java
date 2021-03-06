package com.water.water.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface td_Area_LeaderDao {
    //新增用户及对应的权限
    void addAreaLeader(@Param("Leader") Integer Leader, @Param("AreaID") String AreaID);

    //删除该用户对应的分区权限
    void deleteRight(@Param("Leader") Integer Leader);

    //修改Leader的旧值为新值
    void updateLeader(@Param("new_name") String new_name, @Param("origin_name") String origin_name);

    //5.6获取该员工在分区的权限
    List showLeader(@Param("Leader") Integer Leader);

    // 根据分区id查找所有用户id
    List getUIDByAID(String AreaID);

    Integer deleteLeader(String areaID);

    //5.11根据userID获取对应权限
    List getRightByID(@Param("Leader") Integer userID);
}
