package com.water.water.service;

import com.water.water.dao.TerminalsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TerminalsService {
    @Autowired
    private TerminalsDao terminalsDao;

    public List getTmnID(){
        return terminalsDao.getTmnID();
    }
}
