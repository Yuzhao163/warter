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
    public List geterrorbyusername(@RequestParam String TmnLeader) throws Exception{
        String tmnleader = TmnLeader;
        List errordata =
                errordealRecService.geterrorbyusername(tmnleader);
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
    @RequestMapping(value = "api/geterrorbytmnleader")
    public List geterrorbytmnleader(@RequestParam String TmnID) throws Exception{
        String tmnID = TmnID;

        List errordata = errordealRecService.geterrorbytmnID(tmnID);
        return errordata;
    }

    //5.9--------------------------------------------------------------------------
    @RequestMapping(value = "api/getTerminalError")
    public List getTerminalError(@RequestParam String TmnID) throws Exception{
        String tmnID = TmnID;

        List error = errordealRecService.geterrorbytmnID(tmnID);

        return error;
    }
}

