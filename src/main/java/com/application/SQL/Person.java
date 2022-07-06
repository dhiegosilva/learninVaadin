package com.application.SQL;


public class Person {
private long id; // private = restricted access
private String name; // private = restricted access
private String lastName; // private = restricted access
	
	  public Person(long id, String name, String lastName) {
		  this.id=id;
		  this.name=name;
		  this.lastName=lastName;
	  }
	
	  // Getter
	  public long getId() {
		return id;
	  }
	  
	  public String getName() {
	    return name;
	  }
	  public String getLastName() {
	    return lastName;
	  }
	
	  // Setter
	  public void setName(String newName) {
	    this.name = newName;
	  }
	  public void setLastName(String newName) {
	    this.lastName = newName;
	  }
	  

	

}