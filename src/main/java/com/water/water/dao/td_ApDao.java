package com.water.water.dao;
import com.water.water.pojo.td_Ap;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface td_ApDao {
    List getPipIDByAreaID(String AreaID);

    List getAIDByPID(String PipID);

    Integer clearAreaID(String AreaID);


    //5.9插入选中的管线ID和分区ID
    void addID(@Param("AreaID") String areaID, @Param("PipID") String pipID);

    //5.9删除td_Ap表中PipID对应的AreaID关系
    void deletePip(@Param("PipID") String pipID);

    void updateID(@Param("AreaID") String s, @Param("PipID") String pipID);


}
