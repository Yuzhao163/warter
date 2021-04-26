package com.water.water.dao;
import com.water.water.pojo.td_Ap;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface td_ApDao {
    List getPipIDByAreaID(String AreaID);

    List getAIDByPID(String PipID);

}
