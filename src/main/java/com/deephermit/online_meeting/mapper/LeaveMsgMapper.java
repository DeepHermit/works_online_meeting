package com.deephermit.online_meeting.mapper;

import com.deephermit.online_meeting.model.LeaveMsg;
import com.deephermit.online_meeting.util.CommonMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LeaveMsgMapper extends CommonMapper<LeaveMsg> {
    @Select("select * from leave_msg where meeting_id=#{meeting_id}")
    List<LeaveMsg> selectAllByMeetingId(@Param("meeting_id") String meeting_id);
}