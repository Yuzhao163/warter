package com.water.water.dao;

import com.water.water.pojo.td_User_Right;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface Td_User_RightDao {

    // 根据用户id找到right_pp
    Integer getRightByUID(String UserID);

    // 插入一条数据
//    Integer addUserRight(String UserID,Integer Right_PP,String TmnID,String PipID,String AreaID);

    // 获取全部数据
    List getAll();

    // 选择出所有控制柜管理员id
    List getUIDByRight();

    // 选出所有分区管理员id
    List getAIDByRight();

    // 选出所有管线管理员id
    List getPIDByRight();


    Integer deleteUserRight(String TmnID);

    void addUserRight(Integer UserID, Integer Right_PP, String TmnID, String PipID, String AreaID);

    td_User_Right showID(@Param("UserID") Integer UserID);

    void updateUserRight(@Param("UserID") Integer UserID);

}
