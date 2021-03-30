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

@RestController
@CrossOrigin
public class UserManageController {

    @Autowired
    UserManageService userManageService;

    @RequestMapping(value = "api/staff")
    public List<UserManage> getAllUserManage() throws Exception{
        List<UserManage> user = userManageService.getAllUserManage();
        return user;
    }

    @ResponseBody
    @RequestMapping(value = "api/delstaff")
    public Integer deletetd_user(HttpServletRequest request){
        String UserID = request.getParameter("UserID");
        Integer user = userManageService.deletetd_user(UserID);
        System.out.println("测试成功");
        return user;
    }

    @ResponseBody
    @RequestMapping(value = "api/addstaff")
    public Integer insertTotd_user(UserManage userManage){
//        SimpleDateFormat sdf = new SimpleDateFormat();
//        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");  //a为am，pm的标记
//        Date date = new Date();
//        String new_date = sdf.format(date);
//        System.out.println(new_date);
        System.out.println("测试一下22222222222222222222222");

        return userManageService.insertTotd_user(userManage);
    }

    @ResponseBody
    @RequestMapping(value = "api/updstaff")
    public Result updatetd_user(UserManage userManage){
        try {
            Integer num = userManageService.updatetd_user(userManage);
            if(num != 200){
                return new Result(400);
            }
            else {
                System.out.println("调用了else");
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
}
