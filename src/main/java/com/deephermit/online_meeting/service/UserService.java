package com.deephermit.online_meeting.service;

import com.deephermit.online_meeting.mapper.UserInfoMapper;
import com.deephermit.online_meeting.mapper.UserStatusMapper;
import com.deephermit.online_meeting.model.UserInfo;
import com.deephermit.online_meeting.model.UserStatus;
import com.deephermit.online_meeting.util.DateTimeUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Autowired(required = false)
    private UserInfoMapper userInfoMapper;
    @Autowired(required = false)
    private UserStatusMapper userStatusMapper;

    public UserInfo getAccount(String account, String password){
//        System.out.println(account);
        List<UserInfo> userInfos = userInfoMapper.selectByName(account);
        if(userInfos.isEmpty()){

        }else if(userInfos.get(0).getUser_password().equals(password)){
            return userInfos.get(0);
        }
        return null;
    }
    public String updateLoginCode(String userId){
        UserStatus userStatus;
        List<UserStatus> userStatuses;
        String login_code="";
//        创建数据库中不存在的login_code
        do{
            int number =(int) (Math.random()*1000000);
            login_code = String.valueOf(number);
            userStatuses=userStatusMapper.selectByLoginCode(login_code);
        }while (userStatuses.size()!=0);
//     user是否已有code
        userStatuses=userStatusMapper.selectByUserId(userId);
        if (userStatuses.size()!=0){
            userStatusMapper.delete(userStatuses.get(0));
        }
        UserStatus userStatus1 = new UserStatus(userId,login_code,DateTimeUtil.getDate());
        userStatusMapper.insert(userStatus1);
        return login_code;
    }
}
