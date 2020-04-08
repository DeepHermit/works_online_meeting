package com.deephermit.online_meeting.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "`user_status`")
public class UserStatus {
    @Column(name = "`user_id`")
    @GeneratedValue(generator = "JDBC")
    private String user_id;

    @Column(name = "`login_code`")
    private String login_code;

    @Column(name = "`create_date`")
    private String create_date;

    public UserStatus(String user_id, String login_code, String create_date) {
        this.user_id = user_id;
        this.login_code = login_code;
        this.create_date = create_date;
    }

    public UserStatus() {
        super();
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
     * @return login_code
     */
    public String getLogin_code() {
        return login_code;
    }

    /**
     * @param login_code
     */
    public void setLogin_code(String login_code) {
        this.login_code = login_code == null ? null : login_code.trim();
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
}