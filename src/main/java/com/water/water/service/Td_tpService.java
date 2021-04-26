package com.water.water.service;

import com.water.water.dao.Td_tpDao;
import com.water.water.dao.TerminalsDao;
import com.water.water.pojo.Terminals;
import com.water.water.pojo.td_Tp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Td_tpService {
    @Autowired
    private Td_tpDao td_tpDao;
    @Autowired
    private TerminalsDao terminalsDao;

    public td_Tp getAlltdById(String TmnId){
        return td_tpDao.getAlltdById(TmnId);
    }
//    public List getAlltdByPipID(String PipID){
//        List tp = td_tpDao.getAlltdByPipID(PipID);
//        List Terminals = new ArrayList<>();
//        for(int j = 0;j < tp.size();j++){
//            td_Tp tp1 = (td_Tp)tp.get(j);
//            String tmnid = tp1.getTmnID();
//            com.water.water.pojo.Terminals Terminals_name = terminalsDao.getNameByID(tmnid);
//            String terminals = Terminals_name.getTmnName();
//            Terminals.add(terminals);
//        }
//        return Terminals;
//    }

//    public List getAlltdByPipID(String PipID){
//        List tp = td_tpDao.getAlltdByPipID(PipID);
//        System.out.println(tp.size());
//        List Terminals = new ArrayList<>();
//        for(int j = 0;j < tp.size();j++){
//            td_Tp tp1 = (td_Tp)tp.get(j);
//            String tmnid = tp1.getTmnID();
//            System.out.println("这是我的控制柜id"+tmnid);
//            com.water.water.pojo.Terminals Terminals_name = terminalsDao.getNameByID2(tmnid);
//            String terminals = Terminals_name.getTmnName();
//            System.out.println("我是控制柜的领导者"+Terminals_name.getTmnLeader());
//            if(Terminals_name.getTmnLeader().equals("")) {
//                Terminals.add(terminals);
//            }
//        }
//        System.out.println(Terminals);
//        return Terminals;
//    }
//
    //用来传回在下拉列表中显示出Leader人员为空的控制柜的名称
    public List getTmnLeaderNull(){
        List terminals = terminalsDao.getTmnLeaderNull();
        List terminal1 = new ArrayList();
        for(int i = 0; i< terminals.size(); i++){
            Terminals terminal = (Terminals) terminals.get(i);
            String tmnName = terminal.getTmnName();
            terminal1.add(tmnName);
        }
        return terminal1;
    }
}
