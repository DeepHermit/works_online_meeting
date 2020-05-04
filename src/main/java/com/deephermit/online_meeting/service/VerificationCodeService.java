package com.deephermit.online_meeting.service;

import com.deephermit.online_meeting.mapper.DeviceInfoMapper;
import com.deephermit.online_meeting.model.DeviceInfo;
import com.deephermit.online_meeting.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class VerificationCodeService {
    @Autowired(required = false)
    private DeviceInfoMapper deviceInfoMapper;
    public String addAndGetSuid(){
        String suid="";
        List<DeviceInfo> deviceInfos;
        //创建数据库中不存在的suid
        do{
            int number =(int) (Math.random()*1000000);
            suid = String.valueOf(number);
            deviceInfos=deviceInfoMapper.selectBySuid(suid);
        }while (deviceInfos.size()!=0);
        DeviceInfo deviceInfo = new DeviceInfo(null,suid,null,DateTimeUtil.getDate(),null);
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
        DeviceInfo deviceInfo = new DeviceInfo(null,suid,code,null,DateTimeUtil.getDate());
        deviceInfoMapper.updateByPrimaryKeySelective(deviceInfo);
        return code;
    }
    public Boolean isVerificationCodeRight(String suid,String verificationCode){
        List<DeviceInfo> deviceInfos = deviceInfoMapper.selectBySuid(suid);
        if (deviceInfos.size()!=0&&deviceInfos.get(0).getVerification_code().equals(verificationCode)){
            return true;
        }
        return false;
    }

    public Boolean updateDevUser(String suid, String user_id) {
        List<DeviceInfo> deviceInfos = deviceInfoMapper.selectByUserId(user_id);
        if(deviceInfos!=null&&deviceInfos.size()!=0){
            for(int i=0;i<deviceInfos.size();i++){
                deviceInfoMapper.delete(deviceInfos.get(i));
            }
        }
        DeviceInfo deviceInfo = new DeviceInfo(user_id,suid,null,null,null);
        deviceInfoMapper.updateByPrimaryKeySelective(deviceInfo);
        return true;
    }
    public Boolean isLoginValid(String user_id,String suid){
        List<DeviceInfo> deviceInfos = deviceInfoMapper.selectByUserId(user_id);
        if(deviceInfos==null||deviceInfos.size()==0){
            return false;
        }else{
            if(deviceInfos.get(0).getSuid().equals(suid)){
                return true;
            }
        }
        return false;
    }
}
