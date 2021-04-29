package com.water.water.controller;

import com.water.water.pojo.UserRight;
import com.water.water.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class TestController {
    @Autowired
    TestService t;

//    @RequestMapping("api/addUserRight")
    public void addUserRights(String Right_PP){

        System.out.println(Right_PP);
    }

//    @RequestMapping("api/addstaff")
    public void addStaff(UserRight userRight){
        System.out.println(userRight);
        System.out.println(userRight.getTmnName());
    }

//    @RequestMapping("api/updstaff")
    public void updStaff(UserRight userRight){
        System.out.println(userRight);
    }
}
