package com.water.water.controller;



import com.water.water.service.TerminalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
public class SelectController {

    @Autowired
    private TerminalsService terminalsService;



    @GetMapping(value = "api/getarea")
    @ResponseBody
    public List getTmnId() {
        // 对 html 标签进行转义，防止 XSS 攻击
        System.out.println("进来");
        List TmnID = terminalsService.getTmnID();
        System.out.println("调用123456");
        System.out.println(TmnID);
        return TmnID;
    }


}

