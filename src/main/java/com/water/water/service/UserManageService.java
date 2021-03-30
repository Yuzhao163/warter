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

    public List<UserManage> getByUserManageName(String username){
        return userManageDao.getByUserManageName(username);
    }

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

    public Integer deletetd_user(String UserID){
        return userManageDao.deletetd_user(UserID);
    }

    public List<UserManage> getAllUserManage(){
        return userManageDao.getAllUserManage();
    }

    public Integer updatetd_user(UserManage userManage){
//        if(userManage.getUserPswd().isEmpty()){
//            userManage.setUserPswd("123456");
//            userManageDao.updatetd_user(userManage);
//        } else {
//            userManageDao.updatetd_user(userManage);
//        }
        userManageDao.updatetd_user(userManage);
        System.out.println("我执行了！");
        return 200;
    }

}
