package com.water.water.controller;


import com.water.water.pojo.Rec_Detail;
import com.water.water.service.PipeShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class PipeShowContorller {

    @Autowired
    private PipeShowService pipeShowService;

    @GetMapping(value = "api/getPipe")
    @ResponseBody
    public Map<String, Rec_Detail> getPipe() {
        // 对 html 标签进行转义，防止 XSS 攻击
        Map<String,Rec_Detail> pipedata = pipeShowService.getPipe();
        return pipedata;
    }
}
