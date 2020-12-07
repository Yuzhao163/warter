package com.water.water.controller;

import com.water.water.Result.Result;
import com.water.water.pojo.CommRec;
import com.water.water.pojo.User;
import com.water.water.service.CommRecService;
import com.water.water.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.Map;


@CrossOrigin
@RestController
public class CommRecController {

    @Autowired
    private CommRecService commRecService;

    @RequestMapping(value = "api/ma",method = RequestMethod.POST)
    public Result regist(@RequestBody Map<String,String> loginMap){
        System.out.println("调用le 1");
        try {
            System.out.println("进来了");
            System.out.println(loginMap);
            String loginMapclass = loginMap.getClass().getName().toString();
            System.out.println(loginMapclass);
            for(Map.Entry<String, String> entry : loginMap.entrySet()) {
                System.out.println("key:" + entry.getKey() + "value:" + entry.getValue());
            }
            return new Result(200);
        } catch (Exception e) {
            System.out.println("错了");
            System.out.println(e);
            return new Result(400);
        }
}
}
