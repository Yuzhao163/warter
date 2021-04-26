package com.water.water.dao;

import com.water.water.pojo.User;
import com.water.water.pojo.UserManage;
import org.apache.ibatis.annotations.Mapper;

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


//  查询所有的用户名称除了当前id的
    List<String> getAllUserName(String userID);
//  根据id更改用户信息
    Integer updateUserInfo(UserManage userManage);
//  根据id查询用户名称
    String getUserNameByID(String userName);
//  获取用户id和名称
    List getUIDName();

}
