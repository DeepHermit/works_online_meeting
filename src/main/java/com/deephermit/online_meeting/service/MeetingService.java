package com.deephermit.online_meeting.service;

import com.deephermit.online_meeting.mapper.MeetingInfoMapper;
import com.deephermit.online_meeting.mapper.MeetingPasswordMapper;
import com.deephermit.online_meeting.mapper.UserMeetingRelMapper;
import com.deephermit.online_meeting.model.MeetingInfo;
import com.deephermit.online_meeting.model.MeetingPassword;
import com.deephermit.online_meeting.model.UserMeetingRel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
                if(userId==userMeetingRels.get(i).getUser_id()){
                    return false;
                }
            }
            UserMeetingRel userMeetingRel = new UserMeetingRel(meetingId,userId,"2");
            userMeetingRelMapper.insert(userMeetingRel);
            return true;
        }
    }
}
