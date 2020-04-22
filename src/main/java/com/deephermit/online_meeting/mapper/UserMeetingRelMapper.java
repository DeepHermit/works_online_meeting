package com.deephermit.online_meeting.mapper;

import com.deephermit.online_meeting.model.UserMeetingRel;
import com.deephermit.online_meeting.util.CommonMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMeetingRelMapper extends CommonMapper<UserMeetingRel> {
    @Select("select * from user_meeting_rel where meeting_id=#{meeting_id}")
    List<UserMeetingRel> selectByMeetingId(@Param("meeting_id") String meeting_id);
}