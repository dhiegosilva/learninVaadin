package com.application.Objects;


public class Person {
private String name; // private = restricted access
private String lastName; // private = restricted access
	
	  public Person(String name, String lastName) {
		  this.name=name;
		  this.lastName=lastName;
	  }
	
	  // Getter
	  public String getName() {
	    return name;
	  }
	
	  // Setter
	  public void setName(String newName) {
	    this.name = newName;
	  }
	  
	  public String getLastName() {
	    return lastName;
	  }
	
	  // Setter
	  public void setLastName(String newName) {
	    this.lastName = newName;
	  }
}