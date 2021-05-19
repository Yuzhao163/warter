package com.water.water.controller;

import com.water.water.Result.Result;
import com.water.water.netty.MyServerHandler;
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
    @Autowired
    private MyServerHandler myServerHandler;

    @RequestMapping(value = "api/ma",method = RequestMethod.POST)
    public Result regist(@RequestBody Map<String,String> loginMap){
        try {
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

    @RequestMapping(value = "api/order")
    public Result SendOrder(CommRec commRec) throws Exception{
        commRec.setD_ID("1");
        String IP = commRecService.GetIp(commRec);
        myServerHandler.channelActive(commRec,IP);
       return commRecService.SendOrder(commRec);
    }

    @RequestMapping(value = "api/sendall")
    public Result sendall(Integer openorclose) throws Exception{
        try{
            myServerHandler.sendall(openorclose);
            return new Result(200);
        }catch (Exception e){
            System.out.println(e);
            return new Result(400);
        }
    }
}
