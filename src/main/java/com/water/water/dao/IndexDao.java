package com.water.water.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IndexDao {
    //    @Select("select `V_per` from td_Rec_Detail where Id = 2")
    List getAllMessage();

    List getSelectMessage(String W_work,String W_Upline,String W_Downline);

    List getMessageDesc();

    Integer getCountMessage();

    List getSelectMessageByPage(Integer page,Integer size);

    List getAllMessageToException();
}
