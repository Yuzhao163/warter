package com.water.water.dao;

import com.water.water.pojo.User;
import com.water.water.pojo.UserManage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserManageDao {
    List<UserManage> getByUserManageName(String username);
    Integer insertTotd_user(UserManage userManage);
    Integer deletetd_user(String UserID);
    List<UserManage> getAllUserManage();
    UserManage getByusername(String username);
    Integer updatetd_user(UserManage userManage);
    List getUserMessageByName(String UserName);

    Integer insert_user(UserManage userManage);
    Integer update_user(UserManage userManage);
    Integer delete_user(String UserID);

    //通过用户名获取用户ID
    UserManage getUserID(String UserName);

    //5.3将前端的未修改的名称传往后端，与数据库进行对比
    List getAllUser(@Param("UserName") String userName);


    UserManage getUserNameByID(String UserID);
}
