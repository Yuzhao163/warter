package com.water.water.dao;

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
    //void updateAreaLeader(@Param("AreaLeader") String AreaLeader,@Param(
            //"AreaID") String AreaID);

    //获取所有分区领导名称
    List getAreaLeader();

    //通过AreaLeder获取对应AreaID
    //List getAreaID(@Param("AreaLeader") String Arealeader);

    //通过AreaID获取Leader,使之为空
    void updateLeaderByAreaID(String id);

    //5.2 和管线分布冲突
    // 应用到人员管理上面的getAreas方法
    List getArea();

    //5.2带有追加功能的update，用于添加多个leader
    //void appendAreaLeader(@Param("AreaLeader") String AreaLeader, @Param(
            //"AreaID") String AreaID);

//    //5.2根据AreaID判断AreaLeader是否为空
//    td_Areas getLeaderByID(@Param("AreaID") String AreaID);

    //5.2获取AreaLeader为空的所有AreaID
    List getLeaderIsNull();

    String getNameByID(String AreaID);

    //  获取分区总个数
    Integer getAreaSize();

    //  获取全部分区信息
    List getAllAreas(Integer page, Integer size);

    //  获取所有分区id
    List getAreaID();

    //  添加分区名称与id
    Integer addArea(String AreaID,String AreaName);

    String getByAreaId(String AreaID);

    String getAreaNameByName(String AreaName);

    Integer modifyAreaNameByID(String AreaID, String AreaName);

    Integer deleteArea(String AreaID);


}
