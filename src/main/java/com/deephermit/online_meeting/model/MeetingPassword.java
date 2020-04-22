package com.deephermit.online_meeting.model;

import javax.persistence.*;

@Table(name = "`meeting_password`")
public class MeetingPassword {
    @Id
    @Column(name = "`meeting_id`")
    @GeneratedValue(generator = "JDBC")
    private String meeting_id;

    @Column(name = "`meeting_password`")
    private String meeting_password;

    public MeetingPassword(String meeting_id, String meeting_password) {
        this.meeting_id = meeting_id;
        this.meeting_password = meeting_password;
    }

    public MeetingPassword() {
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
     * @return meeting_password
     */
    public String getMeeting_password() {
        return meeting_password;
    }

    /**
     * @param meeting_password
     */
    public void setMeeting_password(String meeting_password) {
        this.meeting_password = meeting_password == null ? null : meeting_password.trim();
    }
}