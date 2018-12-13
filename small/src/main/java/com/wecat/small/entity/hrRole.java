package com.wecat.small.entity;

import java.io.Serializable;

public class hrRole implements Serializable {
    private Integer id;

    private Integer hrid;

    private Integer rid;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHrid() {
        return hrid;
    }

    public void setHrid(Integer hrid) {
        this.hrid = hrid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

	@Override
	public String toString() {
		return "hrRole [id=" + id + ", hrid=" + hrid + ", rid=" + rid + "]";
	}
    
}