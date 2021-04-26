package com.water.water.service;
/*
    author：李小杰
    date:3/27/2021
    function:add/update
 */
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.water.water.dao.*;
import com.water.water.pojo.UserManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserManageService {
    @Autowired
    UserManageDao userManageDao;
    @Autowired
    td_AreasDao td_areasDao;
    @Autowired
    td_PipesDao td_pipesDao;
    @Autowired
    TerminalsDao terminalsDao;
    @Autowired
    ErrordealRecDao errordealRecDao;

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
        userManageDao.deletetd_user(UserID);
        userManageDao.delete_user(UserID);
        return 200;
    }

    //获取所有员工信息
    public List<UserManage> getAllUserManage(){
        return userManageDao.getAllUserManage();
    }
    //更新员工信息
    public Integer updatetd_user(UserManage userManage){
        userManageDao.updatetd_user(userManage);
        userManageDao.update_user(userManage);
        return 200;
    }

    public UserManage getByusername(String username){
        return userManageDao.getByusername(username);
    }

    //根据员工姓名获取员工具体信息
    public List getUserMessageByName(String UserName){
        return userManageDao.getByUserManageName(UserName);
    }

//lmh
    //  判断修改后的用户名是否重复
    public Integer checkUserName(String userName,String userID) {
    //      将用户名与数据库中的用户名进行比较
        List<String> allName = userManageDao.getAllUserName(userID);
        for (int i=0; i<allName.size(); i++) {
            if (userName.equals(allName.get(i))) {
//              存在相等
                return 201;
            }
        }
//      不存在用户名
        return 200;
    }

//  根据用户id修改个人信息
    public Integer updateUserInfo(UserManage userManage) {
//      在没有进行真正的更新之前先将旧的用户名记录下来 用于其他表的更新
        String userID = userManage.getUserID();
        String beforeName = userManageDao.getUserNameByID(userID);
        String userName = userManage.getUserName();
        String MoPhone = userManage.getMoPhone();
        Date date1 = new Date();//获取当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = sdf.format(date1);//时间存储为字符串
        System.out.println(str);
        Timestamp ModTime = Timestamp.valueOf(str);//转换时间字符串为Timestamp
        userManage.setModTime(ModTime);
        if(userManageDao.updateUserInfo(userManage)==1) {
//          在修改个人信息的同时修改相关表信息（分区表、管线表、控制柜表、异常详细记录表 ）
            td_areasDao.updateAreaLeader(userName,MoPhone,beforeName);
            td_pipesDao.updatePipLeader(userName,MoPhone,beforeName);
            terminalsDao.updateTmnLeader(userName,beforeName);
            errordealRecDao.updateErrorDealUser(userName,beforeName);
            return 200;
        } else {
            return 400;
        }
    }

}
