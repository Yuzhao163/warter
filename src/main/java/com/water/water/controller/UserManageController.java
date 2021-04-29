package com.water.water.controller;
/*
    author：李小杰
    date:3/27/2021
    function:update,add
 */
import com.water.water.Result.Result;
import com.water.water.pojo.Area_Pip_Tmn_Leader;
import com.water.water.pojo.User;
import com.water.water.pojo.UserManage;
import com.water.water.pojo.UserRight;
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
    public List getAllUserManage() throws Exception{
        List user = userManageService.getAllUserManage();
//        System.out.println(user["RegTime"]);
//        System.out.println(user);
        return user;
    }
    //删除员工数据
    @ResponseBody
    @RequestMapping(value = "api/delstaff")
    public Integer deletetd_user(HttpServletRequest request){
        String UserID = request.getParameter("UserID");
        Integer userID = Integer.parseInt(UserID);
//        Integer user = userManageService.deletetd_user(UserID);
        Integer user = userManageService.deleteUser(userID);
        return user;
    }

    @RequestMapping(value = "api/getUserMessage")
    public List getUserMessageByName(@RequestParam String UserName) throws Exception{
        return userManageService.getUserMessageByName(UserName);
    }

    //插入员工数据
    @ResponseBody
    @RequestMapping(value = "api/addstaff")
    public Integer insertTotd_user(UserRight userRight){
        System.out.println("后又跟，安都跟"+userRight);
        return userManageService.insertTotd_user(userRight);
    }

    //更新员工信息
    @ResponseBody
    @RequestMapping(value = "api/updstaff")
    public Integer updatetd_user(UserRight userRight){
        System.out.println("这是我的"+userRight);
        return userManageService.updatetd_user(userRight);

    }



}
