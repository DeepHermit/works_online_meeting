package com.deephermit.online_meeting.model;

import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import javax.persistence.*;

@Data
@Table(name = "`user_info`")
public class UserInfo {
    /**
     * 百万用户
     */
    @Column(name = "`user_id`")
    @GeneratedValue(generator = "JDBC")
    private String user_id;

    @Column(name = "`user_name`")
    private String user_name;

    /**
     * 最长32位邮箱地址
     */
    @Column(name = "`email`")
    private String email;

    @Column(name = "`user_password`")
    private String user_password;

    public UserInfo(String user_id, String user_name, String email, String user_password) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.email = email;
        this.user_password = user_password;
    }

    public UserInfo() {
        super();
    }

    /**
     * 获取百万用户
     *
     * @return user_id - 百万用户
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * 设置百万用户
     *
     * @param user_id 百万用户
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id == null ? null : user_id.trim();
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

    /**
     * 获取最长32位邮箱地址
     *
     * @return email - 最长32位邮箱地址
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置最长32位邮箱地址
     *
     * @param email 最长32位邮箱地址
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
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