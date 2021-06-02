package com.water.water.service;


import com.water.water.dao.*;
import com.water.water.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/*
*
* */

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
    td_PipesDao td_PipesDao;
    @Autowired
    td_error_recDao td_error_recDao;
    @Autowired
    UserManageDao userManageDao;
    @Autowired
    td_Tmn_LeaderDao td_tmn_leaderDao;
    @Autowired
    td_ApDao td_apDao;

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

    //??
    public List getErrorsById(List AllError) {

        ArrayList list = new ArrayList();
        //System.out.println(AllError);
        for (int i = 0; i < AllError.size(); i++) {
            Error_Connection error_connection = new Error_Connection();
            ErrordealRec message = (ErrordealRec) AllError.get(i);
            Long ERDId = message.getERDId();
            Long ERId = message.getERId();
            String Exception = message.getException();
            String Result = message.getResult();
            //Date C_t = message.getC_t();
            Timestamp C_t = message.getC_t();
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
            //根据pipid查找对应的areaid
            List areaID = td_apDao.getAIDByPID(PipId);
            //String areaID = td_pips.getAreaID();
            //td_Areas td_areas = td_areasDao.getAreaNameByAreaID(areaID);
            //String AreaName = td_areas.getAreaName();
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
            //error_connection.setAreaName(AreaName);
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
            //String user = terminals.getTmnLeader();
            //获取当前控制柜的管理员
            List tmn_leader = td_tmn_leaderDao.getUIDByTID(TmnId);
            String username = "";
            for(int j = 0;j < tmn_leader.size();j++){
                td_Tmn_Leader tmnleader = (td_Tmn_Leader)tmn_leader.get(j);
                Integer user = tmnleader.getLeader();
                username = username + userManageDao.getUNameByID(user) + ";";

            }
            String TmnName = terminals.getTmnName();
            ErrordealRec errordealRec = new ErrordealRec();
            td_Tp tp = td_tpDao.getAlltdById(TmnId);
            String PipId = tp.getPipID();
            Integer PTid = tp.getPTid();
            //message.setTmnID(TmnName);
            errordealRec.setUser(username);
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
                Long ERID = message.getERId();
                List ErrorDeal = errordealRecDao.getErrorByErId(ERID);
                for(int k = 0;k < ErrorDeal.size();k++){
                    ErrordealRec errordeal = (ErrordealRec)ErrorDeal.get(k);
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

    //5.19
//    public List geterrorbyusername(String TmnLeader) throws Exception{
//        //拿出来当前页的所有数据
//        //对数据进行处理
//        //需要修改
////        List terminal = terminalsDao.getTerminalsByUserName(TmnLeader);
//        //5.17修改
//        List terminal = new ArrayList();
//        UserManage userManage = userManageDao.getUserID(TmnLeader);
//        List tmnID = td_tmn_leaderDao.getRightByID(userManage.getUserID());
//        for(int i = 0; i < tmnID.size(); i++){
//            td_Tmn_Leader td_tmn_leader = (td_Tmn_Leader) tmnID.get(i);
//            String tmnid = td_tmn_leader.getTmnID();
//            String tmnName = terminalsDao.getTmnNameByTmnID(tmnid);
//            Terminals tmn = terminalsDao.getTmnByTmnName(tmnName);
//            terminal.add(tmn);
//        }
//
//        List error = new ArrayList();
//        for(int k = 0;k < terminal.size();k++){
//            Terminals terminals = (Terminals)terminal.get(k);
//            String TmnId = terminals.getTmnId();
//            List Allerror = new ArrayList();
//            try{
//                Allerror = td_error_recDao.getErrorByTmnId(TmnId);
//            }catch(Exception e){
//                Allerror = Allerror;
//            }
//            for (int i = 0; i < Allerror.size(); i++) {
//                td_error_rec message = (td_error_rec)Allerror.get(i);
////                String TmnId = message.getTmnID();
////                Terminals terminals = terminalsDao.getNameByID(TmnId);
//                //String user = terminals.getTmnLeader();
////                UserManage userID = userManageDao.getUserID(TmnLeader);
////                td_tmn_leaderDao.
//                String TmnName = terminals.getTmnName();
//                ErrordealRec errordealRec = new ErrordealRec();
//                td_Tp tp = td_tpDao.getAlltdById(TmnId);
//                String PipId = tp.getPipID();
//                Integer PTid = tp.getPTid();
//                //message.setTmnID(TmnName);
//                errordealRec.setUser(TmnLeader);
//                errordealRec.setTmnId(TmnId);
//                errordealRec.setPipId(PipId);
//                errordealRec.setPTid(PTid);
//                errordealRec.setTmnName(TmnName);
//                errordealRec.setIf_deal(message.getIf_deal());
//                errordealRec.setError_Position(message.getError_Position());
//                errordealRec.setTime(message.getTime());
//                errordealRec.setTmnId(message.getTmnID());
//                errordealRec.setERId(message.getERId());
//                String if_deal = message.getIf_deal();
//                //if_deal如果已经处理过了就是2，如果未处理是1
//                if(if_deal.equals("1")){
//                    error.add(errordealRec);
//                }else{
//                    //由tmnID去td_errordeal_rec表中查找
//                    Short ERID = message.getERId();
//                    List ErrorDeal = errordealRecDao.getErrorByErId(ERID);
//                    for(int j = 0;j < ErrorDeal.size();j++){
//                        ErrordealRec errordeal = (ErrordealRec)ErrorDeal.get(j);
//                        errordealRec.setException(errordeal.getException());
//                        errordealRec.setResult(errordeal.getResult());
//                        errordealRec.setUser(errordeal.getUser());
//                        errordealRec.setC_t(errordeal.getC_t());
//                    }
//                    error.add(errordealRec);
//                }
//            }
//        }
//        return error;
//    }

    public List geterrorbyusername(String TmnLeader, Integer page, Integer size) throws Exception{
        //拿出来当前页的所有数据
        //对数据进行处理
        //需要修改
//        List terminal = terminalsDao.getTerminalsByUserName(TmnLeader);
        //分页
        if (page != null && size != null){
            page = (page - 1) * size;
        }

        //5.17修改
        List terminal = new ArrayList();
        UserManage userManage = userManageDao.getUserID(TmnLeader);
        List tmnID = td_tmn_leaderDao.getRightByID(userManage.getUserID());
        for(int i = 0; i < tmnID.size(); i++){
            td_Tmn_Leader td_tmn_leader = (td_Tmn_Leader) tmnID.get(i);
            String tmnid = td_tmn_leader.getTmnID();
//            System.out.println("该控制柜ID的顺序为"+tmnid);
//            String tmnid = String.valueOf(tmnID.size() - (i+1));
            String tmnName = terminalsDao.getTmnNameByTmnID(tmnid);
            Terminals tmn = terminalsDao.getTmnByTmnName(tmnName);
            //terminal.add(tmn);
            terminal.add(tmnid);
        }
        //获取名下每一个控制柜。然后遍历控制柜得到每一个控制柜的异常情况
        //以上方式会因为分页，每次只能取出5个控制柜的异常，又因为是对控制柜进行循环，所以得到结果是控制柜个数*异常
        //由此分页错误，所以，先将所有控制柜的ID取出，通过SQL语句的改进，完成分页
//        System.out.println("取出该员工名下控制柜ID"+terminal);

        List error = new ArrayList();
//        System.out.println("取出该员工名下控制柜的数量"+terminal.size());
        Integer userID = userManage.getUserID();
        List rightByID = td_tmn_leaderDao.getRightByID(userID);

        if(rightByID.size() != 0){
            List errorByTmnId = td_error_recDao.getErrorByTmnId(terminal, page, size);
        for(int j = 0; j < errorByTmnId.size(); j++){
            System.out.println("这是我取得的异常的个数"+(td_error_rec)errorByTmnId.get(j));
        }

            for(int i =0; i < errorByTmnId.size();i++){
                td_error_rec td_error_rec = (td_error_rec) errorByTmnId.get(i);
                String tmnID1 = td_error_rec.getTmnID();
                //获得该控制柜的ID，通过该ID找到控制柜的名称
                Terminals nameByID = terminalsDao.getNameByID(tmnID1);
                String tmnName = nameByID.getTmnName();

                ErrordealRec errordealRec = new ErrordealRec();
                td_Tp tp = td_tpDao.getAlltdById(tmnID1);
                String PipId = tp.getPipID();
                Integer PTid = tp.getPTid();
                //message.setTmnID(TmnName);
                errordealRec.setUser(TmnLeader);
                errordealRec.setTmnId(tmnID1);
                errordealRec.setPipId(PipId);
                errordealRec.setPTid(PTid);
                errordealRec.setTmnName(tmnName);
                errordealRec.setIf_deal(td_error_rec.getIf_deal());
                errordealRec.setError_Position(td_error_rec.getError_Position());
                errordealRec.setTime(td_error_rec.getTime());
                errordealRec.setTmnId(td_error_rec.getTmnID());
                errordealRec.setERId(td_error_rec.getERId());
                String if_deal = td_error_rec.getIf_deal();
                //if_deal如果已经处理过了就是2，如果未处理是1
                if(if_deal.equals("1")){
                    error.add(errordealRec);
                }else{
                    //由tmnID去td_errordeal_rec表中查找
                    Long ERID = td_error_rec.getERId();
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


//        for(int k = 0;k < terminal.size();k++){
//            Terminals terminals = (Terminals)terminal.get(k);
//            String TmnId = terminals.getTmnId();
//            List Allerror = new ArrayList();
//            try{
//                Allerror = td_error_recDao.getErrorByTmnId(TmnId, page, size);
//            }catch(Exception e){
//                Allerror = Allerror;
//            }
//            for (int i = 0; i < Allerror.size(); i++) {
//                td_error_rec message = (td_error_rec)Allerror.get(i);
////                String TmnId = message.getTmnID();
////                Terminals terminals = terminalsDao.getNameByID(TmnId);
//                //String user = terminals.getTmnLeader();
////                UserManage userID = userManageDao.getUserID(TmnLeader);
////                td_tmn_leaderDao.
//                String TmnName = terminals.getTmnName();
//                ErrordealRec errordealRec = new ErrordealRec();
//                td_Tp tp = td_tpDao.getAlltdById(TmnId);
//                String PipId = tp.getPipID();
//                Integer PTid = tp.getPTid();
//                //message.setTmnID(TmnName);
//                errordealRec.setUser(TmnLeader);
//                errordealRec.setTmnId(TmnId);
//                errordealRec.setPipId(PipId);
//                errordealRec.setPTid(PTid);
//                errordealRec.setTmnName(TmnName);
//                errordealRec.setIf_deal(message.getIf_deal());
//                errordealRec.setError_Position(message.getError_Position());
//                errordealRec.setTime(message.getTime());
//                errordealRec.setTmnId(message.getTmnID());
//                errordealRec.setERId(message.getERId());
//                String if_deal = message.getIf_deal();
//                //if_deal如果已经处理过了就是2，如果未处理是1
//                if(if_deal.equals("1")){
//                    error.add(errordealRec);
//                }else{
//                    //由tmnID去td_errordeal_rec表中查找
//                    Short ERID = message.getERId();
//                    List ErrorDeal = errordealRecDao.getErrorByErId(ERID);
//                    for(int j = 0;j < ErrorDeal.size();j++){
//                        ErrordealRec errordeal = (ErrordealRec)ErrorDeal.get(j);
//                        errordealRec.setException(errordeal.getException());
//                        errordealRec.setResult(errordeal.getResult());
//                        errordealRec.setUser(errordeal.getUser());
//                        errordealRec.setC_t(errordeal.getC_t());
//                    }
//                    error.add(errordealRec);
//                }

        }
        return error;
    }

//        public List getAllErrorById(List AllError) {
//
//        return AllError;
//    }
//    public Integer InsertToError(ErrordealRec errordealRec){
//        String TmnID = errordealRec.getTmnId();
//        //根据tmnID去总异常表中查找该条记录，判断if_deal是否为2，如果不是的话则改成2，如果是的话则不变
//        td_error_rec error = td_error_recDao.getIfByTmnId(TmnID);
//        String if_deal = error.getIf_deal();
//        if(if_deal.equals("1")){
//            td_error_recDao.updateByTmnId(TmnID);
//        }else{
//            return errordealRecDao.InsertToError(errordealRec);
//        }
//        return errordealRecDao.InsertToError(errordealRec);
//    };

    //5.19
//    public Integer InsertToError(ErrordealRec errordealRec){
//    String TmnID = errordealRec.getTmnId();
//    //根据tmnID去总异常表中查找该条记录，判断if_deal是否为2，如果不是的话则改成2，如果是的话则不变
//    td_error_rec error = td_error_recDao.getIfByTmnId(TmnID);
////    //将当前时间添加到C_t中
////    java.util.Date date = new java.util.Date();//获取当前时间
////    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////    String str = sdf.format(date);//时间存储为字符串
////    Timestamp now = Timestamp.valueOf(str);//转换时间字符串为Timestamp
////    errordealRec.setC_t(now);
//
//    String if_deal = error.getIf_deal();
//    if(if_deal.equals("1")){
//        td_error_recDao.updateByTmnId(TmnID);
//    }else{
//
//        //5.8对已经处理过的数据进行追加操作
//        return errordealRecDao.appendToError(errordealRec);
////            return errordealRecDao.InsertToError(errordealRec);
//
//    }
//    //return errordealRecDao.InsertToError(errordealRec);
//    return errordealRecDao.appendToError(errordealRec);
//};

    public Integer InsertToError(ErrordealRec errordealRec){
//        System.out.println("异常处理记录在此"+errordealRec);
//        String TmnID = errordealRec.getTmnId();
        Long erId = errordealRec.getERId();
        //根据tmnID去总异常表中查找该条记录，判断if_deal是否为2，如果不是的话则改成2，如果是的话则不变
        //5.19注释掉，因为单个控制柜的故障有非常多
        //td_error_rec error = td_error_recDao.getIfByTmnId(TmnID);

//    //将当前时间添加到C_t中
//    java.util.Date date = new java.util.Date();//获取当前时间
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    String str = sdf.format(date);//时间存储为字符串
//    Timestamp now = Timestamp.valueOf(str);//转换时间字符串为Timestamp
//    errordealRec.setC_t(now);

        //5.19添加
        td_error_rec error = td_error_recDao.getIfByErid(erId);
        //5.19getIfByTmnId需要修改为getIfByErid，因为每一条异常都需要处理记录，而控制柜可能有很多异常部位
        //List<td_error_rec> ifDeal = td_error_recDao.getIfByTmnId(TmnID);

        String if_deal = error.getIf_deal();
        if(if_deal.equals("1")){
            //5.19
            // td_error_recDao.updateByTmnId(TmnID);
            //依照Erid来进行更新if_deal
            td_error_recDao.updateByErid(erId);
        }else{

            //5.8对已经处理过的数据进行追加操作
            return errordealRecDao.appendToError(errordealRec);
//            return errordealRecDao.InsertToError(errordealRec);

        }
        //return errordealRecDao.InsertToError(errordealRec);
        return errordealRecDao.appendToError(errordealRec);
    }

    //5.8----------------------------------------------------------------------------------------------
//    public List geterrorbytmnID(String TmnID) {
//        //通过TmnID从td_errordeal_rec表中获取对应TmnID的处理记录
//        List errorByTmnID = errordealRecDao.getAllErrorByTmnID(TmnID);
//        System.out.println(errorByTmnID);
//        return errorByTmnID;
//    }

    public List geterrorbytmnID(Long Erid) {
        //通过TmnID从td_errordeal_rec表中获取对应TmnID的处理记录
        List errorByTmnID = errordealRecDao.getAllErrorByTmnID(Erid);
//        System.out.println(errorByTmnID);
        return errorByTmnID;
    }

    //5.19统计该员工名下控制柜异常数量
    public Integer getCountNum(String tmnLeader) {
        Integer countNum = 0;
//        System.out.println("这是登录用户的名称"+tmnLeader);//测试
        UserManage userManage = userManageDao.getUserID(tmnLeader);
        Integer userID = userManage.getUserID();
//        System.out.println("这是故障处理中该员工的userID"+userID);//测试

        List rightByID = td_tmn_leaderDao.getRightByID(userID);
        System.out.println("这个人取得了控制柜的ID是"+rightByID);
        if(rightByID.size() != 0){
            for(int i = 0; i < rightByID.size(); i++){
                td_Tmn_Leader td_tmn_leader = (td_Tmn_Leader) rightByID.get(i);
                String tmnID = td_tmn_leader.getTmnID();
//            System.out.println("这是该员工控制的控制柜的ID"+tmnID);//测试

                countNum += td_error_recDao.getCountNum(tmnID);
            }
//        System.out.println("该员工控制的控制柜下故障总数"+countNum);//测试
            return countNum;
        } else {
            return 0;
        }

    }

    //5.19使用Erid去查询该控制柜异常状态的处理记录
    public List geterrorbyErid(Long erid) {
        List errorByErId = errordealRecDao.getErrorByErId(erid);
        return errorByErId;
    }
}
