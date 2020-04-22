package com.deephermit.online_meeting.model;

import javax.persistence.*;

@Table(name = "`user_meeting_rel`")
public class UserMeetingRel {
    @Id
    @Column(name = "`meeting_id`")
    @GeneratedValue(generator = "JDBC")
    private String meeting_id;

    @Column(name = "`user_id`")
    private String user_id;

    @Column(name = "`role_id`")
    private String role_id;

    public UserMeetingRel(String meeting_id, String user_id, String role_id) {
        this.meeting_id = meeting_id;
        this.user_id = user_id;
        this.role_id = role_id;
    }

    public UserMeetingRel() {
        super();
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
     * @return role_id
     */
    public String getRole_id() {
        return role_id;
    }

    /**
     * @param role_id
     */
    public void setRole_id(String role_id) {
        this.role_id = role_id == null ? null : role_id.trim();
    }
}