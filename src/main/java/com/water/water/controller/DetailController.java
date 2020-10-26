package com.water.water.controller;


import com.water.water.dao.DetailDao;
import com.water.water.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class DetailController {

    @Autowired
    private DetailService detailService;

    @RequestMapping(value = "api/detail")
    public List getVper(@RequestParam Integer Id) throws Exception{
        Integer id = Id;
        System.out.println("正在查询阀开度");
        List Vper = detailService.getVper(id);

        System.out.println("得到的阀开度是："+Vper);

        return Vper;
    }

}