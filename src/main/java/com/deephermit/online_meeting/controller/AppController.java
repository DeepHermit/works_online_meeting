package com.deephermit.online_meeting.controller;

import com.deephermit.online_meeting.mapper.UserInfoMapper;
import com.deephermit.online_meeting.mapper.UserStatusMapper;
import com.deephermit.online_meeting.model.UserInfo;
import com.deephermit.online_meeting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashMap;
import java.util.Map;

@EnableSwagger2
@RestController
@RequestMapping("/user")
public class AppController {
    @Autowired(required = false)
    private UserService userService;
    //      map:
//      1.user:账号，密码，历史会议，预约会议，
//      2.msg:
//        1.账号或者密码错误，登录失败   !
//        2.已经在其他地方登录
//        3.登录成功
//      3.result
    @RequestMapping(value = "/login")
    public Map<String,Object> login(@RequestParam("account")String account, @RequestParam("password")String password){
        Map<String,Object> map=new HashMap<>();
        UserInfo userInfo = userService.getAccount(account,password);
        if(userInfo==null) {
            map.put("msg", "账号或者密码错误，登录失败！");
            map.put("result", false);
        }
        else{
//            更新登录码
            String loginCode=userService.updateLoginCode(userInfo.getUser_id());
//            获取所有个人信息
            map.put("loginCode",loginCode);
            map.put("userInfo",userInfo);
            map.put("msg","登录成功！");
            map.put("reslut",true);
        }
        return map;
    }
    @RequestMapping(value = "/loginIsValid")
    public Map<String,Object> loginIsValid(){
        Map<String,Object> map=new HashMap<>();

        return map;
    }
}
