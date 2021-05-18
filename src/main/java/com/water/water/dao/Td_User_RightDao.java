package com.water.water.dao;

import com.water.water.pojo.td_User_Right;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhaoyu
 * @version 1.0
 * @date 2021/5/7 10:08
 */
    @Mapper
    public interface Td_User_RightDao {
        void addUserRight(Integer UserID, Integer Right_PP, String TmnID, String PipID, String AreaID);
        void addUserRights(Integer UserID, Integer Right_PP);

        td_User_Right showID(@Param("UserID") Integer UserID);

        void updateUserRight(@Param("UserID") Integer UserID);

        void updateUserRights(@Param("UserID") Integer userID,@Param("Right_PP") Integer right_pp);

        td_User_Right getRight_PP(Integer userID);

        void deleteUser(String UserID);


        // 选择出所有控制柜管理员id
        List getUIDByRight();

        // 选出所有分区管理员id
        List getAIDByRight();

        // 选出所有管线管理员id
        List getPIDByRight();

        Integer deleteUserRight(String TmnID);



    }
