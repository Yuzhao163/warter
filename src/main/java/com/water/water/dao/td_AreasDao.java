package com.water.water.dao;

import com.water.water.Result.Result;
import com.water.water.pojo.td_Areas;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface td_AreasDao {
    List getAreas();
    td_Areas getAreaIDByAreaName(String AreaName);
    td_Areas getAreaNameByAreaID(String AreaID);

    //更新新增用户分配所属分区
    void updateAreaLeader(@Param("AreaLeader") String AreaLeader,@Param("AreaID") String AreaID);

    //获取所有分区领导名称
    List getAreaLeader();

    //通过AreaLeder获取对应AreaID
    List getAreaID(@Param("AreaLeader") String Arealeader);

    List getAreas1();

}
