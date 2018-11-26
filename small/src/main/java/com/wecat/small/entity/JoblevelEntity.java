package com.wecat.small.entity;

import java.io.Serializable;
import java.sql.Timestamp;


public class JoblevelEntity implements Serializable {
    private Integer id;

    private String name;

    private String titlelevel;
    
    private String titleLevel;
 
    private Timestamp createdate;
    
    private Timestamp createDate;

    private Boolean enabled;

    private static final long serialVersionUID = 1L;

    
    
    
    public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getTitleLevel() {
		return titleLevel;
	}

	public void setTitleLevel(String titleLevel) {
		this.titleLevel = titleLevel;
	}

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
        this.name = name == null ? null : name.trim();
    }

    public String getTitlelevel() {
        return titlelevel;
    }

    public void setTitlelevel(String titlelevel) {
        this.titlelevel = titlelevel == null ? null : titlelevel.trim();
    }

    

    public Timestamp getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}

	public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}