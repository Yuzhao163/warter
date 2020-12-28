package com.water.water.controller;

import com.water.water.pojo.Rec_Detail;
import com.water.water.pojo.UserManage;
import com.water.water.service.UserManageService;
import com.water.water.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserManageController {

    @Autowired
    UserManageService userManageService;

    @RequestMapping(value = "api/staff")
    public List<UserManage> getAllUserManage() throws Exception{
    /*  List<UserManage> user = userManageService.getAllUserManage();
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(user);
        jsonObject.put("UserManage",jsonArray);
        model.addAttribute("UserManage",jsonArray.toString());
        return jsonObject.toString();
    */
        System.out.println("进来了嘛》》》》》》》》》》》》？？？？？");
        List<UserManage> user = userManageService.getAllUserManage();
        return user;
    }

  /*  @RequestMapping(value = "api/staff")
    public String deletetd_user(String UserID) throws Exception{
        System.out.println("进来了嘛》》》》》》》》》》》》？？？？？");
        System.out.println(UserID);
        return userManageService.deletetd_user(UserID);
    }

    @RequestMapping(value = "api/staff")
    public int updatetd_user(String UserID) throws Exception{
        System.out.println("进来了嘛》》》》》》》》》》》》？？？？？");
        return userManageService.updatetd_user(UserID);

    }

    @RequestMapping(value = "api/staff")
    public UserManage insertTotd_user(UserManage userManage) throws Exception{
        System.out.println("进来了嘛》》》》》》》》》》》》？？？？？");
        return userManageService.insertTotd_user(userManage);
    }

    @RequestMapping(value = "api/staff")
    public List<UserManage> getByUserManageName(String username) throws Exception{
        System.out.println("进来了嘛》》》》》》》》》》》》？？？？？");
        return userManageService.getByUserManageName(username);
    }
*/

 /*   @ResponseBody
    @GetMapping("api/e")
    public int updatetd_user(UserManage userManage){

    }*/
}
