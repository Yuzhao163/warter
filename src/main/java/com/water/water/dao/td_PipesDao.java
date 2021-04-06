package com.water.water.dao;

import com.water.water.pojo.td_PIPs;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface td_PipesDao {
    List getPips();
    List getPipsByAreaID(String AreaID);
    td_PIPs getPipByAreaID(String AreaID);
    //通过PipsID查找AreaID
    td_PIPs getAreasIdByPips(String PipsID);
}
