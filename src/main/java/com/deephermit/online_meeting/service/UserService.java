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
    public List<UserInfo> getAccountByName(String account){
        List<UserInfo> userInfos = userInfoMapper.selectByName(account);
        return userInfos;
    }
    public String getIDOfUser(){
        List<UserPassword> userPasswords = userPasswordMapper.selectAll();
        return String.valueOf(userPasswords.size()+100000);
    }
    public void addUser(UserInfo userInfo,UserPassword userPassword){
        userInfoMapper.insert(userInfo);
        userPasswordMapper.insert(userPassword);
    }
    public void updataPassword(String user_id, String newPassword) {
        UserPassword userPassword = new UserPassword(user_id,newPassword);
        userPasswordMapper.updateByPrimaryKey(userPassword);
    }

    public Boolean userExist(String userId){
        List<UserInfo> userInfos = userInfoMapper.selectById(userId);
        if(userInfos.size()==0){
            return false;
        }else{
            return true;
        }
    }
    public void generatorData(){
        //90个用户1个管理员
        UserInfo rootAdmin = new UserInfo("100000","rootAdmin","343506776@qq.com","18215689962");
        UserPassword rootUserPassword = new UserPassword("100000","rootAdmin");
        userInfoMapper.insert(rootAdmin);
        userPasswordMapper.insert(rootUserPassword);
        for(int i=1;i<91;i++){
            UserInfo userInfo = new UserInfo(String.valueOf(100000+i),"appuser"+String.valueOf(i),null,null);
            UserPassword userPassword = new UserPassword(String.valueOf(100000+i),"appuser"+String.valueOf(i));
            userInfoMapper.insert(userInfo);
            userPasswordMapper.insert(userPassword);
        }
    }
}
