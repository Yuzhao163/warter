package com.water.water.service;


import com.mysql.cj.xdevapi.JsonArray;
import com.water.water.dao.*;
import com.water.water.pojo.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
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

//    class message{
//        private
//    }
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
/*这部分是4.8注释掉的数据*/
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
//            td_PIPs td_pips = td_PipesDao.getAreasIdByPips(PipId);
//            String areaID = td_pips.getAreaID();
//            td_PIPs td_piPs = td_PipesDao.getPipNameByPipID(PipId);
//            String PipName = td_piPs.getPipName();
//            td_Areas td_areas = td_areasDao.getAreaNameByAreaID(areaID);
//            String AreaName = td_areas.getAreaName();
//
//            Integer PTid = tp.getPTid();
//            //message.setTmnID(TmnName);
//
////
////            AllError.add(areaID);
////            AllError.add(PipName);
////            AllError.add(AreaName);
//            message.setTmnId(TmnId);
//            message.setTmnName(TmnName);
//            message.setPipId(PipId);
//            message.setPTid(PTid);
////            message.setAreaID(areaID);
////            message.setPipName(PipName);
////            message.setAreaName(AreaName);
//
//        }
//        return AllError;
//    }

    public List getAllError() {
        return errordealRecDao.getAllError();
    }
    public List getAllErrorById(List AllError) {

        ArrayList list = new ArrayList();
        System.out.println(AllError);
        for (int i = 0; i < AllError.size(); i++) {
            Error_Connection error_connection = new Error_Connection();
            ErrordealRec message = (ErrordealRec) AllError.get(i);
            System.out.println("吼吼哦吼"+message);
            Short ERDId = message.getERDId();
            Short ERId = message.getERId();
            String Exception = message.getException();
            String Result = message.getResult();
            Date C_t = message.getC_t();
            String User = message.getUser();
            Long PackageId = message.getPackageId();
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
            error_connection.setERDId(ERDId);
            error_connection.setERId(ERId);
            error_connection.setException(Exception);
            error_connection.setResult(Result);
            error_connection.setC_t(C_t);
            error_connection.setUser(User);
            error_connection.setPackageId(PackageId);
            error_connection.setTmnId(TmnId);
            error_connection.setTmnName(TmnName);
            error_connection.setPipId(PipId);
            error_connection.setPTid(PTid);
            error_connection.setAreaName(AreaName);
            list.add(error_connection);
            System.out.println("哈哈哈哈哈哈哈哈哈哈" + list);
//            AllError.add(areaID);
//            AllError.add(PipName);
//            AllError.add(AreaName);
//            message.setTmnId(TmnId);
//            message.setTmnName(TmnName);
//            message.setPipId(PipId);
//            message.setPTid(PTid);
//            message.setAreaID(areaID);
//            message.setPipName(PipName);
//            message.setAreaName(AreaName);

        }
        for(Object s:list){
            System.out.println(s);
        }


        return list;
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


    public Integer updateError(ErrordealRec errordealRec){
        System.out.println("我这边插入错误啦");
        System.out.println(errordealRec.toString());
        return errordealRecDao.updateError(errordealRec);
    }
}
