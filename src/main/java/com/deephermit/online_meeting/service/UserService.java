package com.deephermit.online_meeting.service;

import com.deephermit.online_meeting.mapper.DeviceInfoMapper;
import com.deephermit.online_meeting.mapper.UserInfoMapper;
import com.deephermit.online_meeting.mapper.UserStatusMapper;
import com.deephermit.online_meeting.model.DeviceInfo;
import com.deephermit.online_meeting.model.UserInfo;
import com.deephermit.online_meeting.model.UserStatus;
import com.deephermit.online_meeting.util.DateTimeUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Service
public class UserService {
    @Autowired(required = false)
    private UserInfoMapper userInfoMapper;
    @Autowired(required = false)
    private UserStatusMapper userStatusMapper;
    @Autowired(required = false)
    private DeviceInfoMapper deviceInfoMapper;

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
    public String addAndGetSuid(){
        String suid="";
        List<DeviceInfo> deviceInfos;
        //创建数据库中不存在的suid
        do{
            int number =(int) (Math.random()*1000000);
            suid = String.valueOf(number);
            deviceInfos=deviceInfoMapper.selectBySuid(suid);
        }while (deviceInfos.size()!=0);
        DeviceInfo deviceInfo = new DeviceInfo(suid,null,DateTimeUtil.getDate(),null);
        deviceInfoMapper.insert(deviceInfo);
        return suid;
    }
    public Boolean isSuidValid(String suid){
        List<DeviceInfo> deviceInfos = deviceInfoMapper.selectBySuid(suid);
        if(deviceInfos.size()==0){
            return false;
        }
        return true;
    }
    public String getVerificationCode(String suid){
        String code="";
        Random random = new Random();
        int n=4;//四位验证码
        for ( int i = 0; i < n; i++ )
        {
            String str = random.nextInt( 2 ) % 2 == 0 ? "num" : "char";
            if ( "char".equalsIgnoreCase( str ) )
            {
//                确定大小写
                int nextInt = random.nextInt( 2 ) % 2 == 0 ? 65 : 97;
                code += (char) ( nextInt + random.nextInt( 26 ) );
            }
            else if ( "num".equalsIgnoreCase( str ) )
            { // 产生数字
                code += String.valueOf( random.nextInt( 10 ) );
            }
        }
        DeviceInfo deviceInfo = new DeviceInfo(suid,code,null,DateTimeUtil.getDate());
        System.out.println(deviceInfo.toString());
        deviceInfoMapper.updateByPrimaryKeySelective(deviceInfo);
        return code;
    }
    public Boolean isLoginValid(String user_id,String login_code){
        List<UserStatus> userStatuses = userStatusMapper.selectByUserId(user_id);
        if(userStatuses.size()!=0&&userStatuses.get(0).getLogin_code().equals(login_code)){
            return true;
        }
        return false;
    }
}
