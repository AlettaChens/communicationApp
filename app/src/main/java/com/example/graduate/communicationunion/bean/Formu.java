package com.example.graduate.communicationunion.bean;

import android.content.Intent;

import java.io.Serializable;

public class Formu  implements Serializable{
    private Integer formuId;
    private String title;
    private String content;
    private String anthorName;
    private String anthorAvatar;
    private String publishDate;
    private Integer star;
    private Integer isCheck;

    public Integer getFormuId() {
        return formuId;
    }

    public void setFormuId(Integer formuId) {
        this.formuId = formuId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnthorName() {
        return anthorName;
    }

    public void setAnthorName(String anthorName) {
        this.anthorName = anthorName;
    }

    public String getAnthorAvatar() {
        return anthorAvatar;
    }

    public void setAnthorAvatar(String anthorAvatar) {
        this.anthorAvatar = anthorAvatar;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Integer getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Integer isCheck) {
        this.isCheck = isCheck;
    }
}
