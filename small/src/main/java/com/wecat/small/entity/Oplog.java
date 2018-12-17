package com.wecat.small.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;



/**
 * <p>
 * 
 * </p>
 *
 * @author cjy
 * @since 2018-12-13
 */
public class Oplog implements Serializable {

    private static final long serialVersionUID = 1L;   

   private Integer id;
    /**
     * 开始时间
     */
   private Date createDate;
    /**
     * 操作内容
     */
   private String operate;
    /**
     * 操作员ID
     */
   private Integer hrid;
    /**
     * 日志类型
     */
   private String type;
    /**
     * 日志标题
     */
   private String title;
    /**
     * 请求地址
     */
   private String remoteAddr;
    /**
     * 路径
     */
   private String requestUri;
    /**
     * 请求方式
     */
   private String method;
    /**
     * 异常
     */
   private String exception;
    /**
     * 提交参数
     */
   private String params;
    /**
     * 结束时间
     */
   private Date endTime;
    /**
     * 用户id
     */
   private Integer userId;


   public  Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

  

   public  String getOperate() {
      return operate;
   }

   public void setOperate(String operate) {
      this.operate = operate;
   }

   public  Integer getHrid() {
      return hrid;
   }

   public void setHrid(Integer hrid) {
      this.hrid = hrid;
   }

   public  String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public  String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public  String getRemoteAddr() {
      return remoteAddr;
   }

   public void setRemoteAddr(String remoteAddr) {
      this.remoteAddr = remoteAddr;
   }

   public  String getRequestUri() {
      return requestUri;
   }

   public void setRequestUri(String requestUri) {
      this.requestUri = requestUri;
   }

   public  String getMethod() {
      return method;
   }

   public void setMethod(String method) {
      this.method = method;
   }

   public  String getException() {
      return exception;
   }

   public void setException(String exception) {
      this.exception = exception;
   }

   public  String getParams() {
      return params;
   }

   public void setParams(String params) {
      this.params = params;
   }

   

   public Date getCreateDate() {
	return createDate;
}

public void setCreateDate(Date createDate) {
	this.createDate = createDate;
}

public Date getEndTime() {
	return endTime;
}

public void setEndTime(Date endTime) {
	this.endTime = endTime;
}

public  Integer getUserId() {
      return userId;
   }

   public void setUserId(Integer userId) {
      this.userId = userId;
   }
   
   /**
    * 设置请求参数
    * @param paramMap
    */
   public void setMapToParams(Map<String, String[]> map) {
//	   Map map=request.getParameterMap();  
	    Set keSet=map.entrySet();  
	    for(Iterator itr=keSet.iterator();itr.hasNext();){  
	        Map.Entry me=(Map.Entry)itr.next();  
	        Object ok=me.getKey();  
	        Object ov=me.getValue();  
	        String[] value=new String[1];  
	        if(ov instanceof String[]){  
	            value=(String[])ov;  
	        }else{  
	            value[0]=ov.toString();  
	        }  
	  
	        for(int k=0;k<value.length;k++){  
	            System.out.println(ok+"="+value[k]);  
	        }  
	      }
   }


   @Override
   public String toString() {
      return "Oplog{" +
         "id=" + id +
         ", createDate=" + createDate +
         ", operate=" + operate +
         ", hrid=" + hrid +
         ", type=" + type +
         ", title=" + title +
         ", remoteAddr=" + remoteAddr +
         ", requestUri=" + requestUri +
         ", method=" + method +
         ", exception=" + exception +
         ", params=" + params +
         ", endTime=" + endTime +
         ", userId=" + userId +
         "}";
   }
}