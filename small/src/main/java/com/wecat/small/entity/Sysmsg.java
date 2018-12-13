package com.wecat.small.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;



/**
 * <p>
 * 
 * </p>
 *
 * @author cjy
 * @since 2018-11-30
 */
public class Sysmsg implements Serializable {

    private static final long serialVersionUID = 1L;   

   private Integer id;
    /**
     * 消息id
     */
   private Integer mid;
    /**
     * 0表示群发消息
     */
   private Integer type;
    /**
     * 这条消息是给谁的
     */
   private Integer hrid;
    /**
     * 0 未读 1 已读
     */
   private Integer state;
   
   private String title;

   private String message;

   private Date createdate;
   
   


   public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}

public Date getCreatedate() {
	return createdate;
}

public void setCreatedate(Date createdate) {
	this.createdate = createdate;
}

public  Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public  Integer getMid() {
      return mid;
   }

   public void setMid(Integer mid) {
      this.mid = mid;
   }

   public  Integer getType() {
      return type;
   }

   public void setType(Integer type) {
      this.type = type;
   }

   public  Integer getHrid() {
      return hrid;
   }

   public void setHrid(Integer hrid) {
      this.hrid = hrid;
   }

   public  Integer getState() {
      return state;
   }

   public void setState(Integer state) {
      this.state = state;
   }

	@Override
	public String toString() {
		return "Sysmsg [id=" + id + ", mid=" + mid + ", type=" + type + ", hrid=" + hrid + ", state=" + state + ", title="
			+ title + ", message=" + message + ", createdate=" + createdate + "]";
	}


   
}