package com.water.water.service;
/*
    author：李小杰
    date:3/27/2021
    function:add/update
 */
import com.water.water.dao.UserManageDao;
import com.water.water.pojo.User;
import com.water.water.pojo.UserManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManageService {
    @Autowired
    UserManageDao userManageDao;
    //通过用户名称搜索展示用户信息
    public List<UserManage> getByUserManageName(String username){
        return userManageDao.getByUserManageName(username);
    }
    //插入员工数据
    public Integer insertTotd_user(UserManage userManage){
        if(userManage.getUserName().isEmpty()){
            userManage.setUserName("admin"+userManage.getRealName());
        }
        if(userManage.getUserPswd().isEmpty()) {
            userManage.setUserPswd("123456");
            userManageDao.insertTotd_user(userManage);
        } else{
            userManageDao.insertTotd_user(userManage);
        }
        return 200;
    }
    //删除员工数据
    public Integer deletetd_user(String UserID){
        return userManageDao.deletetd_user(UserID);
    }

    //获取所有员工信息
    public List<UserManage> getAllUserManage(){
        return userManageDao.getAllUserManage();
    }
    //更新员工信息
    public Integer updatetd_user(UserManage userManage){
        userManageDao.updatetd_user(userManage);
        return 200;
    }

    public UserManage getByusername(String username){
        return userManageDao.getByusername(username);
    }

    //根据员工姓名获取员工具体信息
    public List getUserMessageByName(String UserName){
        return userManageDao.getByUserManageName(UserName);
    }
}
