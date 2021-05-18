package com.water.water.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface td_Tmn_LeaderDao {
    //新增用户及对应的权限
    void addTmnLeader(@Param("Leader") Integer Leader, @Param("TmnID") String TmnID);

    //删除该用户对应的控制柜权限
    void deleteRight(@Param("Leader") Integer Leader);

//    //修改Leader的旧值为新值
//    void updateLeader(@Param("new_name") String new_name, @Param("origin_name") String origin_name);

    //5.6获取该员工在控制柜的权限
    List showLeader(@Param("Leader") Integer Leader);

    // 通过控制柜id获取用户id
    List getUIDByTID(String TmnID);

    Integer deleteTmnLeader(String TmnID);

    //5.11根据userID获取对应权限
    List getRightByID(@Param("Leader") Integer userID);
}
