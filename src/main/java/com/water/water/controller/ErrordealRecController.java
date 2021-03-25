package com.water.water.controller;

import com.water.water.Result.Result;
import com.water.water.service.CommRecService;
import com.water.water.service.ErrordealRecService;
import com.water.water.service.IndexService;
import com.water.water.service.TerminalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
public class ErrordealRecController {
        @Autowired
        private ErrordealRecService errordealRecService;

    @RequestMapping(value = "api/error")
    @ResponseBody
    public List getAllError() throws Exception {
        List allerror = errordealRecService.getAllError();
        return allerror;
    }
}

