package com.water.water.controller;
/*
    author：李小杰
    date:3/27/2021
    function:update,add
 */
import com.water.water.Result.Result;
import com.water.water.pojo.User;
import com.water.water.pojo.UserManage;
import com.water.water.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import sun.font.DelegatingShape;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Map;

@RestController
@CrossOrigin
public class UserManageController {

    @Autowired
    UserManageService userManageService;
    //通过用户名称搜索展示用户信息
    @RequestMapping(value = "api/staff")
    public List<UserManage> getAllUserManage() throws Exception{
        List<UserManage> user = userManageService.getAllUserManage();
        return user;
    }
    //删除员工数据
    @ResponseBody
    @RequestMapping(value = "api/delstaff")
    public Integer deletetd_user(HttpServletRequest request){
        String UserID = request.getParameter("UserID");
        Integer user = userManageService.deletetd_user(UserID);
        return user;
    }

    @RequestMapping(value = "api/getUserMessage")
    public List getUserMessageByName(@RequestParam String UserName) throws Exception{
        return userManageService.getUserMessageByName(UserName);
    }
    //插入员工数据
    @ResponseBody
    @RequestMapping(value = "api/addstaff")
    public Integer insertTotd_user(UserManage userManage){
        return userManageService.insertTotd_user(userManage);
    }
    //更新员工信息
    @ResponseBody
    @RequestMapping(value = "api/updstaff")
    public Result updatetd_user(UserManage userManage){
        try {
            Integer num = userManageService.updatetd_user(userManage);
            if(num != 200){
                return new Result(400);
            }
            else {
                if (userManage.getUserPswd().isEmpty()){
                    userManage.setUserPswd("123456");
                    userManageService.updatetd_user(userManage);
                }else {
                    userManageService.updatetd_user(userManage);
                }
                return new Result(200);
            }
        } catch (Exception e) {
            return new Result(400);
        }
    }

//  个人信息的修改功能
//  根据id查询修改后的用户名是否重复
//    @ResponseBody
//    @RequestMapping(value = "api/checkUserName")
//    public Integer checkUserName(@RequestParam String userName) {
//        return userManageService.checkUserName(userName);
//    }

//  根据用户id修改个人信息
    @ResponseBody
    @RequestMapping(value = "api/updateUserInfo")
    public Integer updateUserInfo(UserManage userManage) {
//      首先判断修改的用户名是否有重复
        String userName = userManage.getUserName();
        String userID = userManage.getUserID();
//      用户名有重复不执行更新
        if (userManageService.checkUserName(userName,userID)==201) {
            return 201;
        } else {
//      用户名没有重复执行更新
            System.out.println(userManage);
            return userManageService.updateUserInfo(userManage);
        }
    }
}
