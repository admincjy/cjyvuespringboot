package com.wecat.small.entity;

import java.io.Serializable;
import java.util.List;

public class Department implements Serializable {
    private Integer id;

    private String name;

    private Integer parentId;

    private String deppath;

    private Boolean enabled;

    private Boolean isparent;
    
    private List<Department> children;
    
    private Integer result;

    private static final long serialVersionUID = 1L;
    
    
    
    
    public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public List<Department> getChildren() {
		return children;
	}

	public void setChildren(List<Department> children) {
		this.children = children;
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

    

    public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getDeppath() {
        return deppath;
    }

    public void setDeppath(String deppath) {
        this.deppath = deppath == null ? null : deppath.trim();
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getIsparent() {
        return isparent;
    }

    public void setIsparent(Boolean isparent) {
        this.isparent = isparent;
    }
}