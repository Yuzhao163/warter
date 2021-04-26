package com.water.water.controller;

import com.water.water.service.DetailService;
import com.water.water.service.IndexService;
import com.water.water.service.PipeShowService;
import com.water.water.service.TerminalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
public class IndexContorller {
    @Autowired
    private IndexService indexService;
    @Autowired
    private TerminalsService terminalsService;

    @RequestMapping(value = "api/allmessage")
    public List getAllMessage() throws Exception {
        List allmessage = indexService.getAllMessage();
        List result_selectmessage = terminalsService.getNameByID(allmessage);
        return result_selectmessage;
    }

    @RequestMapping(value = "api/MessageDesc")
    public List getMessageDesc() throws Exception{
        List MessageDesc = indexService.getMessageDesc();
        List result_selectmessage = terminalsService.getNameByID(MessageDesc);
        return result_selectmessage;
    }

    @RequestMapping(value = "api/SelectMessage")
    public List getSelectMessage(@RequestParam Map params) throws Exception {
        System.out.println(params.get("W_work"));
        List selectmessage = indexService.getSelectMessage(params);
        List result_selectmessage = terminalsService.getNameByID(selectmessage);
        return result_selectmessage;
    }

    @RequestMapping(value = "api/count")
    public Integer getCountMessage() throws Exception{
        return indexService.getCountMessage();
    }

    @RequestMapping(value = "api/SelectMessageByPage")
    public List getSelectMessageByPage(@RequestParam Integer page,Integer size) throws Exception{
        return indexService.getSelectMessageByPage(page,size);
    }
}
