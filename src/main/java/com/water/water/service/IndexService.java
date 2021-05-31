package com.water.water.service;

import com.water.water.dao.DetailDao;
import com.water.water.dao.IndexDao;
import com.water.water.dao.TerminalsDao;
import com.water.water.dao.td_error_recDao;
import com.water.water.pojo.ErrordealRec;
import com.water.water.pojo.Rec_Detail;
import com.water.water.pojo.Terminals;
import com.water.water.util.PrintClassName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@Service
public class IndexService {

    @Autowired
    private IndexDao indexDao;
    @Autowired
    private TerminalsDao terminalsDao;
    @Autowired
    private com.water.water.dao.td_error_recDao td_error_recDao;

    public List getAllMessage(){
        return indexDao.getAllMessage();
    }

    public List<Map> getAllMessageToException(){
        List allmessage = indexDao.getAllMessageToException();
        Map fistException = new HashMap();
        List<Map> AllToException = new LinkedList<>();
        Integer yanzhong = 0;
        Integer jinji = 0;
        Integer jinggao = 0;
        for (int i = 0;i<allmessage.size();i++){
            try{
                Rec_Detail message = (Rec_Detail)allmessage.get(i);
                String TmnID = message.getTmnID();
                Short Errorme = 0;
                Short W_line = message.getW_line();
                Short V_status = message.getV_status();
                Short B_status = message.getB_status();
                Short F_volume = message.getF_Volume();
                if(W_line > 90){
                    Errorme = 4;
                    yanzhong++;
                    if(V_status > 90){
                        Errorme = 4;
                        yanzhong++;
                    }
                    if(B_status > 90){
                        Errorme = 4;
                        yanzhong++;
                    }
                    if(F_volume > 90){
                        Errorme = 4;
                        yanzhong++;
                    }
                }
                else if(W_line > 70 && W_line <= 90){
                    Errorme = 3;
                    jinji++;
                    if(V_status > 90){
                        Errorme = 4;
                        yanzhong++;
                    }
                    if(B_status > 90){
                        Errorme = 4;
                        yanzhong++;
                    }
                    if(F_volume > 90){
                        Errorme = 4;
                        yanzhong++;
                    }
                }else if(W_line > 50 && W_line <= 70){
                    Errorme = 2;
                    jinggao++;
                    if(V_status > 90){
                        Errorme = 3;
                        jinji++;
                        if(B_status > 90){
                            Errorme = 4;
                            yanzhong++;
                        }
                        if(F_volume > 90){
                            Errorme = 4;
                            yanzhong++;
                        }
                    }else if(B_status > 90){
                        Errorme = 3;
                        jinji++;
                        if(V_status > 90){
                            Errorme = 4;
                            yanzhong++;
                        }
                        if(F_volume > 90){
                            Errorme = 4;
                            yanzhong++;
                        }
                    }else if(F_volume > 90){
                        Errorme = 3;
                        if(V_status > 90){
                            Errorme = 4;
                            yanzhong++;
                        }
                        if(B_status > 90){
                            Errorme = 4;
                            yanzhong++;
                        }
                    }
                }
            }catch (Exception e){
                System.out.println(e);
            }
        }
        fistException.put("yanzhong",yanzhong);
        fistException.put("jinji",jinji);
        fistException.put("jinggao",jinggao);
        AllToException.add(fistException);
        return AllToException;
    }

    public List getMessageDesc(){
        ErrordealRec errordealRec = new ErrordealRec();
        List allmessage = indexDao.getMessageDesc();
        for (int i = 0;i<allmessage.size();i++){
            try{
                //Rec_Detail message = (Rec_Detail)allmessage.get(i);
                Rec_Detail message = (Rec_Detail)allmessage.get(i);
                String TmnID = message.getTmnID();
                Short Errorme = 0;
                Short W_line = message.getW_line();
                Short V_status = message.getV_status();
                Short B_status = message.getB_status();
                Short F_volume = message.getF_Volume();
                String errormessage = " ";
                if(W_line > 90){
                    Errorme = 4;
                    errormessage = errormessage + ("水位高于百分之九十");
                    if(V_status > 90){
                        Errorme = 4;
                        errormessage = errormessage + ("蓄电池状态异常");
                    }
                    if(B_status > 90){
                        Errorme = 4;
                        errormessage = errormessage + ("油温异常");
                    }
                    if(F_volume > 90){
                        Errorme = 4;
                        errormessage = errormessage + ("环境温度过高");
                    }
                }
                else if(W_line > 70 && W_line <= 90){
                    Errorme = 3;
                    errormessage = errormessage + ("水位高于百分之七十");
                    if(V_status > 90){
                        Errorme = 4;
                        errormessage = errormessage + ("蓄电池状态异常");
                    }
                    if(B_status > 90){
                        Errorme = 4;
                        errormessage = errormessage + ("油温异常");
                    }
                    if(F_volume > 90){
                        Errorme = 4;
                        errormessage = errormessage + ("环境温度过高");
                    }
                }else if(W_line > 50 && W_line <= 70){
                    Errorme = 2;
                    errormessage = errormessage + ("水位高于百分之五十");
                    if(V_status > 90){
                        Errorme = 3;
                        errormessage = errormessage + ("蓄电池状态异常");
                        if(B_status > 90){
                            Errorme = 4;
                            errormessage = errormessage + ("油温异常");
                        }
                        if(F_volume > 90){
                            Errorme = 4;
                            errormessage = errormessage + ("环境温度过高");
                        }
                    }else if(B_status > 90){
                        Errorme = 3;
                        errormessage = errormessage + ("油温异常");
                        if(V_status > 90){
                            Errorme = 4;
                            errormessage = errormessage + ("蓄电池状态异常");
                        }
                        if(F_volume > 90){
                            Errorme = 4;
                            errormessage = errormessage + ("环境温度过高");
                        }
                    }else if(F_volume > 90){
                        Errorme = 3;
                        errormessage = errormessage + ("环境温度过高");
                        if(V_status > 90){
                            Errorme = 4;
                            errormessage = errormessage + ("蓄电池状态异常");
                        }
                        if(B_status > 90){
                            Errorme = 4;
                            errormessage = errormessage + ("油温异常");
                        }
                    }
                }
//                if(Errorme > 0){
//                    errordealRec.setError_Position(errormessage);
//                    errordealRec.setTmnId(TmnID);
//                    errordealRec.setIf_deal("1");
//                    td_error_recDao.insertToNewError(errordealRec);
//                }
                message.setSend_error(Errorme);
                String TmnName;
                if(TmnID == null){
                    TmnName = "名字查询失败";
                }else{
                    Terminals terminals = terminalsDao.getNameByID(TmnID);
                    TmnName = terminals.getTmnName();
                }

                message.setTmnID(TmnName);
            }catch (Exception e){
                Rec_Detail message = new Rec_Detail();
                message.setTmnID("控制柜名称");
                allmessage.set(i,message);
                System.out.println("首页查询数据异常：："+e);
            }
        }
        return allmessage;
    }

    public List getSelectMessage(Map params, Integer page, Integer size){
        String W_work = "";
//        String defult = "";
//        String W_Upline = "";
//        String W_Downline = "";
        for (Object key : params.keySet()){
//            System.out.println("key:=" + key + "  and value:=   " +  params.get(key)+"  \n");
            if (key.equals("W_work")){
                W_work = (params.get("W_work")).toString();
            }
//            if (key.equals("defult")){
//                defult = (params.get("defult")).toString();
////                System.out.println("default:"+defult);
//                if(defult.equals("601")){
//                    W_Upline = "25";
//                    W_Downline = "0";
//                }else if (defult.equals("602")){
//                    W_Upline = "50";
//                    W_Downline = "25";
//                }else if (defult.equals("603")){
//                    W_Upline = "75";
//                    W_Downline = "50";
//                }else if(defult.equals("604")){
//                    W_Upline = "100";
//                    W_Downline = "75";
//                }
//            }
        }
        if (page != null && size != null){
            page = (page - 1) * size;
        }
        //return indexDao.getSelectMessage(W_work,W_Upline,W_Downline);
        return indexDao.getSelectMessage(W_work, page, size);
    }

    public Integer getCountMessage(){
        return indexDao.getCountMessage();
    }

    public Integer getCountMessageByWork(Map params) {
        String W_work = "";
        for (Object key : params.keySet()) {
//            System.out.println("key:=" + key + "  and value:=   " +  params.get(key)+"  \n");
            if (key.equals("W_work")) {
                W_work = (params.get("W_work")).toString();
            }
        }
        return indexDao.getCountMessageByWork(W_work);
    }

    public List getSelectMessageByPage(Integer page,Integer size){
        ErrordealRec errordealRec = new ErrordealRec();
        if (page != null && size != null){
            page = (page - 1) * size;
        }
        List allmessage = indexDao.getSelectMessageByPage(page,size);
        for (int i = 0;i<allmessage.size();i++){
            try{
                //Rec_Detail message = (Rec_Detail)allmessage.get(i);
                Rec_Detail message = (Rec_Detail)allmessage.get(i);
                String TmnID = message.getTmnID();
                Short Errorme = 0;
                Short W_line = message.getW_line();
                Short V_status = message.getV_status();
                Short B_status = message.getB_status();
                Short F_volume = message.getF_Volume();
                String errormessage = " ";
                if(W_line > 90){
                    Errorme = 4;
                    errormessage = errormessage + ("水位高于百分之九十");
                    if(V_status > 90){
                        Errorme = 4;
                        errormessage = errormessage + ("蓄电池状态异常");
                    }
                    if(B_status > 90){
                        Errorme = 4;
                        errormessage = errormessage + ("油温异常");
                    }
                    if(F_volume > 90){
                        Errorme = 4;
                        errormessage = errormessage + ("环境温度过高");
                    }
                }
                else if(W_line > 70 && W_line <= 90){
                    Errorme = 3;
                    errormessage = errormessage + ("水位高于百分之七十");
                    if(V_status > 90){
                        Errorme = 4;
                        errormessage = errormessage + ("蓄电池状态异常");
                    }
                    if(B_status > 90){
                        Errorme = 4;
                        errormessage = errormessage + ("油温异常");
                    }
                    if(F_volume > 90){
                        Errorme = 4;
                        errormessage = errormessage + ("环境温度过高");
                    }
                }else if(W_line > 50 && W_line <= 70){
                    Errorme = 2;
                    errormessage = errormessage + ("水位高于百分之五十");
                    if(V_status > 90){
                        Errorme = 3;
                        errormessage = errormessage + ("蓄电池状态异常");
                        if(B_status > 90){
                            Errorme = 4;
                            errormessage = errormessage + ("油温异常");
                        }
                        if(F_volume > 90){
                            Errorme = 4;
                            errormessage = errormessage + ("环境温度过高");
                        }
                    }else if(B_status > 90){
                        Errorme = 3;
                        errormessage = errormessage + ("油温异常");
                        if(V_status > 90){
                            Errorme = 4;
                            errormessage = errormessage + ("蓄电池状态异常");
                        }
                        if(F_volume > 90){
                            Errorme = 4;
                            errormessage = errormessage + ("环境温度过高");
                        }
                    }else if(F_volume > 90){
                        Errorme = 3;
                        errormessage = errormessage + ("环境温度过高");
                        if(V_status > 90){
                            Errorme = 4;
                            errormessage = errormessage + ("蓄电池状态异常");
                        }
                        if(B_status > 90){
                            Errorme = 4;
                            errormessage = errormessage + ("油温异常");
                        }
                    }
                }
//                if(Errorme > 0){
//                    errordealRec.setError_Position(errormessage);
//                    errordealRec.setTmnId(TmnID);
//                    errordealRec.setIf_deal("1");
//                    td_error_recDao.insertToNewError(errordealRec);
//                }
                message.setSend_error(Errorme);
                String TmnName;
                if(TmnID == null){
                    TmnName = "名字查询失败";
                }else{
                    Terminals terminals = terminalsDao.getNameByID(TmnID);
                    TmnName = terminals.getTmnName();
                }

                message.setTmnID(TmnName);
            }catch (Exception e){
                Rec_Detail message = new Rec_Detail();
                message.setTmnID("控制柜名称");
                allmessage.set(i,message);
                System.out.println("异常"+e);
            }
        }
        return allmessage;
    }
}
