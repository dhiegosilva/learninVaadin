package com.application.SQL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UserTable")
public class User {
   @Id
   @GeneratedValue
   private Long id;
   private String firstName;
   private String lastName;
   private String username;
   private String email;
   private String password;

   protected User() {
   }

   public User(String firstName, String lastName, String username, String email, String password) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.username = username;
      this.email = email;
      this.password = password;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }
   
   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }
   
   public String getPassword() {
	   return password;
   }
   
   public void setPassword(String password) {
	   this.password = password;
   }
}