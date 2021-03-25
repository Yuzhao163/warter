package com.water.water.service;


import com.water.water.dao.ErrordealRecDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ErrordealRecService {
    @Autowired
    private ErrordealRecDao errordealRecDao;

    public List getAllError(){
        return errordealRecDao.getAllError();
    }
}
