package com.deephermit.online_meeting.model;

import javax.persistence.*;

@Table(name = "`device_info`")
public class DeviceInfo {
    @Column(name = "`user_id`")
    private String user_id;

    /**
     * 专用唯一性标识码
     */
    @Id
    @Column(name = "`suid`")
    @GeneratedValue(generator = "JDBC")
    private String suid;

    @Column(name = "`verification_code`")
    private String verification_code;

    @Column(name = "`suid_create_date`")
    private String suid_create_date;

    @Column(name = "`code_create_date`")
    private String code_create_date;

    public DeviceInfo(String user_id, String suid, String verification_code, String suid_create_date, String code_create_date) {
        this.user_id = user_id;
        this.suid = suid;
        this.verification_code = verification_code;
        this.suid_create_date = suid_create_date;
        this.code_create_date = code_create_date;
    }

    public DeviceInfo() {
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
     * 获取专用唯一性标识码
     *
     * @return suid - 专用唯一性标识码
     */
    public String getSuid() {
        return suid;
    }

    /**
     * 设置专用唯一性标识码
     *
     * @param suid 专用唯一性标识码
     */
    public void setSuid(String suid) {
        this.suid = suid == null ? null : suid.trim();
    }

    /**
     * @return verification_code
     */
    public String getVerification_code() {
        return verification_code;
    }

    /**
     * @param verification_code
     */
    public void setVerification_code(String verification_code) {
        this.verification_code = verification_code == null ? null : verification_code.trim();
    }

    /**
     * @return suid_create_date
     */
    public String getSuid_create_date() {
        return suid_create_date;
    }

    /**
     * @param suid_create_date
     */
    public void setSuid_create_date(String suid_create_date) {
        this.suid_create_date = suid_create_date == null ? null : suid_create_date.trim();
    }

    /**
     * @return code_create_date
     */
    public String getCode_create_date() {
        return code_create_date;
    }

    /**
     * @param code_create_date
     */
    public void setCode_create_date(String code_create_date) {
        this.code_create_date = code_create_date == null ? null : code_create_date.trim();
    }
}