package com.water.water.service;

import com.water.water.dao.Td_User_RightDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Autowired
    Td_User_RightDao td_user_rightDao;

    public void addUserRights(){

    }
}
