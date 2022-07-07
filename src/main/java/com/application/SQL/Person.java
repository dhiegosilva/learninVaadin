package com.application.SQL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Person {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;

  private String name;

  private String lastname;
  
  public Person() {
  }
  
  
  public Person(Integer id) {
	  this.id=id;
  }

  public Person(Integer id, String name, String lastname) {
	  this.id=id;
	  this.name=name;
	  this.lastname=lastname;
  }
  
  public Person(String name, String lastname) {
	  this.name=name;
	  this.lastname=lastname;
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
    this.name = name;
  }

  public String getLastName() {
    return lastname;
  }

  public void setLastName(String lastName) {
    this.lastname = lastName;
  }
}