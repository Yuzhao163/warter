package com.water.water.service;

import com.water.water.dao.DetailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailService {

    @Autowired
    private DetailDao detailDao;

    public List getVper(Integer id){
        return detailDao.getVper(id);
    }

}
