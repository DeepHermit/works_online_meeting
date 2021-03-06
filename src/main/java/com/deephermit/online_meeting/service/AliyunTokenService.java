package com.deephermit.online_meeting.service;

import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AliyunTokenService {
    public static String createToken(
            String appId, String appKey, String channelId, String userId,
            String nonce, Long timestamp
    ) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(appId.getBytes());
        digest.update(appKey.getBytes());
        digest.update(channelId.getBytes());
        digest.update(userId.getBytes());
        digest.update(nonce.getBytes());
        digest.update(Long.toString(timestamp).getBytes());

        String token = DatatypeConverter.printHexBinary(digest.digest()).toLowerCase();
        return token;
    }
    public static String createUserID(String channelID, String user) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(channelID.getBytes());
        digest.update("/".getBytes());
        digest.update(user.getBytes());

        String uid = DatatypeConverter.printHexBinary(digest.digest()).toLowerCase();
        return uid.substring(0, 16);
    }
    public Map<String,Object> getToken(String meetingId,String UserId) throws Exception {
        Map<String,Object> map = new HashMap<>();
        String appID="6yno5hcr";
        String appKey="0ebab26baa78f05ec21b94c2caf500ea";
        String gslb="https://rgslb.rtc.aliyuncs.com";
        // Generated by AppServer.
        String nonce;
        Long timestamp;
        String userID;
        String token;
        //本身
        String channelID=meetingId;
        String user=UserId;
        //开始
        userID = createUserID(channelID, user);

        // Warning: nonce support the AppKey generated token.
        // the Nonce should be prefix with 'AK-' otherwise the joining verification will failed.
        // eg. nonce: "AK-0464002093ce3dd010cb05356c8b1d0f".
        nonce = String.format("AK-%s", UUID.randomUUID().toString());

        // Warning: timestamp is the token expiration time.
        // User can custom defined the expire time of token.
        // eg, Expires in two days. timestamp: 1559890860.
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.HOUR_OF_DAY, 48);
        timestamp = nowTime.getTimeInMillis() / 1000;

        token = createToken(appID, appKey, channelID, userID, nonce, timestamp);
        map.put("nonce",nonce);
        map.put("token",token);
        map.put("timestamp",timestamp.toString());
        map.put("meetingId",channelID);
        map.put("userID",userID);
        String username = String.format("%s?appid=%s&channel=%s&nonce=%s&timestamp=%d",
                userID, appID, channelID, nonce, timestamp);

        System.out.printf("Login: appID=%s, appKey=%s, channelID=%s, userID=%s, nonce=%s, "+
                        "timestamp=%d, user=%s, userName=%s, token=%s\n",
                appID, appKey, channelID, userID, nonce, timestamp, user, username, token);
        return map;
    }
}
