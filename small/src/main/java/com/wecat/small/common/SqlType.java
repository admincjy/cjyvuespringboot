package com.wecat.small.common;



public enum SqlType {
	
	SELECT("查询"),
	UPDTAE("更新"),
	DELETE("删除"),
	INSERTE("新增"),
	OTHER("其他");
     
	private String name;

	private SqlType(String name) {
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
	
	
    
}
