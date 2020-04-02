package com.deephermit.online_meeting.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`user_status`")
public class UserStatus {
    @Column(name = "`user_id`")
    @GeneratedValue(generator = "JDBC")
    private String userId;

    @Column(name = "`login_code`")
    private String loginCode;

    @Column(name = "`create_date`")
    private Date createDate;

    public UserStatus(String userId, String loginCode, Date createDate) {
        this.userId = userId;
        this.loginCode = loginCode;
        this.createDate = createDate;
    }

    public UserStatus() {
        super();
    }

    /**
     * @return user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * @return login_code
     */
    public String getLoginCode() {
        return loginCode;
    }

    /**
     * @param loginCode
     */
    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode == null ? null : loginCode.trim();
    }

    /**
     * @return create_date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}