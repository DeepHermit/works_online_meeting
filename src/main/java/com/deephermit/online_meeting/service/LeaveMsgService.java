package com.deephermit.online_meeting.service;

import com.deephermit.online_meeting.mapper.LeaveMsgMapper;
import com.deephermit.online_meeting.model.LeaveMsg;
import com.deephermit.online_meeting.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveMsgService {
    @Autowired(required = false)
    private LeaveMsgMapper leaveMsgMapper;

    public void sendMsg(String meeting_id,String user_id,String user_name,String msg){
        leaveMsgMapper.insert(new LeaveMsg(leaveMsgMapper.selectAll().size(),user_id,meeting_id,DateTimeUtil.getDate(),msg,user_name));
    }
    public void generatorData() {
        //增加十条留言
        int count=0;
        LeaveMsg leaveMsgA = new LeaveMsg(count,"100002","100000", DateTimeUtil.getDate(),"这次讨论使我获益匪浅！","appuser2");
        LeaveMsg leaveMsgB = new LeaveMsg(++count,"100009","100000", DateTimeUtil.getDate(),"对软件开发的灵感在大佬们的语言中澎湃而出","appuser9");
        LeaveMsg leaveMsgC = new LeaveMsg(++count,"100026","100000", DateTimeUtil.getDate(),"单独研究还是不如团队合作来得好啊","appuser26");
        LeaveMsg leaveMsgD = new LeaveMsg(++count,"100032","100000", DateTimeUtil.getDate(),"这个在线会议真是太棒了！","appuser32");
        LeaveMsg leaveMsgE = new LeaveMsg(++count,"100059","100000", DateTimeUtil.getDate(),"充实愉悦，大家都做得真棒！","appuser59");
        LeaveMsg leaveMsgF = new LeaveMsg(++count,"100066","100000", DateTimeUtil.getDate(),"期待下一次和大家一起探讨","appuser66");
        leaveMsgMapper.insert(leaveMsgA);
        leaveMsgMapper.insert(leaveMsgB);
        leaveMsgMapper.insert(leaveMsgC);
        leaveMsgMapper.insert(leaveMsgD);
        leaveMsgMapper.insert(leaveMsgE);
        leaveMsgMapper.insert(leaveMsgF);
    }

    public List<LeaveMsg> getAllLeaveMsgByMeetingId(String meeting_id) {
        List<LeaveMsg> leaveMsgs = leaveMsgMapper.selectAllByMeetingId(meeting_id);
        if(leaveMsgs.size()==0){
            return null;
        }else{
            return leaveMsgs;
        }
    }
}
