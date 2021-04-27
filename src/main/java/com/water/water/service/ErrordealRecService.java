package com.water.water.service;


import com.water.water.dao.*;
import com.water.water.pojo.*;
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
    td_AreasDao td_areasDao;
    @Autowired
    com.water.water.dao.td_PipesDao td_PipesDao;
    @Autowired
    td_error_recDao td_error_recDao;

    //public ErrordealRec getAllError(){
    //return errordealRecDao.getAllError();
    //}
    public List getAllError() {
        return errordealRecDao.getAllError();
    }

    public List getAllErrorById(List AllError) {
        for (int i = 0; i < AllError.size(); i++) {
            ErrordealRec message = (ErrordealRec) AllError.get(i);
            String TmnId = message.getTmnId();
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

    public List getErrors() {
        return errordealRecDao.getAllError();
    }

    public List getErrorsById(List AllError) {

        ArrayList list = new ArrayList();
        System.out.println(AllError);
        for (int i = 0; i < AllError.size(); i++) {
            Error_Connection error_connection = new Error_Connection();
            ErrordealRec message = (ErrordealRec) AllError.get(i);
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
            td_Areas td_areas = td_areasDao.getAreaNameByAreaID(areaID);
            String AreaName = td_areas.getAreaName();
            Integer PTid = tp.getPTid();
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
        }
        return list;
    }

    public Integer updateError(ErrordealRec errordealRec) {
        return errordealRecDao.updateError(errordealRec);
    }
    //统计td_error_rec表中数据总数
    public Integer getCountMessage(){
        return td_error_recDao.getCountErrorMessage();
    }
    //有了总数，有了page,size之后，需要判断是否提交过。
    public List getSelectErrorMessageByPage(Integer page,Integer size){
        if (page != null && size != null){
            page = (page - 1) * size;
        }
        //拿出来当前页的所有数据
        //对数据进行处理
        List Allerror = td_error_recDao.getSelectMessageByPage(page,size);
        List error = new ArrayList();
        for (int i = 0; i < Allerror.size(); i++) {
            td_error_rec message = (td_error_rec)Allerror.get(i);
            String TmnId = message.getTmnID();
            Terminals terminals = terminalsDao.getNameByID(TmnId);
            String user = terminals.getTmnLeader();
            String TmnName = terminals.getTmnName();
            ErrordealRec errordealRec = new ErrordealRec();
            td_Tp tp = td_tpDao.getAlltdById(TmnId);
            String PipId = tp.getPipID();
            Integer PTid = tp.getPTid();
            //message.setTmnID(TmnName);
            errordealRec.setUser(user);
            errordealRec.setTmnId(TmnId);
            errordealRec.setPipId(PipId);
            errordealRec.setPTid(PTid);
            errordealRec.setTmnName(TmnName);
            errordealRec.setIf_deal(message.getIf_deal());
            errordealRec.setError_Position(message.getError_Position());
            errordealRec.setTime(message.getTime());
            errordealRec.setTmnId(message.getTmnID());
            errordealRec.setERId(message.getERId());
            String if_deal = message.getIf_deal();
            //if_deal如果已经处理过了就是2，如果未处理是1
            if(if_deal.equals("1")){
                error.add(errordealRec);
            }else{
                //由tmnID去td_errordeal_rec表中查找
                Short ERID = message.getERId();
                List ErrorDeal = errordealRecDao.getErrorByErId(ERID);
                for(int j = 0;j < ErrorDeal.size();j++){
                    ErrordealRec errordeal = (ErrordealRec)ErrorDeal.get(j);
                    errordealRec.setException(errordeal.getException());
                    errordealRec.setResult(errordeal.getResult());
                    errordealRec.setUser(errordeal.getUser());
                    errordealRec.setC_t(errordeal.getC_t());
                }
                error.add(errordealRec);
            }
        }
        return error;
    }

//        public List getAllErrorById(List AllError) {
//
//        return AllError;
//    }


}
