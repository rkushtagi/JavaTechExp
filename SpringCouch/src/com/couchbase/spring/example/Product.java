package com.couchbase.spring.example;

public class Product {
	
	String id;
	String description;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void init(){
		
		System.out.println("calling init() method....");
		
	}
	
	public void destroy(){
		
		System.out.println("calling destroy() method....");
		
	}

}
