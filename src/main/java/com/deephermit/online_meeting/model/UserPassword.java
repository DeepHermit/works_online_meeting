package com.deephermit.online_meeting.model;

import javax.persistence.*;

@Table(name = "`user_password`")
public class UserPassword {
    @Id
    @Column(name = "`user_id`")
    @GeneratedValue(generator = "JDBC")
    private String user_id;

    @Column(name = "`user_password`")
    private String user_password;

    public UserPassword(String user_id, String user_password) {
        this.user_id = user_id;
        this.user_password = user_password;
    }

    public UserPassword() {
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
     * @return user_password
     */
    public String getUser_password() {
        return user_password;
    }

    /**
     * @param user_password
     */
    public void setUser_password(String user_password) {
        this.user_password = user_password == null ? null : user_password.trim();
    }
}