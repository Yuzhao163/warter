package com.water.water.service;

import com.water.water.dao.td_PipesDao;
import com.water.water.pojo.td_PIPs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class td_PipesService {
    @Autowired
    private td_PipesDao td_pipesDao;

    public List getPips() {
        return td_pipesDao.getPips();
    }

    td_PIPs getAreasIdByPips(String PipsID) {
        return td_pipesDao.getAreasIdByPips(PipsID);
    }

    //获取对应管线--4.27/15.37------5.2解决和管线部分的冲突问题-------------------------------------------
    public List getPipe(){
        List pipes = td_pipesDao.getPipe();
        return pipes;
    }


}
