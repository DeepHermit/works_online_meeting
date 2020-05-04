package com.deephermit.online_meeting.model;

import javax.persistence.*;

@Table(name = "`leave_msg`")
public class LeaveMsg {
    @Id
    @Column(name = "`leave_msg_id`")
    @GeneratedValue(generator = "JDBC")
    private Integer leave_msg_id;

    @Column(name = "`user_id`")
    private String user_id;

    @Column(name = "`meeting_id`")
    private String meeting_id;

    @Column(name = "`create_date`")
    private String create_date;

    @Column(name = "`msg`")
    private String msg;

    @Column(name = "`user_name`")
    private String user_name;

    public LeaveMsg(Integer leave_msg_id, String user_id, String meeting_id, String create_date, String msg, String user_name) {
        this.leave_msg_id = leave_msg_id;
        this.user_id = user_id;
        this.meeting_id = meeting_id;
        this.create_date = create_date;
        this.msg = msg;
        this.user_name = user_name;
    }

    public LeaveMsg() {
        super();
    }

    /**
     * @return leave_msg_id
     */
    public Integer getLeave_msg_id() {
        return leave_msg_id;
    }

    /**
     * @param leave_msg_id
     */
    public void setLeave_msg_id(Integer leave_msg_id) {
        this.leave_msg_id = leave_msg_id;
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
     * @return create_date
     */
    public String getCreate_date() {
        return create_date;
    }

    /**
     * @param create_date
     */
    public void setCreate_date(String create_date) {
        this.create_date = create_date == null ? null : create_date.trim();
    }

    /**
     * @return msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg
     */
    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    /**
     * @return user_name
     */
    public String getUser_name() {
        return user_name;
    }

    /**
     * @param user_name
     */
    public void setUser_name(String user_name) {
        this.user_name = user_name == null ? null : user_name.trim();
    }
}