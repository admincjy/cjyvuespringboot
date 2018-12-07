package com.wecat.small.entity;


import java.io.Serializable;



/**
 * <p>
 * 
 * </p>
 *
 * @author cjy
 * @since 2018-12-06
 */
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;   

   private Integer id;
   private String name;
    /**
     * 角色名称
     */
   private String nameZh;


   public  Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public  String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public  String getNameZh() {
      return nameZh;
   }

   public void setNameZh(String nameZh) {
      this.nameZh = nameZh;
   }


   @Override
   public String toString() {
      return "Role{" +
         "id=" + id +
         ", name=" + name +
         ", nameZh=" + nameZh +
         "}";
   }
}