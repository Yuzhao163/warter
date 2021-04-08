package com.water.water.controller;

import com.water.water.Result.Result;
import com.water.water.pojo.UserManage;
import com.water.water.service.TerminalsService;
import com.water.water.service.UserManageService;
import com.water.water.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import com.water.water.pojo.User;
import java.util.List;


@CrossOrigin
@RestController
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserManageService userManageService;

    @PostMapping(value = "api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String username = requestUser.getUsername();
        String password = requestUser.getPassword();
        username = HtmlUtils.htmlEscape(username);
        try {
//            User user = userService.getByusername(username);
//            String passwords = user.getPassword();
//            String userid = user
            UserManage userManage = userManageService.getByusername(username);
            String UserPswd = userManage.getUserPswd();
            String UClassID = userManage.getUClassID();
            if(userManage == null){
                return new Result(400);
            }
            else {
                if(UserPswd.equals(password) && UClassID.equals("1")) {
                    return new Result(100);
                }else if(UserPswd.equals(password) && UClassID.equals("2")){
                    return new Result(200);
                }else if (UserPswd.equals(password) && UClassID.equals("3")){
                    return new Result(300);
                }else{
                    return new Result(400);
                }
            }
        } catch (Exception e) {
            return new Result(400);
        }
    }


    @PostMapping(value = "api/regist")
    @ResponseBody
    public Result regist(@RequestBody User requestUser) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String username = requestUser.getUsername();
        String password = requestUser.getPassword();
        String telephone = requestUser.getTelephone();
        String email = requestUser.getEmail();
        username = HtmlUtils.htmlEscape(username);
        password = HtmlUtils.htmlEscape(password);
        email = HtmlUtils.htmlEscape(email);
        try {
            User user = userService.insertToUser(requestUser);
            if(user == null){
                return new Result(400);
            }
            else {
                return new Result(200);
                }
        } catch (Exception e) {
            return new Result(400);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/regist")
    public String user(){
        String message = "myu";
        return message;
    }
}


