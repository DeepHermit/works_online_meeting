package com.deephermit.online_meeting.mapper;

import com.deephermit.online_meeting.model.UserPassword;
import com.deephermit.online_meeting.util.CommonMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserPasswordMapper extends CommonMapper<UserPassword> {
    @Select("select * from user_password where user_id=#{id}")
    List<UserPassword> selectById(@Param("id") String id);
}