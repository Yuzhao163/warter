package com.water.water.service;


import com.water.water.dao.DetailDao;
import com.water.water.dao.ErrordealRecDao;
import com.water.water.dao.Td_tpDao;
import com.water.water.dao.TerminalsDao;
import com.water.water.pojo.ErrordealRec;
import com.water.water.pojo.Rec_Detail;
import com.water.water.pojo.Terminals;
import com.water.water.pojo.td_Tp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ErrordealRecService {
    @Autowired
    private ErrordealRecDao errordealRecDao;
    @Autowired
    private TerminalsDao terminalsDao;
    @Autowired
    private DetailDao detailDao;
    @Autowired
    Td_tpDao td_tpDao;
    //public ErrordealRec getAllError(){
        //return errordealRecDao.getAllError();
    //}
    public List getAllError(){
        return errordealRecDao.getAllError();
    }
    public List getAllErrorById(List AllError){
        for (int i = 0;i<AllError.size();i++){
            ErrordealRec message = (ErrordealRec)AllError.get(i);
            Long packageId = message.getPackageId();
            Rec_Detail TmnId_message = detailDao.getPipeByPackageId(packageId);
            String TmnId = TmnId_message.getTmnID();
            Terminals terminals = terminalsDao.getNameByID(TmnId);
            String TmnName = terminals.getTmnName();
            td_Tp tp = td_tpDao.getAlltdById(TmnId);
            String PipId = tp.getPipID();
            Integer PTid = tp.getPTid();
            //message.setTmnID(TmnName);
            message.setTmnId(TmnId);
            message.setTmnName(TmnName);
            message.setPipId(PipId);
            message.setPTid(PTid);
        }
        return AllError;
    }
}
