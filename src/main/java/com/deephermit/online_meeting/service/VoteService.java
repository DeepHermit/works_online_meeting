package com.deephermit.online_meeting.service;

import com.deephermit.online_meeting.mapper.UserVoteMeetingMapper;
import com.deephermit.online_meeting.mapper.VoteInfoMapper;
import com.deephermit.online_meeting.mapper.VoteMeetingRelMapper;
import com.deephermit.online_meeting.model.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VoteService {
    @Autowired(required = false)
    UserVoteMeetingMapper userVoteMeetingMapper;
    @Autowired(required = false)
    VoteInfoMapper voteInfoMapper;
    @Autowired(required = false)
    VoteMeetingRelMapper voteMeetingRelMapper;
    public List<VoteInfo> getAllVoteInfoByMeetingId(String meeting_id){
        List<VoteInfo> voteInfos = new ArrayList<VoteInfo>();
        List<VoteMeetingRel> voteMeetingRels = voteMeetingRelMapper.selectAllByMeetingId(meeting_id);
        if(voteMeetingRels.size()==0){
            return null;
        }else{
            for(int i=0;i<voteMeetingRels.size();i++){
                voteInfos.add(voteInfoMapper.selectByVoteId(voteMeetingRels.get(i).getVote_id()).get(0));
            }
        }
        return voteInfos;
    }

    public void generatorData(){
        //管理员拥有6个投票问题
//        VoteInfo voteInfo6 = new VoteInfo("0","100000","你喜欢开发吗？","非常喜欢","喜欢","一般","不喜欢");
//        VoteInfo voteInfo1 = new VoteInfo("1","100000","代码出现问题是改正还是重写？","改正","重写","不理会","视情况而定");
//        VoteInfo voteInfo2 = new VoteInfo("2","100000","需求出现变化是马上改变思路还是继续原计划最后修正？","改变思路","原计划继续","不理会新需求","视情况而定");
//        VoteInfo voteInfo3 = new VoteInfo("3","100000","遇到技术难题怎么办？","找外包","面向百度开发","请教大佬","跳过不做");
//        VoteInfo voteInfo4 = new VoteInfo("4","100000","接手他人代码第一反应是什么？","想自己重写不想改","立马开始研究","请教代码的原创者","视情况而定");
//        VoteInfo voteInfo5 = new VoteInfo("5","100000","无法按时完成任务怎么办？","说明情况，下次努力","熬夜赶工","借鉴他人代码","找人一起写");
//        voteInfoMapper.insert(voteInfo6);
//        voteInfoMapper.insert(voteInfo1);
//        voteInfoMapper.insert(voteInfo2);
//        voteInfoMapper.insert(voteInfo3);
//        voteInfoMapper.insert(voteInfo4);
//        voteInfoMapper.insert(voteInfo5);
        //管理员投票
        for(int i=0;i<3;i++){
            UserVoteMeeting userVoteMeeting = new UserVoteMeeting(String.valueOf(i),"100000","100000",String.valueOf(i),"A");
            userVoteMeetingMapper.insert(userVoteMeeting);
        }
        int recourdCount=2;
        //90用户投票1
        for(int i=1;i<22;i++){
            UserVoteMeeting userVoteMeeting = new UserVoteMeeting(String.valueOf(++recourdCount),"100000",String.valueOf(100000+i),"1","A");
            userVoteMeetingMapper.insert(userVoteMeeting);
        }
        for(int i=22;i<33;i++){
            UserVoteMeeting userVoteMeeting = new UserVoteMeeting(String.valueOf(++recourdCount),"100000",String.valueOf(100000+i),"1","B");
            userVoteMeetingMapper.insert(userVoteMeeting);
        }
        for(int i=33;i<56;i++){
            UserVoteMeeting userVoteMeeting = new UserVoteMeeting(String.valueOf(++recourdCount),"100000",String.valueOf(100000+i),"1","C");
            userVoteMeetingMapper.insert(userVoteMeeting);
        }
        for(int i=56;i<91;i++){
            UserVoteMeeting userVoteMeeting = new UserVoteMeeting(String.valueOf(++recourdCount),"100000",String.valueOf(100000+i),"1","D");
            userVoteMeetingMapper.insert(userVoteMeeting);
        }
        //90用户投票2
        for(int i=1;i<6;i++){
            UserVoteMeeting userVoteMeeting = new UserVoteMeeting(String.valueOf(++recourdCount),"100000",String.valueOf(100000+i),"2","A");
            userVoteMeetingMapper.insert(userVoteMeeting);
        }
        for(int i=6;i<31;i++){
            UserVoteMeeting userVoteMeeting = new UserVoteMeeting(String.valueOf(++recourdCount),"100000",String.valueOf(100000+i),"2","B");
            userVoteMeetingMapper.insert(userVoteMeeting);
        }
        for(int i=31;i<76;i++){
            UserVoteMeeting userVoteMeeting = new UserVoteMeeting(String.valueOf(++recourdCount),"100000",String.valueOf(100000+i),"2","C");
            userVoteMeetingMapper.insert(userVoteMeeting);
        }
        for(int i=76;i<91;i++){
            UserVoteMeeting userVoteMeeting = new UserVoteMeeting(String.valueOf(++recourdCount),"100000",String.valueOf(100000+i),"2","D");
            userVoteMeetingMapper.insert(userVoteMeeting);
        }
        //90用户投票0
        for(int i=1;i<26;i++){
            UserVoteMeeting userVoteMeeting = new UserVoteMeeting(String.valueOf(++recourdCount),"100000",String.valueOf(100000+i),"0","A");
            userVoteMeetingMapper.insert(userVoteMeeting);
        }
        for(int i=26;i<33;i++){
            UserVoteMeeting userVoteMeeting = new UserVoteMeeting(String.valueOf(++recourdCount),"100000",String.valueOf(100000+i),"0","B");
            userVoteMeetingMapper.insert(userVoteMeeting);
        }
        for(int i=33;i<67;i++){
            UserVoteMeeting userVoteMeeting = new UserVoteMeeting(String.valueOf(++recourdCount),"100000",String.valueOf(100000+i),"0","C");
            userVoteMeetingMapper.insert(userVoteMeeting);
        }
        for(int i=67;i<91;i++){
            UserVoteMeeting userVoteMeeting = new UserVoteMeeting(String.valueOf(++recourdCount),"100000",String.valueOf(100000+i),"0","D");
            userVoteMeetingMapper.insert(userVoteMeeting);
        }
        //将三个投票给会议1
//        voteMeetingRelMapper.insert(new VoteMeetingRel("0","0","100000"));
//        voteMeetingRelMapper.insert(new VoteMeetingRel("1","1","100000"));
//        voteMeetingRelMapper.insert(new VoteMeetingRel("2","2","100000"));
    }

    public List<Map<String, List<VoteNumber>>> getVoteData(String meeting_id) {
        List<Map<String, List<VoteNumber>>> voteData = new ArrayList<Map<String, List<VoteNumber>>>();
        List<VoteMeetingRel> voteMeetingRels = voteMeetingRelMapper.selectAllByMeetingId(meeting_id);
        for(int i=0;i<voteMeetingRels.size();i++){
            Map<String,List<VoteNumber>> aVoteData = new HashMap<>();
            List<VoteNumber> voteNumbers = new ArrayList<>();
            String vote_id = voteMeetingRels.get(i).getVote_id();
            List<UserVoteMeeting> userVoteMeetings = userVoteMeetingMapper.selectMeetingVotes(meeting_id,vote_id);
            int numberA=0;
            int numberB=0;
            int numberC=0;
            int numberD=0;
            for(int j=0;j<userVoteMeetings.size();j++){
                if(userVoteMeetings.get(j).getOption().equals("A")){
                    numberA++;
                }else if(userVoteMeetings.get(j).getOption().equals("B")){
                    numberB++;
                }else if(userVoteMeetings.get(j).getOption().equals("C")){
                    numberC++;
                }else{
                    numberD++;
                }
            }
            voteNumbers.add(new VoteNumber("A",numberA));
            voteNumbers.add(new VoteNumber("B",numberB));
            voteNumbers.add(new VoteNumber("C",numberC));
            voteNumbers.add(new VoteNumber("D",numberD));
            aVoteData.put("series",voteNumbers);
            voteData.add(aVoteData);
        }
        return voteData;
    }

    public List<VoteResult> getVoteResults(String meeting_id, String user_id) {
        List<VoteResult> voteResults = new ArrayList<>();
        List<VoteMeetingRel> voteMeetingRels = voteMeetingRelMapper.selectAllByMeetingId(meeting_id);
        for(int i=0;i<voteMeetingRels.size();i++){
            List<UserVoteMeeting> userVoteMeetings = userVoteMeetingMapper.selectMeetingVotes(meeting_id,voteMeetingRels.get(i).getVote_id());
            String option = null;
            for(int j=0;j<userVoteMeetings.size();j++){
                if(userVoteMeetings.get(j).getUser_id().equals(user_id)){
                    option = userVoteMeetings.get(j).getOption();
                    break;
                }
            }
            VoteResult voteResult = new VoteResult(voteMeetingRels.get(i).getVote_id(),option);
            voteResults.add(voteResult);
        }
        return voteResults;
    }

    public boolean senVotes(String meeting_id, String user_id, String voteResults) {
        try {
            JSONArray jsonArray = new JSONArray(voteResults);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                Boolean existResult = false;
                System.out.print(jsonArray.get(i));
                System.out.println(jsonArray.get(i));
                System.out.println(jsonObject.getString("option"));
                if(!jsonObject.getString("option").equals("A")&&!jsonObject.getString("option").equals("B")&&!jsonObject.getString("option").equals("C")&&!jsonObject.getString("option").equals("D")){
                    continue;
                }
                List<UserVoteMeeting> userVoteMeetings = userVoteMeetingMapper.selectMeetingVotes(meeting_id,jsonObject.getString("vote_id"));
                for(int j = 0;j<userVoteMeetings.size();j++){
                    if(userVoteMeetings.get(j).getUser_id().equals(user_id)){
                        existResult=true;
                        userVoteMeetingMapper.updateByPrimaryKey(new UserVoteMeeting(userVoteMeetings.get(j).getVote_record_id(),meeting_id,user_id,jsonObject.getString("vote_id"),jsonObject.getString("option")));
                        break;
                    }
                }
                if(!existResult){
                    List<UserVoteMeeting> userVoteMeetingList = userVoteMeetingMapper.selectAll();
                    int id=0;
                    int k;
                    do{
                        for(k=0;k<userVoteMeetingList.size();k++){
                            if(userVoteMeetingList.get(k).getVote_record_id().equals(String.valueOf(id))){
                                id++;
                                break;
                            }
                        }
                    }while (k<userVoteMeetingList.size());
                    userVoteMeetingMapper.insert(new UserVoteMeeting(String.valueOf(id),meeting_id,user_id,jsonObject.getString("vote_id"),jsonObject.getString("option")));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Map<String, Object> getMeetingWithAndWithout(String meeting_id, String user_id) {
        Map<String, Object> map = new HashMap<>();
        List<VoteInfo> with = new ArrayList<>();
        List<VoteInfo> without = new ArrayList<>();
        List<VoteInfo> voteInfos = voteInfoMapper.selectByUserId(user_id);
        List<VoteMeetingRel> voteMeetingRels = voteMeetingRelMapper.selectAllByMeetingId(meeting_id);
        for(int i=0;i<voteInfos.size();i++){
            int j =0;
            for(;j<voteMeetingRels.size();j++){
                if(voteMeetingRels.get(j).getVote_id().equals(voteInfos.get(i).getVote_id())){
                    with.add(voteInfos.get(i));
                    break;
                }
            }
            if(j==voteMeetingRels.size()){
                without.add(voteInfos.get(i));
            }
        }
        map.put("with",with);
        map.put("without",without);
        return map;
    }

    public boolean meetingDeleteVote(String meeting_id, String vote_id) {
        if(voteMeetingRelMapper.selectRel(meeting_id,vote_id).size()==0){
            return false;
        }else{
            voteMeetingRelMapper.delete(new VoteMeetingRel(null,vote_id,meeting_id));
            return true;
        }
    }

    public boolean meetingAddVote(String meeting_id, String vote_id) {
        if(voteMeetingRelMapper.selectRel(meeting_id,vote_id).size()==0){
            List<VoteMeetingRel> voteMeetingRels = voteMeetingRelMapper.selectAll();
            int id=0;
            int i;
            do{
                for(i=0;i<voteMeetingRels.size();i++){
                    if(voteMeetingRels.get(i).getVote_use_id().equals(String.valueOf(id))){
                        id++;
                        break;
                    }
                }
            }while (i<voteMeetingRels.size());
            voteMeetingRelMapper.insert(new VoteMeetingRel(String.valueOf(id),vote_id,meeting_id));
            return true;
        }else{
            return false;
        }
    }

    public List<VoteInfo> getAllVoteInfoByUserId(String user_id) {
        return voteInfoMapper.selectByUserId(user_id);
    }

    public boolean userAddVote(String user_id, String vote_question, String a, String b, String c, String d) {
        List<VoteInfo> voteInfos = voteInfoMapper.selectAll();
        int id=0;
        int i;
        do{
            for (i=0;i<voteInfos.size();i++){
                if(voteInfos.get(i).getVote_id().equals(String.valueOf(id))){
                    id++;
                    break;
                }
            }
        }while (i<voteInfos.size());
        voteInfoMapper.insert(new VoteInfo(String.valueOf(id),user_id,vote_question,a,b,c,d));
        return true;
    }

    public boolean userDeleteVote(String user_id, String vote_id) {
        List<VoteInfo> voteInfos = voteInfoMapper.selectByUserIdAndVoteId(user_id,vote_id);
        if(voteInfos.size()==0){
            return false;
        }else {
            voteInfoMapper.delete(voteInfos.get(0));
            List<VoteMeetingRel> voteMeetingRels = voteMeetingRelMapper.selectByVoteId(vote_id);
            for(int i=0;i<voteMeetingRels.size();i++){
                voteMeetingRelMapper.delete(voteMeetingRels.get(i));
            }
            List<UserVoteMeeting> userVoteMeetings = userVoteMeetingMapper.selectAllByVoteId(vote_id);
            for(int i=0;i<userVoteMeetings.size();i++){
                userVoteMeetingMapper.delete(userVoteMeetings.get(i));
            }
            return true;
        }
    }
}
