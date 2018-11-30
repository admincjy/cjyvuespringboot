package com.wecat.small.entity;


import java.time.LocalDateTime;
import java.io.Serializable;



/**
 * <p>
 * 
 * </p>
 *
 * @author cjy
 * @since 2018-11-30
 */
public class Msgcontent implements Serializable {

    private static final long serialVersionUID = 1L;   

   private Integer id;
   private String title;
   private String message;
   private LocalDateTime createDate;


   public  Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public  String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public  String getMessage() {
      return message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public  LocalDateTime getCreateDate() {
      return createDate;
   }

   public void setCreateDate(LocalDateTime createDate) {
      this.createDate = createDate;
   }


   @Override
   public String toString() {
      return "Msgcontent{" +
         "id=" + id +
         ", title=" + title +
         ", message=" + message +
         ", createDate=" + createDate +
         "}";
   }
}