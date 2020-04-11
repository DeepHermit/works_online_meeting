package com.deephermit.online_meeting.mapper;

import com.deephermit.online_meeting.model.DeviceInfo;
import com.deephermit.online_meeting.util.CommonMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeviceInfoMapper extends CommonMapper<DeviceInfo> {
    @Select("select * from device_info where suid=#{suid}")
    List<DeviceInfo> selectBySuid(@Param("suid") String suid);
}