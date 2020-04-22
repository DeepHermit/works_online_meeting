package com.deephermit.online_meeting.model;

import javax.persistence.*;

@Table(name = "`meeting_info`")
public class MeetingInfo {
    @Id
    @Column(name = "`meeting_id`")
    @GeneratedValue(generator = "JDBC")
    private String meeting_id;

    @Column(name = "`start_time`")
    private String start_time;

    @Column(name = "`end_time`")
    private String end_time;

    @Column(name = "`title`")
    private String title;

    public MeetingInfo(String meeting_id, String start_time, String end_time, String title) {
        this.meeting_id = meeting_id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.title = title;
    }

    public MeetingInfo() {
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
     * @return start_time
     */
    public String getStart_time() {
        return start_time;
    }

    /**
     * @param start_time
     */
    public void setStart_time(String start_time) {
        this.start_time = start_time == null ? null : start_time.trim();
    }

    /**
     * @return end_time
     */
    public String getEnd_time() {
        return end_time;
    }

    /**
     * @param end_time
     */
    public void setEnd_time(String end_time) {
        this.end_time = end_time == null ? null : end_time.trim();
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }
}