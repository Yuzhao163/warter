package com.water.water.controller;


import com.water.water.pojo.Rec_Detail;
import com.water.water.service.PipeShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "api/getpipebyusername")
    public Map<String , Rec_Detail> getPipeByUserName(@RequestParam String UserName){
        String userName = UserName;
        Map<String , Rec_Detail> pipedata = pipeShowService.getPipeByUserName(userName);
        return pipedata;
    }
}
