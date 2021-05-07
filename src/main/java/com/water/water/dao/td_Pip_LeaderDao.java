package com.water.water.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface td_Pip_LeaderDao {
    //新增用户及对应的权限
    void addPipLeader(@Param("Leader") String Leader, @Param("PipID") String PipID);

    //删除该用户对应的管线权限
    void deleteRight(@Param("Leader") String Leader);

    //修改Leader的旧值为新值
    void updateLeader(@Param("new_name") String new_name, @Param("origin_name") String origin_name);

    //5.6获取该员工在管线的权限
    List showLeader(@Param("Leader") String Leader);
}
