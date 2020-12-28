package com.water.water.service;


import com.water.water.dao.PipeShowDao;
import com.water.water.pojo.Rec_Detail;
import com.water.water.pojo.Terminals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PipeShowService {
    @Autowired
    private PipeShowDao pipeShowDao;

    public List getPipe(){
        return pipeShowDao.getPipe();
    }
}
