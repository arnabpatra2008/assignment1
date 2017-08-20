package com.Person;

public class Person {
	
	private String first_name;
	private String last_name;
	
	
	public Person() {
		
	}
	
	public Person(String first_name, String last_name)
	{
		this.first_name = first_name;
		this.last_name = last_name;
	}
	
	public String getfirst_name() {
		return first_name;
	}
	
	public void setfirst_name(String first_name) {
		this.first_name = first_name;
	}
	
	public String getlast_name() {
		return last_name;
	}
	
	public void setLastName(String last_name) {
		this.last_name = last_name;
	}
	
	
	@Override
	public String toString() {
		
		return "First Name : "+this.first_name+" - Last Name : "+this.last_name;
	}

}
