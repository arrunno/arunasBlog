package com.arunas.blog.data;

public enum UserStatus {

   ADMIN(1),
   USER(2);

   private final int id;

   UserStatus(int id)
   {
       this.id = id;
   }

   public int getId(){
       return id;
   }

}
