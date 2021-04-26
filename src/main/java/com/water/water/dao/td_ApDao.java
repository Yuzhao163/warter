package com.water.water.dao;


import com.water.water.pojo.td_Ap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface td_ApDao {
    //代替td_PipsDao中的通过管线ID获取分区ID
    td_Ap getAreasIdByPips(String PipsID);

    //通过ap表获取对应的PipID
    List getPipID(String AreaID);
}
