package com.deephermit.online_meeting.service;

import com.deephermit.online_meeting.mapper.DeviceInfoMapper;
import com.deephermit.online_meeting.mapper.UserInfoMapper;
import com.deephermit.online_meeting.mapper.UserPasswordMapper;
import com.deephermit.online_meeting.model.DeviceInfo;
import com.deephermit.online_meeting.model.UserInfo;
import com.deephermit.online_meeting.model.UserPassword;
import com.deephermit.online_meeting.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class UserService {
    @Autowired(required = false)
    private UserInfoMapper userInfoMapper;
    @Autowired(required = false)
    private UserPasswordMapper userPasswordMapper;

    public UserInfo getAccount(String account, String password){
//        System.out.println(account);
        List<UserInfo> userInfos = userInfoMapper.selectByName(account);
        if(userInfos.size()!=0){
            String id = userInfos.get(0).getUser_id();
            List<UserPassword> userPasswords = userPasswordMapper.selectById(id);
            if(userPasswords.size()!=0&&userPasswords.get(0).getUser_password().equals(password)){
                return userInfos.get(0);
            }
        }
        return null;
    }
    public Boolean isLoginValid(String user_id,String login_code){

        return false;
    }
}
