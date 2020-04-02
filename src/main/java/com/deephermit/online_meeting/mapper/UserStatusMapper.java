package com.deephermit.online_meeting.mapper;

import com.deephermit.online_meeting.model.UserStatus;
import com.deephermit.online_meeting.util.CommonMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;

public interface UserStatusMapper extends CommonMapper<UserStatus> {
}