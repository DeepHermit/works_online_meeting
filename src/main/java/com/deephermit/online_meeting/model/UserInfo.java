package com.deephermit.online_meeting.model;

import javax.persistence.*;

@Table(name = "`user_info`")
public class UserInfo {
    /**
     * 百万用户
     */
    @Column(name = "`user_id`")
    @GeneratedValue(generator = "JDBC")
    private String userId;

    @Column(name = "`user_name`")
    private String userName;

    /**
     * 最长32位邮箱地址
     */
    @Column(name = "`email`")
    private String email;

    @Column(name = "`user_password`")
    private String userPassword;

    public UserInfo(String userId, String userName, String email, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.userPassword = userPassword;
    }

    public UserInfo() {
        super();
    }

    /**
     * 获取百万用户
     *
     * @return user_id - 百万用户
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置百万用户
     *
     * @param userId 百万用户
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * @return user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
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
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * @param userPassword
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }
}