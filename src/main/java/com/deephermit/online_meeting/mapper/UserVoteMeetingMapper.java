package com.deephermit.online_meeting.mapper;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.deephermit.online_meeting.model.UserVoteMeeting;
import com.deephermit.online_meeting.util.CommonMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserVoteMeetingMapper extends CommonMapper<UserVoteMeeting> {
    @Select("select * from user_vote_meeting where meeting_id=#{meeting_id}")
    List<UserVoteMeeting>selectAllByMeetingId(@Param("meeting_id") String meeting_id);
    @Select("select * from user_vote_meeting where meeting_id=#{meeting_id} and vote_id=#{vote_id}")
    List<UserVoteMeeting>selectMeetingVotes(@Param("meeting_id") String meeting_id,@Param("vote_id") String vote_id);
    @Select("select * from user_vote_meeting where meeting_id=#{meeting_id} and user_id=#{user_id}")
    List<UserVoteMeeting>selectByMeetingIdAndUserId(@Param("meeting_id") String meeting_id,@Param("user_id") String user_id);
    @Select("select * from user_vote_meeting where vote_id=#{vote_id}")
    List<UserVoteMeeting>selectAllByVoteId(@Param("vote_id") String vote_id);
}