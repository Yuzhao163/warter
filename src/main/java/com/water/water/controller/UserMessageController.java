package com.water.water.controller;

import com.water.water.pojo.UserManage;
import com.water.water.service.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserMessageController {

    @Autowired
    UserMessageService userMessageService;

    @RequestMapping("api/selectMes")
    public UserManage select_userMessage(String UserID){
        return userMessageService.select_userMessage(UserID);
    }

    @RequestMapping("api/updateMes")
    public void update_Message(UserManage userManage){
        userMessageService.update_Message(userManage);
    }

}
