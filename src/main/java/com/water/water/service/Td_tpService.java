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
    public List getAlltdByPipID(String PipID){
        List tp = td_tpDao.getAlltdByPipID(PipID);
        List Terminals = new ArrayList<>();
        for(int j = 0;j < tp.size();j++){
            td_Tp tp1 = (td_Tp)tp.get(j);
            String tmnid = tp1.getTmnID();
            com.water.water.pojo.Terminals Terminals_name = terminalsDao.getNameByID(tmnid);
            String terminals = Terminals_name.getTmnName();
            Terminals.add(terminals);
        }
        return Terminals;
    }


}
