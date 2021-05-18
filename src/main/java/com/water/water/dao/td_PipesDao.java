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

//    td_PIPs getAreasIdByPips(String PipsID);

//  修改管线管理员名称和电话
    Integer updatePipLeader(String pipLeader, String pipLeadPhone, String beforeName);

//  根据管线id查询管线名称和id
    List getPIDNameByPID(String pipID);

    Integer getPipSize();

    List getAllPips(Integer page, Integer size);



    //5.9增加管线
    Integer addPips(@Param("PipID") String PipID, @Param("PipName") String PipName);

    //5.9根据管线名称查询是否存在同名管线
    String ifSameNamePip(String PipName);

    //5.9判断在数据库中是否存在相同的PipID
    String getByPipId(String pipID);

    //5.9删除管线
    void deletePip(@Param("PipID") String pipID);

    //5.9更新管线
    void updatePips(@Param("PipID") String pipID, @Param("PipName") String pipName);

    //5.9判断名称是否重复
    String getPipNameByName(@Param("PipName") String pipName);




}
