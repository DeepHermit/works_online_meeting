package com.deephermit.online_meeting.model;

import javax.persistence.*;

@Table(name = "`vote_info`")
public class VoteInfo {
    @Id
    @Column(name = "`vote_id`")
    @GeneratedValue(generator = "JDBC")
    private String vote_id;

    @Column(name = "`user_id`")
    private String user_id;

    @Column(name = "`vote_question`")
    private String vote_question;

    @Column(name = "`A`")
    private String a;

    @Column(name = "`B`")
    private String b;

    @Column(name = "`C`")
    private String c;

    @Column(name = "`D`")
    private String d;

    public VoteInfo(String vote_id, String user_id, String vote_question, String a, String b, String c, String d) {
        this.vote_id = vote_id;
        this.user_id = user_id;
        this.vote_question = vote_question;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public VoteInfo() {
        super();
    }

    /**
     * @return vote_id
     */
    public String getVote_id() {
        return vote_id;
    }

    /**
     * @param vote_id
     */
    public void setVote_id(String vote_id) {
        this.vote_id = vote_id == null ? null : vote_id.trim();
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
     * @return vote_question
     */
    public String getVote_question() {
        return vote_question;
    }

    /**
     * @param vote_question
     */
    public void setVote_question(String vote_question) {
        this.vote_question = vote_question == null ? null : vote_question.trim();
    }

    /**
     * @return A
     */
    public String getA() {
        return a;
    }

    /**
     * @param a
     */
    public void setA(String a) {
        this.a = a == null ? null : a.trim();
    }

    /**
     * @return B
     */
    public String getB() {
        return b;
    }

    /**
     * @param b
     */
    public void setB(String b) {
        this.b = b == null ? null : b.trim();
    }

    /**
     * @return C
     */
    public String getC() {
        return c;
    }

    /**
     * @param c
     */
    public void setC(String c) {
        this.c = c == null ? null : c.trim();
    }

    /**
     * @return D
     */
    public String getD() {
        return d;
    }

    /**
     * @param d
     */
    public void setD(String d) {
        this.d = d == null ? null : d.trim();
    }
}