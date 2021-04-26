package com.water.water.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface td_UserRightDao {

    Integer getRightByUID(String UserID);

    Integer addUserRight(String UserID,Integer Right_PP,String TmnID,String PipID,String AreaID);

    List getall();

    Integer deleteUserRight(String TmnID);
}
