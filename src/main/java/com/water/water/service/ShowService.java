package com.water.water.service;

import com.water.water.dao.ShowDao;
import com.water.water.pojo.Rec_Detail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowService {

    @Autowired
    private ShowDao showDao;

    public List<Rec_Detail> show(Rec_Detail rec_detail){
        return showDao.selectElement(rec_detail);
    }
}
