package com.example.graduate.communicationunion.bean;


public class Comment {
    private Integer id;
    private Integer formuId;
    private String content;
    private String date;
    private String comment_user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFormuId() {
        return formuId;
    }

    public void setFormuId(Integer formuId) {
        this.formuId = formuId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment_user() {
        return comment_user;
    }

    public void setComment_user(String comment_user) {
        this.comment_user = comment_user;
    }
}
