package com.water.water.dao;

import com.water.water.pojo.td_Areas;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface td_AreasDao {
    List getAreas();
    td_Areas getAreaIDByAreaName(String AreaName);
    td_Areas getAreaNameByAreaID(String AreaID);

}
