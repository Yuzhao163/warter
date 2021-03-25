package com.water.water.dao;

import com.water.water.pojo.td_Tp;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Td_tpDao {
    td_Tp getAlltdById(String TmnId);
}
