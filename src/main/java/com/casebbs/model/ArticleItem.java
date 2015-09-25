package com.casebbs.model;

import java.util.Date;

public class ArticleItem {
    private Integer id;

    private Integer articalId;

    private String contents;

    private Boolean isneeded;

    private Integer needUnitId;

    private String needUnitName;

    private Integer creator;

    private String creatorName;

    private Integer organId;
    
    private String organName;
    
    public Integer getOrganId() {
		return organId;
	}

	public void setOrganId(Integer organId) {
		this.organId = organId;
	}

	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticalId() {
        return articalId;
    }

    public void setArticalId(Integer articalId) {
        this.articalId = articalId;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Boolean getIsneeded() {
        return isneeded;
    }

    public void setIsneeded(Boolean isneeded) {
        this.isneeded = isneeded;
    }

    public Integer getNeedUnitId() {
        return needUnitId;
    }

    public void setNeedUnitId(Integer needUnitId) {
        this.needUnitId = needUnitId;
    }

    public String getNeedUnitName() {
        return needUnitName;
    }

    public void setNeedUnitName(String needUnitName) {
        this.needUnitName = needUnitName;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}