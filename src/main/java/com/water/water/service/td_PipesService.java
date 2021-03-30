package com.water.water.service;

import com.water.water.dao.td_PipesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class td_PipesService {
    @Autowired
    private td_PipesDao td_pipesDao;
    public List getPips(){
        return td_pipesDao.getPips();
    }
}
