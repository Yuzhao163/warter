package com.water.water.controller;
/*
    author：李小杰
    date:3/27/2021
    function:update,add
 */
import com.water.water.Result.Result;
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
//    //插入员工数据
//    @ResponseBody
//    @RequestMapping(value = "api/addstaff")
//    public Integer insertTotd_user(UserManage userManage){
//        return userManageService.insertTotd_user(userManage);
//    }

    //插入员工数据
    @ResponseBody
    @RequestMapping(value = "api/addstaff")
    public Integer insertTotd_user(UserRight userRight){
        System.out.println("后又跟，安都跟"+userRight);
        return userManageService.insertTotd_user(userRight);
    }

//    //更新员工信息
//    @ResponseBody
//    @RequestMapping(value = "api/updstaff")
//    public Result updatetd_user(UserManage userManage){
//        try {
//            Integer num = userManageService.updatetd_user(userManage);
//            if(num != 200){
//                return new Result(400);
//            }
//            else {
//                if (userManage.getUserPswd().isEmpty()){
//                    userManage.setUserPswd("123456");
//                    userManageService.updatetd_user(userManage);
//                }else {
//                    userManageService.updatetd_user(userManage);
//                }
//                return new Result(200);
//            }
//        } catch (Exception e) {
//            return new Result(400);
//        }
//    }

    //更新员工信息
    @ResponseBody
    @RequestMapping(value = "api/updstaff")
    public Integer updatetd_user(UserRight userRight){
        System.out.println("这是我的"+userRight);
        return userManageService.updatetd_user(userRight);

    }

    //5.3,将前端的未修改的名称传往后端，与数据库进行对比
    @RequestMapping(value = "api/getstaff")
    public List getAllUser(String UserName){
        System.out.println("这是我拿到前端传来的username"+UserName);
        List user = userManageService.getAllUser(UserName);
        return user;
    }
}
