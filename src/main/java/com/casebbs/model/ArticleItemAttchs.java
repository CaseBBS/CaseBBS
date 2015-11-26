package com.casebbs.model;

import java.util.Date;

public class ArticleItemAttchs {
    private Integer id;

    private String name;

    private Integer articlaId;

    private Integer itemId;

    private String attchUrl;

    private String attchType;

    private Date createTime;

    private String creator;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getArticlaId() {
        return articlaId;
    }

    public void setArticlaId(Integer articlaId) {
        this.articlaId = articlaId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getAttchUrl() {
        return attchUrl;
    }

    public void setAttchUrl(String attchUrl) {
        this.attchUrl = attchUrl;
    }

    public String getAttchType() {
        return attchType;
    }

    public void setAttchType(String attchType) {
        this.attchType = attchType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
}