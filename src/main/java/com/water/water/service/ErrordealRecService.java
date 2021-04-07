package com.water.water.service;


import com.water.water.dao.*;
import com.water.water.pojo.*;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    com.water.water.dao.td_PipesDao td_PipesDao;
    @Autowired
    td_AreasDao td_areasDao;

    //public ErrordealRec getAllError(){
    //return errordealRecDao.getAllError();
    //}
    //宇哥代码
//    public List getAllError() {
//        return errordealRecDao.getAllError();
//    }
//
//    public List getAllErrorById(List AllError) {
//        System.out.println(AllError);
//        for (int i = 0; i < AllError.size(); i++) {
//            ErrordealRec message = (ErrordealRec) AllError.get(i);
//            Long packageId = message.getPackageId();
//            Rec_Detail TmnId_message = detailDao.getPipeByPackageId(packageId);
//            String TmnId = TmnId_message.getTmnID();
//            Terminals terminals = terminalsDao.getNameByID(TmnId);
//            String TmnName = terminals.getTmnName();
//            td_Tp tp = td_tpDao.getAlltdById(TmnId);
//            String PipId = tp.getPipID();
//            Integer PTid = tp.getPTid();
//            //message.setTmnID(TmnName);
//            message.setTmnId(TmnId);
//            message.setTmnName(TmnName);
//            message.setPipId(PipId);
//            message.setPTid(PTid);
//        }
//        return AllError;
//    }

    public List getAllError() {
        return errordealRecDao.getAllError();
    }

    public List getAllErrorById(List AllError) {
        System.out.println(AllError);
        for (int i = 0; i < AllError.size(); i++) {
            ErrordealRec message = (ErrordealRec) AllError.get(i);
            Long packageId = message.getPackageId();
            Rec_Detail TmnId_message = detailDao.getPipeByPackageId(packageId);
            String TmnId = TmnId_message.getTmnID();
            Terminals terminals = terminalsDao.getNameByID(TmnId);
            String TmnName = terminals.getTmnName();
            td_Tp tp = td_tpDao.getAlltdById(TmnId);
            String PipId = tp.getPipID();
            td_PIPs td_pips = td_PipesDao.getAreasIdByPips(PipId);
            String areaID = td_pips.getAreaID();
            td_PIPs td_piPs = td_PipesDao.getPipNameByPipID(PipId);
            String PipName = td_piPs.getPipName();
            td_Areas td_areas = td_areasDao.getAreaNameByAreaID(areaID);
            String AreaName = td_areas.getAreaName();

            Integer PTid = tp.getPTid();
            //message.setTmnID(TmnName);
            message.setTmnId(TmnId);
            message.setTmnName(TmnName);
            message.setPipId(PipId);
            message.setPTid(PTid);
            message.setAreaID(areaID);
            message.setPipName(PipName);
            message.setAreaName(AreaName);
        }
        return AllError;
    }


//    public td_Tp getALLdetailByTmnID(String TmnId) {
//        return td_tpDao.getAlltdById(TmnId);
//    }
//
//    public void show_detail_deal(String TmnId){
//        errordealRecDao.show_detail_deal(TmnId);
//    }
//
//    public void update_detail_deal(String TmnId){
//        errordealRecDao.update_detail_deal(TmnId);
//    }

//    public List getError() {
//
//        return errordealRecDao.getError();
//    }
//
//    public List getErrorById(List Error) {
//        int i = 0;
//        System.out.println(errordealRecDao.getError());
//        //List error = errordealRecDao.getError();
//        for (i = 0; i < Error.size(); i++) {
//            ErrordealRec message = (ErrordealRec) Error.get(i);
//            Long packageId = message.getPackageId();
//            Rec_Detail TmnId_message = detailDao.getPipeByPackageId(packageId);
//            String TmnId = TmnId_message.getTmnID();
//            Terminals terminals = terminalsDao.getNameByID(TmnId);
//            String TmnName = terminals.getTmnName();
//            td_Tp tp = td_tpDao.getAlltdById(TmnId);
//            String PipId = tp.getPipID();
//            td_PIPs td_pips = td_PipesDao.getAreasIdByPips(PipId);
//            String areaID = td_pips.getAreaID();
//            Integer PTid = tp.getPTid();
//            //message.setTmnID(TmnName);
//            message.setTmnId(TmnId);
//            message.setTmnName(TmnName);
//            message.setPipId(PipId);
//            message.setPTid(PTid);
//            message.setAreaID(areaID);
//        }
//        return Error;
//    }
//


    public void updateError(ErrordealRec errordealRec){
        errordealRecDao.updateError(errordealRec);
    }
}
