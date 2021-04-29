package com.water.water.dao;

import com.water.water.pojo.User;
import com.water.water.pojo.UserManage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserManageDao {
    List<UserManage> getByUserManageName(String username);

    //插入td_user表
    Integer insertTotd_user(UserManage userManage);

    Integer deletetd_user(String UserID);

    List getAllUserManage();

    UserManage getByusername(String username);

    //修改td_user表
    Integer updatetd_user(UserManage userManage);

    List getUserMessageByName(String UserName);

    Integer insert_user(UserManage userManage);

    Integer update_user(UserManage userManage);

    Integer deleteUser(Integer UserID);

    //通过用户名获取用户ID
    UserManage getUserID(String UserName);
}
