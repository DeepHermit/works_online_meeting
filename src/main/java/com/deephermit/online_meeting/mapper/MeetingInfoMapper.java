package com.deephermit.online_meeting.mapper;

import com.deephermit.online_meeting.model.MeetingInfo;
import com.deephermit.online_meeting.util.CommonMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MeetingInfoMapper extends CommonMapper<MeetingInfo> {
    @Select("select * from meeting_info where meeting_id=#{meeting_id}")
    List<MeetingInfo> selectById(@Param("meeting_id") String meeting_id);
}