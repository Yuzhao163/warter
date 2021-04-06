package com.water.water.service;

import com.water.water.dao.UserMessageDao;
import com.water.water.pojo.UserManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMessageService {
    @Autowired
    UserMessageDao userMessageDao;

    public UserManage select_userMessage(String UserID){
        return userMessageDao.select_userMessage(UserID);
    }

    public void update_Message(UserManage userManage){
        userMessageDao.update_userMessage(userManage);
        userMessageDao.update_user(userManage);
    }
}
