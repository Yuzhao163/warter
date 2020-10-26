package com.water.water.controller;

import com.water.water.pojo.Rec_Detail;
import com.water.water.service.ShowService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@Controller
public class JsonController {

    @Autowired
    private ShowService showService;

    @RequestMapping("api/temp")
    @ResponseBody
    public String jsonReturnValue(Model model, Rec_Detail rec_detail){
        List<Rec_Detail> show = showService.show(rec_detail);
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(show);
        jsonObject.put("Rec_Detail",jsonArray);
        model.addAttribute("Rec_Detail",jsonArray.toString());
        return jsonObject.toString();
    }
}
