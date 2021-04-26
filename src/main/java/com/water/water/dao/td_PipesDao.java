package com.water.water.dao;

import com.water.water.pojo.td_PIPs;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface td_PipesDao {
    List getPips();

    List getPipsByAreaID(String AreaID);

    td_PIPs getPipByAreaID(String AreaID);

    String getPipNameByPipID(String pipID);

    td_PIPs getAreasIdByPips(String PipsID);

//  修改管线管理员名称和电话
    Integer updatePipLeader(String pipLeader, String pipLeadPhone, String beforeName);

//  根据管线id查询管线名称和id
    List getPIDNameByPID(String pipID);


}
