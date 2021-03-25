package com.water.water.service;

import com.water.water.dao.Td_tpDao;
import com.water.water.pojo.td_Tp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Td_tpService {
    @Autowired
    private Td_tpDao td_tpDao;
    public td_Tp getAlltdById(String TmnId){
        return td_tpDao.getAlltdById(TmnId);
    }
}
