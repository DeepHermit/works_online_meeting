package com.deephermit.online_meeting.model;

import javax.persistence.*;

@Table(name = "`user_vote_meeting`")
public class UserVoteMeeting {
    @Id
    @Column(name = "`vote_record_id`")
    @GeneratedValue(generator = "JDBC")
    private String vote_record_id;

    @Column(name = "`meeting_id`")
    private String meeting_id;

    @Column(name = "`user_id`")
    private String user_id;

    @Column(name = "`vote_id`")
    private String vote_id;

    @Column(name = "`option`")
    private String option;

    public UserVoteMeeting(String vote_record_id, String meeting_id, String user_id, String vote_id, String option) {
        this.vote_record_id = vote_record_id;
        this.meeting_id = meeting_id;
        this.user_id = user_id;
        this.vote_id = vote_id;
        this.option = option;
    }

    public UserVoteMeeting() {
        super();
    }

    /**
     * @return vote_record_id
     */
    public String getVote_record_id() {
        return vote_record_id;
    }

    /**
     * @param vote_record_id
     */
    public void setVote_record_id(String vote_record_id) {
        this.vote_record_id = vote_record_id == null ? null : vote_record_id.trim();
    }

    /**
     * @return meeting_id
     */
    public String getMeeting_id() {
        return meeting_id;
    }

    /**
     * @param meeting_id
     */
    public void setMeeting_id(String meeting_id) {
        this.meeting_id = meeting_id == null ? null : meeting_id.trim();
    }

    /**
     * @return user_id
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * @param user_id
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id == null ? null : user_id.trim();
    }

    /**
     * @return vote_id
     */
    public String getVote_id() {
        return vote_id;
    }

    /**
     * @param vote_id
     */
    public void setVote_id(String vote_id) {
        this.vote_id = vote_id == null ? null : vote_id.trim();
    }

    /**
     * @return option
     */
    public String getOption() {
        return option;
    }

    /**
     * @param option
     */
    public void setOption(String option) {
        this.option = option == null ? null : option.trim();
    }
}