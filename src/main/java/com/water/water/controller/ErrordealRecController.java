package com.water.water.controller;
//异常处理
//2021.3.24
//author 李轶杰
import com.mysql.cj.xdevapi.JsonArray;
import com.water.water.Result.Result;
import com.water.water.pojo.ErrordealRec;
import com.water.water.service.*;
import net.sf.json.JSONArray;
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
        System.out.println(allerrorbyid);
        //allerror.add()
        return allerrorbyid;
    }


//    @RequestMapping(value = "api/showde")
//    public void show_detail_deal(String TmnId){
//        errordealRecService.show_detail_deal(TmnId);
//    }
//
//    @RequestMapping(value = "api/updatede")
//    public void update_detail_deal(String TmnId){
//        errordealRecService.update_detail_deal(TmnId);
//    }

//    @RequestMapping(value = "api/geterror")
//    public JSONArray getError() throws Exception{
//        List error = errordealRecService.getError();
//        JSONArray errors = errordealRecService.getErrorById(error);
//        return errors;
//    }

    @RequestMapping(value = "api/adderror")
    public void insertError(String TmnId){
        errordealRecService.updateError(TmnId);
    }
}

