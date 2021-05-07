package com.water.water.service;

import com.water.water.dao.td_ApDao;
import com.water.water.dao.td_PipesDao;
import com.water.water.pojo.td_Ap;
import com.water.water.pojo.td_PIPs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class td_PipesService {
    @Autowired
    private td_PipesDao td_pipesDao;
    @Autowired
    private td_ApDao td_apDao;

    public List getPips() {
        return td_pipesDao.getPips();
    }

//    td_PIPs getAreasIdByPips(String PipsID) {
//        return td_pipesDao.getAreasIdByPips(PipsID);
//    }

    td_Ap getAreasIdByPips(String PipsID) {
//        return td_pipesDao.getAreasIdByPips(PipsID);
        return td_apDao.getAreasIdByPips(PipsID);
    }

    //获取对应管线--4.27/15.37-------------------------------------------------
    public List getPipes(){
        List pipes = td_pipesDao.getPipes();
        return pipes;
    }

    //获取对应管线--4.27/15.37------5.2解决和管线部分的冲突问题-------------------------------------------
    public List getPipe(){
        List pipes = td_pipesDao.getPipe();
        return pipes;
    }


}
