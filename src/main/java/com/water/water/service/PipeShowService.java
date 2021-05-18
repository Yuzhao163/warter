package com.water.water.service;


import com.water.water.dao.*;
import com.water.water.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private UserManageDao userManageDao;
    @Autowired
    private td_Tmn_LeaderDao td_tmn_leaderDao;

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

    public Map<String,Rec_Detail> getPipeByUserName(String username) throws Exception{
        Map<String,Rec_Detail> res = new HashMap<String , Rec_Detail>();
        //获取所有控制柜，遍历，然后放到一个Map里面
        List terminals = new ArrayList();
        UserManage userManage = userManageDao.getUserID(username);
        List tmnID = td_tmn_leaderDao.getRightByID(userManage.getUserID());
        for(int i = 0; i < tmnID.size(); i++) {
            td_Tmn_Leader td_tmn_leader = (td_Tmn_Leader) tmnID.get(i);
            String tmnid = td_tmn_leader.getTmnID();
            String tmnName = terminalsDao.getTmnNameByTmnID(tmnid);
            Terminals tmn = terminalsDao.getTmnByTmnName(tmnName);
            terminals.add(tmn);
        }
        //List pipe = pipeShowDao.getPipe();
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
