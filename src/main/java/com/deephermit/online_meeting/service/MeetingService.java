package com.deephermit.online_meeting.service;

import com.deephermit.online_meeting.mapper.MeetingInfoMapper;
import com.deephermit.online_meeting.mapper.MeetingPasswordMapper;
import com.deephermit.online_meeting.mapper.UserMeetingRelMapper;
import com.deephermit.online_meeting.model.CountDown;
import com.deephermit.online_meeting.model.MeetingInfo;
import com.deephermit.online_meeting.model.MeetingPassword;
import com.deephermit.online_meeting.model.UserMeetingRel;
import com.deephermit.online_meeting.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MeetingService {
    @Autowired(required = false)
    private MeetingInfoMapper meetingInfoMapper;
    @Autowired(required = false)
    private MeetingPasswordMapper meetingPasswordMapper;
    @Autowired(required = false)
    private UserMeetingRelMapper userMeetingRelMapper;

    public String getMeetingId(String meetingName, String meetingPassword, String startTime, String endTime, String userId) {
        String meetingId = "";
        List<MeetingPassword> meetingPasswords;
        //创建数据库中不存在的MeetingId
        do{
            int number =(int) (Math.random()*1000000);
            meetingId = String.valueOf(number);
            meetingPasswords=meetingPasswordMapper.selectById(meetingId);
        }while (meetingPasswords.size()!=0);
        MeetingInfo meetingInfo = new MeetingInfo(meetingId,startTime,endTime,meetingName);
        meetingInfoMapper.insert(meetingInfo);
        MeetingPassword meetingPassword1 = new MeetingPassword(meetingId,meetingPassword);
        meetingPasswordMapper.insert(meetingPassword1);
        UserMeetingRel userMeetingRel = new UserMeetingRel(meetingId,userId,"1");
        userMeetingRelMapper.insert(userMeetingRel);
        return meetingId;
    }
    public MeetingPassword getMeeting(String meetingId){
        List<MeetingPassword> meetingPasswords = meetingPasswordMapper.selectById(meetingId);
        if (meetingPasswords.size()==0){
            return null;
        }else{
            return meetingPasswords.get(0);
        }
    }
    public Boolean addUser(String meetingId,String userId){
        List<UserMeetingRel> userMeetingRels = userMeetingRelMapper.selectByMeetingId(meetingId);
        if(userMeetingRels.size()==0){
            return false;
        }else{
            for(int i=0;i<userMeetingRels.size();i++){
                if(userMeetingRels.get(i).getUser_id().equals(userId)){
                    return false;
                }
            }
            UserMeetingRel userMeetingRel = new UserMeetingRel(meetingId,userId,"2");
            userMeetingRelMapper.insert(userMeetingRel);
            return true;
        }
    }
    public Map<String,Object> getAllOderMeetingAndRel(String userID){
        Map<String,Object> map = new HashMap<>();
        List<UserMeetingRel> userMeetingRelList = userMeetingRelMapper.selectByUserId(userID);
        List<MeetingInfo> meetingInfos = new ArrayList<MeetingInfo>();
        List<UserMeetingRel> userMeetingRels = new ArrayList<UserMeetingRel>();
        List<CountDown> countDowns = new ArrayList<CountDown>();
        for(int i=0;i<userMeetingRelList.size();i++){
            UserMeetingRel userMeetingRel = userMeetingRelList.get(i);
            MeetingInfo meetingInfo = meetingInfoMapper.selectById(userMeetingRel.getMeeting_id()).get(0);
            if(DateTimeUtil.TimeDifference(meetingInfo.getMeeting_id(),meetingInfo.getStart_time())==null){
                continue;
            }else{
               meetingInfos.add(meetingInfo);
               userMeetingRels.add(userMeetingRel);
               countDowns.add(DateTimeUtil.TimeDifference(meetingInfo.getMeeting_id(),meetingInfo.getStart_time()));
            }
        }
        map.put("meetingInfos",meetingInfos);
        map.put("meetingRels",userMeetingRels);
        map.put("countDowns",countDowns);
        return map;
    }
    public Map<String,Object> getAllMeetingAndRel(String userID){
        Map<String,Object> map = new HashMap<>();
        List<UserMeetingRel> userMeetingRelList = userMeetingRelMapper.selectByUserId(userID);
        if(userMeetingRelList.size()==0){
            return null;
        }
        List<MeetingInfo> meetingInfos = new ArrayList<MeetingInfo>();
        for(int i=0;i<userMeetingRelList.size();i++){
            MeetingInfo meetingInfo = meetingInfoMapper.selectById(userMeetingRelList.get(i).getMeeting_id()).get(0);
            meetingInfos.add(meetingInfo);
        }
        map.put("meetingInfos",meetingInfos);
        map.put("meetingRels",userMeetingRelList);
        return map;
    }

    public void generatorData(){
        //自定义用户1的3个会议，管理员的3个会议
        MeetingInfo meetingInfoA = new MeetingInfo("100000","2020-06-01 12:00","2020-06-01 18:00","项目开发探讨");
        MeetingInfo meetingInfoB = new MeetingInfo("100001","2020-06-02 12:00","2020-06-02 18:00","数据库加密");
        MeetingInfo meetingInfoC = new MeetingInfo("100002","2020-06-03 12:00","2020-06-03 18:00","操作系统");
        MeetingInfo meetingInfoD = new MeetingInfo("100003","2020-06-04 12:00","2020-06-04 18:00","人类发展史");
        MeetingInfo meetingInfoE = new MeetingInfo("100004","2020-06-05 12:00","2020-06-05 18:00","自然生态演变");
        MeetingInfo meetingInfoF = new MeetingInfo("100005","2020-06-06 12:00","2020-06-06 18:00","社会文明更迭");
        meetingInfoMapper.insert(meetingInfoA);
        meetingInfoMapper.insert(meetingInfoB);
        meetingInfoMapper.insert(meetingInfoC);
        meetingInfoMapper.insert(meetingInfoD);
        meetingInfoMapper.insert(meetingInfoE);
        meetingInfoMapper.insert(meetingInfoF);
        meetingPasswordMapper.insert(new MeetingPassword("100000","100000"));
        meetingPasswordMapper.insert(new MeetingPassword("100001",""));
        meetingPasswordMapper.insert(new MeetingPassword("100002",""));
        meetingPasswordMapper.insert(new MeetingPassword("100003",""));
        meetingPasswordMapper.insert(new MeetingPassword("100004",""));
        meetingPasswordMapper.insert(new MeetingPassword("100005",""));
        //用户1主持3个会议,管理员主持3个会议，加入3个会议
        userMeetingRelMapper.insert(new UserMeetingRel("100000","100000","1"));
        userMeetingRelMapper.insert(new UserMeetingRel("100001","100000","1"));
        userMeetingRelMapper.insert(new UserMeetingRel("100002","100000","1"));
        userMeetingRelMapper.insert(new UserMeetingRel("100003","100001","1"));
        userMeetingRelMapper.insert(new UserMeetingRel("100004","100001","1"));
        userMeetingRelMapper.insert(new UserMeetingRel("100005","100001","1"));
        userMeetingRelMapper.insert(new UserMeetingRel("100003","100000","2"));
        userMeetingRelMapper.insert(new UserMeetingRel("100004","100000","2"));
        userMeetingRelMapper.insert(new UserMeetingRel("100005","100000","2"));
        //90用户加入会议1
        for(int i=1;i<91;i++){
            userMeetingRelMapper.insert(new UserMeetingRel("100000",String.valueOf(i+100000),"2"));
        }
    }

    public String endMeeting(String meeting_id) {
        List<MeetingInfo> meetingInfos = meetingInfoMapper.selectById(meeting_id);
        if(meetingInfos.size()!=0&&(meetingInfos.get(0).getEnd_time()==null||meetingInfos.get(0).getEnd_time().trim().length()!=0)){
            String nowTime = DateTimeUtil.getyMdHmDate();
            meetingInfoMapper.updateByPrimaryKeySelective(new MeetingInfo(meeting_id,null,nowTime,null));
            return nowTime;
        }
        return null;
    }
}
