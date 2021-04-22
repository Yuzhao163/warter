package com.water.water.service;


import com.water.water.dao.DetailDao;
import com.water.water.dao.PipeShowDao;
import com.water.water.dao.TerminalsDao;
import com.water.water.pojo.ErrordealRec;
import com.water.water.pojo.Rec_Detail;
import com.water.water.pojo.Terminals;
import com.water.water.pojo.td_Tp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PipeShowService {
    @Autowired
    private PipeShowDao pipeShowDao;
    @Autowired
    private TerminalsDao terminalsDao;
    @Autowired
    private DetailDao detailDao;

    public Map<String,Rec_Detail> getPipe(){
        Map<String,Rec_Detail> res = new HashMap<String , Rec_Detail>();
        List pipe = pipeShowDao.getPipe();
        //控制柜和控制柜名字要对应，否则会出现越界错误
        for (int i = 0;i<pipe.size();i++){
            Rec_Detail message = (Rec_Detail)pipe.get(i);
            String TmnId = message.getTmnID();
            Terminals terminals = terminalsDao.getNameByID(TmnId);
            String TmnName = terminals.getTmnName();
            //message.setTmnID(TmnName);
            message.setTmnID(TmnId);
            res.put(TmnName,message);
        }
        return res;
    }

    public Map<String,Rec_Detail> getPipeByUserName(String username){
        Map<String,Rec_Detail> res = new HashMap<String , Rec_Detail>();
        //获取所有控制柜，遍历，然后放到一个Map里面
        List terminals = terminalsDao.getTerminalsByUserName(username);
        List pipe = pipeShowDao.getPipe();
        //控制柜和控制柜名字要对应，否则会出现越界错误
        for (int i = 0;i<terminals.size();i++){
            //获取控制柜的第一个
            Terminals message = (Terminals)terminals.get(i);
            //取出来tmnid
            String TmnId = message.getTmnId();
            //根据tmnid去rec_detail里面找到控制柜
            Rec_Detail rec_detail = pipeShowDao.getRecByTmnID(TmnId);
            //获取terminals名字，将名字放入map
            String TmnName = message.getTmnName();
            //message.setTmnID(TmnName);
            res.put(TmnName,rec_detail);
        }
        return res;
    }

}
