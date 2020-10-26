package com.water.water.controller;


import com.water.water.service.PipeShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class PipeShowContorller {

    @Autowired
    private PipeShowService pipeShowService;

    @GetMapping(value = "api/getPipe")
    @ResponseBody
    public List getPipe() {
        // 对 html 标签进行转义，防止 XSS 攻击
        System.out.println("进来");
        List pipedata = pipeShowService.getPipe();
        System.out.println("调用123456");
        System.out.println(pipedata);
        return pipedata;
    }
}
