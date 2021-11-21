package com.arunas.blog.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
    @Id
    String email;
    String Name;
    String Surname;
    String personId;
    String password;
    UserStatus status;
}
