package com.water.water.dao;

import com.water.water.pojo.td_PIPs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface td_PipesDao {
    List getPips();

    List getPipsByAreaID(String AreaID);

    td_PIPs getPipByAreaID(String AreaID);

    String getPipNameByPipID(String pipID);

    td_PIPs getAreasIdByPips(String PipsID);

    //更新新增用户分配所属管线
    void updatePipLeader(String PipLeader,String PipID);
    //根据管线名称获得管线id
    td_PIPs getPipIDByPipName(String PipName);

    //获取管线全部信息
    List getPipList();

    //获取所有管线领导名称
    List getPipLeader();

    //通过PipLeder获取对应PipID
    List getPipID(@Param("PipLeader") String PipLeader);


    //获取对应管线-4.27/15.37-----5.2为解决和管线分布冲突---------------------------------------------
    List getPipe();

    //通过AreaID获取Leader,使之为空
    void updateLeaderByPipID(String id);

    //5.2获取PipLeader为空的所有PipID
    List getLeaderIsNull();

    //5.2带有追加功能的update，用于添加多个leader
    void appendPipLeader(@Param("PipLeader") String PipLeader, @Param("PipID") String PipID);
}
