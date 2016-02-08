package com.couchbase.spring.example;

import com.couchbase.client.java.document.JsonDocument;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ConnectionManager.insertProduct();
		JsonDocument doc = ConnectionManager.getProduct("1234");
		
		System.out.println("Retrieved Json document:"+doc);

	}

}
