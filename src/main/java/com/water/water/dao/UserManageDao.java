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

    //List<UserManage> getAllUserManage();
    List<UserManage> getAllUserManage(@Param(value="UserName") String UserName,Integer page,Integer size);

    UserManage getByusername(String username);
    Integer updatetd_user(UserManage userManage);
    List getUserMessageByName(String UserName);

//    Integer insert_user(UserManage userManage);
//    Integer update_user(UserManage userManage);
//    Integer delete_user(String UserID);

    //通过用户名获取用户ID
    UserManage getUserID(String UserName);

    //5.3将前端的未修改的名称传往后端，与数据库进行对比
    List getAllUser(@Param("UserName") String userName);

    UserManage getUserNameByID(String UserID);

    //  查询除了此id下的用户名是否有匹配的
    List checkUserName(Integer userID, String userName);

    //  根据id更改用户信息
    Integer updateUserInfo(UserManage userManage);

    //  根据id查询用户名称
    String getUNameByID(Integer userID);

    //  根据用户id获取全部用户信息
    UserManage getUserByID(Integer userID);

    //5.17替换td_user_right表中Right_pp
    // 选择出所有控制柜管理员id
    List getUIDByRight();

    // 选出所有分区管理员id
    List getAIDByRight();

    // 选出所有管线管理员id
    List getPIDByRight();

    //    Integer getCountNum();
    Integer getCountNum(String UserName);

    //5.25添加员工名称是否重复判断
    String getNameRepulicate(String userName);
}
