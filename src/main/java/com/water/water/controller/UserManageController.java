package com.water.water.controller;

import com.water.water.pojo.UserManage;
import com.water.water.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}
