package com.deephermit.online_meeting.mapper;

import com.deephermit.online_meeting.model.VoteInfo;
import com.deephermit.online_meeting.util.CommonMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VoteInfoMapper extends CommonMapper<VoteInfo> {
    @Select("select * from vote_info where vote_id=#{vote_id}")
    List<VoteInfo> selectByVoteId(@Param("vote_id") String vote_id);
    @Select("select * from vote_info where user_id=#{user_id}")
    List<VoteInfo> selectByUserId(@Param("user_id") String user_id);
    @Select("select * from vote_info where user_id=#{user_id} and vote_id=#{vote_id}")
    List<VoteInfo> selectByUserIdAndVoteId(@Param("user_id") String user_id,@Param("vote_id") String vote_id);
}