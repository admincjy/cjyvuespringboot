package com.wecat.small.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class EmailEntity implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -7336673224191962232L;
	private	String subject;
    private	String[] toUsers;
    private	String[] ccUsers; 
    private	String content;
    private	List<Map<String, String>> attachfiles;
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String[] getToUsers() {
		return toUsers;
	}
	public void setToUsers(String[] toUsers) {
		this.toUsers = toUsers;
	}
	public String[] getCcUsers() {
		return ccUsers;
	}
	public void setCcUsers(String[] ccUsers) {
		this.ccUsers = ccUsers;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<Map<String, String>> getAttachfiles() {
		return attachfiles;
	}
	public void setAttachfiles(List<Map<String, String>> attachfiles) {
		this.attachfiles = attachfiles;
	}
	@Override
	public String toString() {
		return "EmailEntity [subject=" + subject + ", toUsers=" + Arrays.toString(toUsers) + ", ccUsers="
				+ Arrays.toString(ccUsers) + ", content=" + content + ", attachfiles=" + attachfiles + "]";
	}
    
    

}
