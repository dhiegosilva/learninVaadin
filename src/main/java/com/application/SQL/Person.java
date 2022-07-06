package com.application.SQL;

public class Person {
	
private int id; // private = restricted access
private String name; // private = restricted access
private String lastName; // private = restricted access
	
	  public Person(int id, String name, String lastName) {
		  this.id=id;
		  this.name=name;
		  this.lastName=lastName;
	  }
	
	  public Person(String name, String lastName) {
		  this.name=name;
		  this.lastName=lastName;
	}

	// Getter
	  public int getId() {
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