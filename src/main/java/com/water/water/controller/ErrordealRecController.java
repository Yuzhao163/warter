package com.water.water.controller;
//异常处理
//2021.3.24
//author 李轶杰
import com.water.water.Result.Result;
import com.water.water.pojo.ErrordealRec;
import com.water.water.pojo.Rec_Detail;
import com.water.water.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
public class ErrordealRecController {
        @Autowired
        private ErrordealRecService errordealRecService;
        @Autowired
        private DetailService detailService;

    @RequestMapping(value = "api/error")
    @ResponseBody
    public List getAllError() throws Exception {
        List allerror = errordealRecService.getAllError();
        List allerrorbyid = errordealRecService.getAllErrorById(allerror);
        //allerror.add()
        return allerrorbyid;
    }

    @RequestMapping(value = "api/errors")
    @ResponseBody
    public List getErrors(){
        List allerror = errordealRecService.getErrors();
        List allerrorbyid = errordealRecService.getErrorsById(allerror);
        return allerrorbyid;
    }

    @RequestMapping(value = "api/adderror")
    public Integer insertError(ErrordealRec errordealRec){
        return errordealRecService.updateError(errordealRec);
    }

    @RequestMapping(value = "api/Errorcount")
    public Integer getCountMessage() throws Exception{
        return errordealRecService.getCountMessage();
    }

    @RequestMapping(value = "api/SelectErrorMessageByPage")
    public List getSelectMessageByPage(@RequestParam Integer page,
                                      Integer size) throws Exception{
        return errordealRecService.getSelectErrorMessageByPage(page,size);
    }

    @RequestMapping(value = "api/geterrorbyusername")
    public List geterrorbyusername(@RequestParam String TmnLeader, Integer page, Integer size) throws Exception{
//        System.out.println("页面个数"+page);
//        System.out.println("页面大小"+size);
        String tmnleader = TmnLeader;
        List errordata = errordealRecService.geterrorbyusername(tmnleader, page, size);
//        for(int i = 0; i < errordata.size(); i++){
//            ErrordealRec errordealRec = (ErrordealRec) errordata.get(i);
//            System.out.println("分页拿到的数据"+errordealRec);
//        }
        return errordata;
    }

    @RequestMapping(value = "api/dealerror")
    public Integer InsertToError(ErrordealRec errordealRec) throws Exception{
        //System.out.println(errordealRec);
//        java.util.Date date = new java.util.Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        //获取String类型的时间
//        String createdate = sdf.format(date);
//        //Date c_t = sdf.parse(createdate);
//        Date c_t = sdf.parse(createdate);
//        errordealRec.setC_t(c_t);
        return errordealRecService.InsertToError(errordealRec);
    }

    //5.8-------------------------------------------------------------------------
//    @RequestMapping(value = "api/geterrorbytmnleader")
//    public List geterrorbytmnleader(@RequestParam String TmnID) throws Exception{
//        String tmnID = TmnID;
//
//        List errordata = errordealRecService.geterrorbytmnID(tmnID);
//        return errordata;
//    }
    @RequestMapping(value = "api/geterrorbyerid")
    public List geterrorbytmnleader(@RequestParam Long Erid) throws Exception{
        //根据异常记录的Erid，去error_deal_rec中查找其对应的处理记录
        List errordata = errordealRecService.geterrorbyErid(Erid);
        return errordata;
    }
    //5.9--------------------------------------------------------------------------
//    @RequestMapping(value = "api/getTerminalError")
//    public List getTerminalError(@RequestParam String TmnID) throws Exception{
//        String tmnID = TmnID;
//
//        List error = errordealRecService.geterrorbytmnID(tmnID);
//
//        return error;
//    }

    @RequestMapping(value = "api/getTerminalError")
    public List getTerminalError(@RequestParam Long Erid) throws Exception{

        List error = errordealRecService.geterrorbytmnID(Erid);

        return error;
    }

    //5.19统计数据总数
    @RequestMapping(value = "api/getCountNum")
    public Integer getCountNum(String TmnLeader){
//        System.out.println("这是前端传来的异常控制柜的控制者名称"+TmnLeader);
        return errordealRecService.getCountNum(TmnLeader);
    }
}

