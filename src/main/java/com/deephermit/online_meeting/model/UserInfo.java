package com.deephermit.online_meeting.model;

import javax.persistence.*;

@Table(name = "`user_info`")
public class UserInfo {
    /**
     * 百万用户
     */
    @Id
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

    @Column(name = "`phone`")
    private String phone;

    public UserInfo(String user_id, String user_name, String email, String phone) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.email = email;
        this.phone = phone;
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
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }
}