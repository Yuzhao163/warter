package com.water.water.service;

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

    public List<UserManage> getByUserManageName(String username){
        return userManageDao.getByUserManageName(username);
    }

    public UserManage insertTotd_user(UserManage userManage){
        userManageDao.insertTotd_user(userManage);
        return userManage;
    }

    public String deletetd_user(String UserID){
        return userManageDao.deletetd_user(UserID);
    }

    public List<UserManage> getAllUserManage(){
        return userManageDao.getAllUserManage();
    }

    public int updatetd_user(String UserID){
        return userManageDao.updatetd_user(UserID);
    }

    public UserManage getByusername(String username){
        return userManageDao.getByusername(username);
    }
}
