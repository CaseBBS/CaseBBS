package com.casebbs.model;

import java.util.Date;

public class ArticleAttchs {
    private Integer id;

    private Integer articleId;

    private String name;

    private String attchType;

    private String attchUrl;

    private Date uploadTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttchType() {
        return attchType;
    }

    public void setAttchType(String attchType) {
        this.attchType = attchType;
    }

    public String getAttchUrl() {
        return attchUrl;
    }

    public void setAttchUrl(String attchUrl) {
        this.attchUrl = attchUrl;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
}