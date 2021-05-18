package com.water.water.controller;
//异常处理
//2021.3.24
//author 李轶杰
import com.water.water.Result.Result;
import com.water.water.pojo.ErrordealRec;
import com.water.water.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

//    @RequestMapping(value = "api/errors")
//    @ResponseBody
//    public List getErrors(){
//        List allerror = errordealRecService.getErrors();
//        List allerrorbyid = errordealRecService.getErrorsById(allerror);
//        return allerrorbyid;
//    }

    @RequestMapping(value = "api/adderror")
    public Integer insertError(ErrordealRec errordealRec){
        return errordealRecService.updateError(errordealRec);
    }

    @RequestMapping(value = "api/Errorcount")
    public Integer getCountMessage() throws Exception{
        return errordealRecService.getCountMessage();
    }

    @RequestMapping(value = "api/SelectErrorMessageByPage")
    public List getSelectMessageByPage(@RequestParam Integer page,Integer size) throws Exception{
        return errordealRecService.getSelectErrorMessageByPage(page,size);
    }
}

