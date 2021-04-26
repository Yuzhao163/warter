package com.water.water.dao;

import com.water.water.pojo.td_User_Right;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface Td_User_RightDao {
    void addUserRight(Integer UserID, Integer Right_PP, String TmnID, String PipID, String AreaID);

    td_User_Right showID(@Param("UserID") Integer UserID);

    void updateUserRight(@Param("UserID") Integer UserID);

}
