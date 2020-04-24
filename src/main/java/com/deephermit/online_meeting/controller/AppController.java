package com.deephermit.online_meeting.controller;

import com.deephermit.online_meeting.model.MeetingPassword;
import com.deephermit.online_meeting.model.UserInfo;
import com.deephermit.online_meeting.model.UserPassword;
import com.deephermit.online_meeting.service.AliyunTokenService;
import com.deephermit.online_meeting.service.MeetingService;
import com.deephermit.online_meeting.service.UserService;
import com.deephermit.online_meeting.service.VerificationCodeService;
import com.deephermit.online_meeting.util.DateTimeUtil;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    @Autowired(required = false)
    private MeetingService meetingService;
    @Autowired(required = false)
    private AliyunTokenService aliyunTokenService;
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
            map.put("result",true);
        }
        return map;
    }
    @RequestMapping(value = "/register")
    public Map<String,Object> register(@RequestParam("account") String account,@RequestParam("password") String password,@RequestParam("phone") String phone,@RequestParam("email") String email,@RequestParam("verificationCode") String verificationCode,@RequestParam("suid") String suid){
        Map<String,Object> map=new HashMap<>();
        if(!verificationCodeService.isVerificationCodeRight(suid,verificationCode)){  //判断验证码对否
            map.put("msg","验证码错误！");
            map.put("result",false);
//            return map;
        }else if(userService.getAccountByName(account).size()!=0){//判断账户名称是否存在
            map.put("msg","注册失败，该用户名已经存在");
            map.put("result",false);
        }else{
            UserInfo userInfo = new UserInfo(userService.getIDOfUser(),account,email,phone);
            UserPassword userPassword = new UserPassword(userInfo.getUser_id(),password);
            userService.addUser(userInfo,userPassword);
            map.put("msg","注册成功！");
            map.put("result",true);
        }
        return map;
    }
    @RequestMapping(value = "/findPwdByPwd")
    public Map<String,Object> findPwdByPwd(@RequestParam("account") String account, @RequestParam("oldPassword") String oldPassword,@RequestParam("newPassword") String newPassword,@RequestParam("verificationCode") String verificationCode,@RequestParam("suid") String suid){
        Map<String,Object> map = new HashMap<>();
        if(!verificationCodeService.isVerificationCodeRight(suid,verificationCode)){
            map.put("msg","验证码错误");
            map.put("result",false);
            return map;
        }
        UserInfo userInfo = userService.getAccount(account,oldPassword);
        if(userInfo==null){
            map.put("msg","该用户不存在或者密码错误");
            map.put("result",false);
        }else{
            userService.updataPassword(userInfo.getUser_id(),newPassword);
            map.put("msg","密码修改成功");
            map.put("result",true);
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
    @RequestMapping(value = "/createMeeting")
    public Map<String,Object> createMeeting(@RequestParam("meetingName") String meetingName,@RequestParam("meetingPassword") String meetingPassword,@RequestParam("startTime") String startTime,@RequestParam("endTime") String endTime,@RequestParam("userId") String userId){
        Map<String,Object> map = new HashMap<>();
        meetingService.getMeetingId(meetingName,meetingPassword,startTime,endTime,userId);
        map.put("msg","预约会议成功！");
        map.put("result",true);
        return map;
    }
    @RequestMapping(value = "/getMeetingTokens")
    public Map<String,Object> getMeetingTokens(@RequestParam("meetingId") String meetingId,@RequestParam("userId") String userId){
        Map<String,Object> map = new HashMap<>();
        try {
            map = aliyunTokenService.getToken(meetingId,userId);
            map.put("msg","进入会议成功！");
            map.put("result",true);
            meetingService.addUser(meetingId,userId);
        } catch (Exception e) {
            map.put("msg","进入会议失败！");
            map.put("result",false);
            e.printStackTrace();
        }
        return map;
    }
    @RequestMapping(value = "/getMeetingPassword")
    public Map<String,Object> getMeetingPassword(@RequestParam("meetingId") String meetingId){
        Map<String,Object> map = new HashMap<>();
        MeetingPassword meetingPassword = meetingService.getMeeting(meetingId);
        if (meetingPassword==null){
            map.put("msg","会议不存在！");
            map.put("result",false);
        }else{
            map.put("meetingPassword",meetingPassword.getMeeting_password());
            map.put("msg","获取会议成功！");
            map.put("result",true);
        }
        return map;
    }
    @RequestMapping(value = "/quickMeeting")
    public Map<String,Object> quickMeeting(@RequestParam("meetingName") String meetingName,@RequestParam("meetingPassword") String meetingPassword,@RequestParam("startTime") String startTime,@RequestParam("endTime") String endTime,@RequestParam("userId") String userId){
        Map<String,Object> map = new HashMap<>();
        String meetingId = meetingService.getMeetingId(meetingName,meetingPassword, DateTimeUtil.getyMdHmDate(),endTime,userId);
        try {
            map = aliyunTokenService.getToken(meetingId,userId);
            map.put("msg","预约会议成功！");
            map.put("result",true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg","预约会议失败！");
            map.put("result",false);
        }
        return map;
    }
}
