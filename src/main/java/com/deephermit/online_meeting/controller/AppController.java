package com.deephermit.online_meeting.controller;

import com.deephermit.online_meeting.model.UserInfo;
import com.deephermit.online_meeting.service.UserService;
import com.deephermit.online_meeting.service.VerificationCodeService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableSwagger2
@RestController
@RequestMapping("/user")
public class AppController {
    @Autowired(required = false)
    private UserService userService;
    @Autowired(required = false)
    private VerificationCodeService verificationCodeService;
    //      map:
//      1.user:账号，密码，历史会议，预约会议，
//      2.msg:
//        1.账号或者密码错误，登录失败   !
//        2.已经在其他地方登录
//        3.登录成功
//      3.result
    @RequestMapping(value = "/login")
    public Map<String,Object> login(@RequestParam("account")String account, @RequestParam("password")String password,@RequestParam("suid") String suid,@RequestParam("verificationCode") String verificationCode){
        Map<String,Object> map=new HashMap<>();
        if(!verificationCodeService.isVerificationCodeRight(suid,verificationCode)){
            map.put("msg","验证码错误！");
            map.put("result",false);
            return map;
        }
        UserInfo userInfo = userService.getAccount(account,password);
        if(userInfo==null) {
            map.put("msg", "账号或者密码错误，登录失败！");
            map.put("result", false);
        }
        else{
//            获取所有个人信息
            verificationCodeService.updateDevUser(suid,userInfo.getUser_id());
            map.put("userInfo",userInfo);
            map.put("msg","登录成功！");
            map.put("reslut",true);
        }
        return map;
    }
    @RequestMapping(value = "/loginIsValid")
    public Map<String,Object> loginIsValid(@RequestParam("user_id") String user_id,@RequestParam("login_code") String login_code){
        Map<String,Object> map=new HashMap<>();
        if(userService.isLoginValid(user_id,login_code)){
            map.put("valid",true);
            map.put("msg","登录有效!");
            map.put("result",true);
        }else {
            map.put("valid",false);
            map.put("msg","登录无效！");
            map.put("result",false);
        }
        return map;
    }
    @RequestMapping(value = "/getSuid")
    public Map<String,Object> getSuid(){
        Map<String,Object> map=new HashMap<>();
        String suid = verificationCodeService.addAndGetSuid();
        map.put("suid",suid);
        map.put("msg","获取专用唯一性设备标识码成功!");
        map.put("result",true);
        return map;
    }
    @RequestMapping(value = "/isSuidValid")
    public Map<String,Object> isSuidValid(@RequestParam("suid") String suid){
        Map<String,Object> map = new HashMap<>();
        if(verificationCodeService.isSuidValid(suid)){
            map.put("valid",true);
            map.put("msg","专用唯一性设备标识码有效!");
            map.put("result",true);
        }else {
            map.put("valid",false);
            map.put("msg","专用唯一性设备标识码无效！");
            map.put("result",false);
        }
        return map;
    }
    @RequestMapping(value = "/getVerificationCode")
    public Map<String,Object> getVerificationCode(@RequestParam("suid") String suid){
        Map<String,Object> map = new HashMap<>();
        if(verificationCodeService.isSuidValid(suid)){
            String verificationCode = verificationCodeService.getVerificationCode(suid);
            map.put("verificationCode",verificationCode);
            map.put("msg","获取验证码成功！");
            map.put("result",true);
        }else{
            map.put("msg","获取验证码失败，专用唯一性设备标识码无效！");
            map.put("result",false);
        }
        return map;
    }
}
