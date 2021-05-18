package com.water.water.service;


import com.water.water.dao.*;
import com.water.water.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PipeDistributeService {
    @Autowired
    private td_AreasDao td_areasDao;
    @Autowired
    private td_PipesDao td_pipesDao;
    @Autowired
    private Td_tpDao td_tpDao;
    @Autowired
    private TerminalsDao terminalsDao;
    @Autowired
    private td_ApDao td_apDao;

    public List getPipeByArea(String AreaName){
        td_Areas Area = td_areasDao.getAreaIDByAreaName(AreaName);
        String AreaID = Area.getAreaID();
        //根据Areaid获取管线id
        List Pipsid = td_apDao.getPipIDByAreaID(AreaID);
        //根据管线id获取对应管线
        List Pips = new ArrayList();
        for(int i = 0;i < Pipsid.size();i++){
            td_Ap Pip = (td_Ap)Pipsid.get(i);
            String pid = Pip.getPipID();
            td_PIPs ppp = td_pipesDao.getPipByPipID(pid);
            Pips.add(ppp);
        }
        return Pips;
    }

//    public List getpipes(String AreaID){
//        List ;
//        return;
//    };

    public Map<String,Map<String, List>> getPipe() {
        String AreaID = "1";
        //根据分区ID获取名字
        //因为表的变化，需要更改
        td_Areas Area = td_areasDao.getAreaNameByAreaID(AreaID);
        String AreaName = Area.getAreaName();
        //新建一个list
        Map<String,Map<String,List>> Areas = new HashMap<>();
        //将名字放入list
        //根据分区id获取该分区下所有管线
        //这个要改
        List pips = td_pipesDao.getPipsByAreaID(AreaID);
        //遍历每条管线，将该管线下控制柜放入管线下级list
        Map<String,List> PIPs = new HashMap<>();
        for (int i = 0; i < pips.size(); i++) {
            td_PIPs message = (td_PIPs)pips.get(i);
            //获取管线名字
            String PipsName = message.getPipName();
            //新建该条管线的list
            //将该管线名字加入list
//            PIPs.put(PipsName);
            //将该管线加入分区下级
            //根据管线名字获取管线id
            String pipid = message.getPipID();
            //获取所有控制柜，并加入管线下级
            List tp = td_tpDao.getAlltdByPipID(pipid);
            List Terminals = new ArrayList<>();
            for(int j = 0;j < tp.size();j++){
                td_Tp tp1 = (td_Tp)tp.get(j);
                String tmnid = tp1.getTmnID();
                Terminals Terminals_name = terminalsDao.getNameByID(tmnid);
                String terminals = Terminals_name.getTmnName();
                Terminals.add(terminals);
            }
            PIPs.put(PipsName,Terminals);
        }
        Areas.put(AreaName, PIPs);
        return Areas;
    }
}
