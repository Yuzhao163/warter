package com.water.water.dao;

import com.water.water.pojo.td_Areas;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface td_AreasDao {
    List getAreas();

    td_Areas getAreaIDByAreaName(String AreaName);

    td_Areas getAreaNameByAreaID(String AreaID);

    Integer updateAreaLeader(String areaLeader, String areaLeadPhone, String beforeName);

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
