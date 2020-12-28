package com.water.water.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DetailDao {

//    @Select("select `V_per` from td_Rec_Detail where Id = 2")
    List getVper(Integer id);


}
