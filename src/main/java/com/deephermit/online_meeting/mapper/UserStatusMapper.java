package com.deephermit.online_meeting.mapper;

import com.deephermit.online_meeting.model.UserStatus;
import com.deephermit.online_meeting.util.CommonMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserStatusMapper extends CommonMapper<UserStatus> {
    @Select("select * from user_status where user_id=#{user_id}")
    List<UserStatus> selectByUserId(@Param("user_id") String user_id);
    @Select("select * from user_status where login_code=#{login_code}")
    List<UserStatus> selectByLoginCode(@Param("login_code") String login_code);
}