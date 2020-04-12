package com.deephermit.online_meeting.mapper;

import com.deephermit.online_meeting.model.UserInfo;
import com.deephermit.online_meeting.util.CommonMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserInfoMapper_now extends CommonMapper<UserInfo> {
    @Select("select * from user_info where user_id=#{id}")
    List<UserInfo> selectById(@Param("id") String id);
    @Select("select * from user_info where user_name=#{name}")
    List<UserInfo> selectByName(@Param("name") String name);
}
//@Select("select * from device_info where suid=#{suid}")
//List<DeviceInfo> selectBySuid(@Param("suid") String suid);

//    @Select("select * from user_info where user_id=#{id}")
//    List<UserInfo> selectById(@Param("id") String id);
//    @Select("select * from user_info where user_name=#{name}")
//    List<UserInfo> selectByName(@Param("name") String name);