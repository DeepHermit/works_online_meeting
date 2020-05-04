package com.deephermit.online_meeting.model;

import javax.persistence.*;

@Table(name = "`vote_meeting_rel`")
public class VoteMeetingRel {
    @Id
    @Column(name = "`vote_use_id`")
    @GeneratedValue(generator = "JDBC")
    private String vote_use_id;

    @Column(name = "`vote_id`")
    private String vote_id;

    @Column(name = "`meeting_id`")
    private String meeting_id;

    public VoteMeetingRel(String vote_use_id, String vote_id, String meeting_id) {
        this.vote_use_id = vote_use_id;
        this.vote_id = vote_id;
        this.meeting_id = meeting_id;
    }

    public VoteMeetingRel() {
        super();
    }

    /**
     * @return vote_use_id
     */
    public String getVote_use_id() {
        return vote_use_id;
    }

    /**
     * @param vote_use_id
     */
    public void setVote_use_id(String vote_use_id) {
        this.vote_use_id = vote_use_id == null ? null : vote_use_id.trim();
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
}