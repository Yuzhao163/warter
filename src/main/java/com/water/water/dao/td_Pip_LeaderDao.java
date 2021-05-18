package com.water.water.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface td_Pip_LeaderDao {
    //新增用户及对应的权限
    void addPipLeader(@Param("Leader") Integer Leader, @Param("PipID") String PipID);

    //删除该用户对应的管线权限
    void deleteRight(@Param("Leader") Integer Leader);

    //修改Leader的旧值为新值
    void updateLeader(@Param("new_name") String new_name, @Param("origin_name") String origin_name);

    //5.6获取该员工在管线的权限
    List showLeader(@Param("Leader") Integer Leader);


    //   根据管线id获得管理员id
    List getUIDByPID(String PipID);

    //5.9删除td_pip_leader中PipID和leader的关系
    void deletePip(@Param("PipID") String pipID);

    //5.9更新td_pip_leader中PipID和leader的关系
    void updatePip();

    //5.11根据userID获取对应权限
    List getRightByID(@Param("Leader") Integer userID);

}
