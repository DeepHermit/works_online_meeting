package com.deephermit.online_meeting.controller;

import com.deephermit.online_meeting.model.*;
import com.deephermit.online_meeting.service.*;
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
    @Autowired(required = false)
    private LeaveMsgService leaveMsgService;
    @Autowired(required = false)
    private VoteService voteService;
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
    public Map<String,Object> loginIsValid(@RequestParam("user_id") String user_id,@RequestParam("suid") String suid){
        Map<String,Object> map=new HashMap<>();
        if(verificationCodeService.isLoginValid(user_id,suid)){
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
    @RequestMapping(value = "/endMeeting")
    public Map<String,Object> endMeeting(@RequestParam("meeting_id") String meeting_id){
        Map<String,Object> map = new HashMap<>();
        if(meetingService.endMeeting(meeting_id)){
            map.put("msg","结束会议成功！");
            map.put("result",true);
        }else{
            map.put("msg","结束会议失败！");
            map.put("result",false);
        }
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
    @RequestMapping(value = "/getAllOrederMeeting")
    public Map<String,Object> getAllOrederMeeting(@RequestParam("userId") String userId){
        Map<String,Object> map = new HashMap<>();
        if(userService.userExist(userId)){
            map = meetingService.getAllOderMeetingAndRel(userId);
            map.put("msg","查询预约会议成功");
            map.put("result",true);
        }else {
            map.put("msg","该用户不存在");
            map.put("result",false);
        }
        return map;
    }
    @RequestMapping(value = "/getAllMeeting")
    public Map<String,Object> getAllMeeting(@RequestParam("user_id") String user_id){
        Map<String,Object> map = new HashMap<>();
        if(meetingService.getAllMeetingAndRel(user_id)==null){
            map.put("msg","该用户未参与任何一场会议");
            map.put("result",false);
        }else{
            map = meetingService.getAllMeetingAndRel(user_id);
            map.put("msg","查询成功！");
            map.put("result",true);
        }
        return map;
    }
//    getVoteAndLeaveMsg
    @RequestMapping(value = "/getVoteAndLeaveMsg")
    public Map<String,Object> getVoteAndLeaveMsg(@RequestParam("meeting_id") String meeting_id){
        Map<String,Object> map = new HashMap<>();
        List<VoteInfo> voteInfos = voteService.getAllVoteInfoByMeetingId(meeting_id);
        map.put("allVoteInfo",voteInfos);
        List<LeaveMsg> leaveMsgs = leaveMsgService.getAllLeaveMsgByMeetingId(meeting_id);
        map.put("allLeaveMsg",leaveMsgs);
        map.put("msg","查询成功");
        map.put("result",true);
        return map;
    }
    @RequestMapping(value = "/senLeaveMsg")
    public Map<String,Object> senLeaveMsg(@RequestParam("meeting_id") String meeting_id,@RequestParam("user_id") String user_id,@RequestParam("user_name") String user_name,@RequestParam("msg") String msg){
        Map<String,Object> map = new HashMap<>();
        leaveMsgService.sendMsg(meeting_id,user_id,user_name,msg);
        map.put("msg","成功!");
        map.put("result",true);
        return map;
    }
    @RequestMapping(value = "/getVoteData")
    public Map<String,Object> getVoteData(@RequestParam("meeting_id") String meeting_id){
        Map<String,Object> map = new HashMap<>();
        List<Map<String,List<VoteNumber>>> voteData = voteService.getVoteData(meeting_id);
        if(voteData==null){
            map.put("msg","获取数据失败!");
            map.put("result",false);
        }else {
            map.put("voteData",voteData);
            map.put("msg","获取数据成功!");
            map.put("result",true);
        }
        return map;
    }
    @RequestMapping(value = "/getVoteResults")
    public Map<String,Object> getVoteResults(@RequestParam("meeting_id") String meeting_id,@RequestParam("user_id") String user_id){
        Map<String,Object> map = new HashMap<>();
        List<VoteResult> voteResults = voteService.getVoteResults(meeting_id,user_id);
        map.put("voteResults",voteResults);
        map.put("msg","获取投票结果成功!");
        map.put("result",true);
        return map;
    }
    @RequestMapping(value = "/sendVotes")
    public Map<String,Object> sendVotes(@RequestParam("meeting_id") String meeting_id,@RequestParam("user_id") String user_id,@RequestParam("voteResults") String voteResults){
        Map<String,Object> map = new HashMap<>();
        System.out.print(voteResults);
        if(voteService.senVotes(meeting_id,user_id,voteResults)){
            map.put("msg","投票提交成功!");
            map.put("result",true);
        }else{
            map.put("msg","投票提交失败!");
            map.put("result",true);
        }
        return map;
    }
    @RequestMapping(value = "/getMeetingWithAndWithout")
    public Map<String,Object> getMeetingWithAndWithout(@RequestParam("meeting_id") String meeting_id,@RequestParam("user_id") String user_id){
        Map<String,Object> map = new HashMap<>();
        map = voteService.getMeetingWithAndWithout(meeting_id,user_id);
        map.put("msg","成功!");
        map.put("result",true);
        return map;
    }
    @RequestMapping(value = "/meetingDeleteVote")
    public Map<String,Object> meetingDeleteVote(@RequestParam("meeting_id") String meeting_id,@RequestParam("vote_id") String vote_id){
        Map<String,Object> map = new HashMap<>();
        if(voteService.meetingDeleteVote(meeting_id,vote_id)){
            map.put("msg","删除成功!");
            map.put("result",true);
        }else{
            map.put("msg","删除失败!");
            map.put("result",false);
        }
        return map;
    }
    @RequestMapping(value = "/meetingAddVote")
    public Map<String,Object> meetingAddVote(@RequestParam("meeting_id") String meeting_id,@RequestParam("vote_id") String vote_id){
        Map<String,Object> map = new HashMap<>();
        if(voteService.meetingAddVote(meeting_id,vote_id)){
            map.put("msg","添加成功!");
            map.put("result",true);
        }else{
            map.put("msg","添加失败!");
            map.put("result",false);
        }
        return map;
    }
    @RequestMapping(value = "/getAllVotes")
    public Map<String,Object> getAllVotes(@RequestParam("user_id") String user_id){
        Map<String,Object> map = new HashMap<>();
        List<VoteInfo> voteInfos = voteService.getAllVoteInfoByUserId(user_id);
        map.put("voteInfos",voteInfos);
        map.put("msg","成功!");
        map.put("result",true);
        return map;
    }
    @RequestMapping(value = "/addVote")
    public Map<String,Object> addVote(@RequestParam("user_id") String user_id,@RequestParam("vote_question") String vote_question,@RequestParam("A") String A,@RequestParam("B") String B,@RequestParam("C") String C,@RequestParam("D") String D){
        Map<String,Object> map = new HashMap<>();
        if(voteService.userAddVote(user_id,vote_question,A,B,C,D)){
            map.put("msg","添加投票成功!");
            map.put("result",true);
        }else {
            map.put("msg","添加投票失败!");
            map.put("result",false);
        }
        return map;
    }
    @RequestMapping(value = "/deleteVote")
    public Map<String,Object> deleteVote(@RequestParam("user_id") String user_id,@RequestParam("vote_id") String vote_id){
        Map<String,Object> map = new HashMap<>();
        if(voteService.userDeleteVote(user_id,vote_id)){
            map.put("msg","删除投票成功!");
            map.put("result",true);
        }else {
            map.put("msg","删除投票失败!");
            map.put("result",false);
        }
        return map;
    }
//    @RequestMapping(value = "/getAllMeeting")
//    public Map<String,Object> getAllMeeting(){
//        Map<String,Object> map = new HashMap<>();
//        map.put("msg","成功!");
//        map.put("result",true);
//        return map;
//    }
    @RequestMapping(value = "/generatorData")
    public Map<String,Object> generatorData(@RequestParam("jsonData") String jsonData){
        Map<String,Object> map = new HashMap<>();
        //90个用户1个管理员
    //        userService.generatorData();
        //自定义用户1的3个会议，管理员的3个会议，管理员参加用户1的三个会议
        //90用户加入会议1
    //        meetingService.generatorData();
        //管理员拥有6个投票问题，会议1中90用户参与三个投票
//            voteService.generatorData();
        //加入十条留言
//        leaveMsgService.generatorData();
        return map;
    }
}
