package com.water.water.dao;

import com.water.water.pojo.UserManage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserManageDao {
    List<UserManage> getByUserManageName(String username);
    int insertTotd_user(UserManage userManage);
    String deletetd_user(String UserID);
    List<UserManage> getAllUserManage();
    int updatetd_user(String UserID);
}
