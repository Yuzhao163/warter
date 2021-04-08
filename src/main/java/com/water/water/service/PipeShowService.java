package com.water.water.service;


import com.water.water.dao.PipeShowDao;
import com.water.water.dao.TerminalsDao;
import com.water.water.pojo.ErrordealRec;
import com.water.water.pojo.Rec_Detail;
import com.water.water.pojo.Terminals;
import com.water.water.pojo.td_Tp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PipeShowService {
    @Autowired
    private PipeShowDao pipeShowDao;
    @Autowired
    private TerminalsDao terminalsDao;

    public List getPipe(){
        List pipe = pipeShowDao.getPipe();
        //控制柜和控制柜名字要对应，否则会出现越界错误
        for (int i = 0;i<pipe.size();i++){
            Rec_Detail message = (Rec_Detail)pipe.get(i);
            String TmnId = message.getTmnID();
            Terminals terminals = terminalsDao.getNameByID(TmnId);
            String TmnName = terminals.getTmnName();
            //message.setTmnID(TmnName);
            message.setTmnID(TmnName);
        }
        return pipe;
    }
}
