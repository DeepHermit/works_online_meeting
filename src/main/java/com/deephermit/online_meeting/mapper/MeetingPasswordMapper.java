package com.deephermit.online_meeting.mapper;

import com.deephermit.online_meeting.model.MeetingPassword;
import com.deephermit.online_meeting.util.CommonMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MeetingPasswordMapper extends CommonMapper<MeetingPassword> {
    @Select("select * from meeting_password where meeting_id=#{meeting_id}")
    List<MeetingPassword> selectById(@Param("meeting_id") String meeting_id);
}