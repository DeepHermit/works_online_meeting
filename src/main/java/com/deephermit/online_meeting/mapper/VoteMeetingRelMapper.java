package com.deephermit.online_meeting.mapper;

import com.deephermit.online_meeting.model.VoteMeetingRel;
import com.deephermit.online_meeting.util.CommonMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VoteMeetingRelMapper extends CommonMapper<VoteMeetingRel> {
    @Select("select * from vote_meeting_rel where meeting_id=#{meeting_id}")
    List<VoteMeetingRel> selectAllByMeetingId(@Param("meeting_id") String meeting_id);
    @Select("select * from vote_meeting_rel where meeting_id=#{meeting_id} and vote_id=#{vote_id}")
    List<VoteMeetingRel> selectRel(@Param("meeting_id") String meeting_id,@Param("vote_id") String vote_id);
    @Select("select * from vote_meeting_rel where vote_id=#{vote_id}")
    List<VoteMeetingRel> selectByVoteId(@Param("vote_id") String vote_id);
}