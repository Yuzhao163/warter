package com.water.water.service;

import com.water.water.dao.CommRecDao;
import com.water.water.pojo.CommRec;
import com.water.water.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.water.water.dao.UserDao;

@Service
public class CommRecService {

    @Autowired
    private CommRecDao commRecDao;

    public CommRec inserttotd_comm(CommRec commRec){
        commRecDao.inserttotd_comm(commRec);
        return commRec;
    }
}