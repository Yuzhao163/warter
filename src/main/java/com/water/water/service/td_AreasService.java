package com.water.water.service;

import com.water.water.dao.td_AreasDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class td_AreasService {
    @Autowired
    private td_AreasDao td_areasDao;

    public List getAreas(){
        return td_areasDao.getAreas();
    }



}
