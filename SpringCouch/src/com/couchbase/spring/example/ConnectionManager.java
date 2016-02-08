package com.couchbase.spring.example;

import java.util.NoSuchElementException;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.PersistTo;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ConnectionManager {

private static final ConnectionManager connectionManager = new ConnectionManager();

  public static ConnectionManager getInstance() {
  	return connectionManager;
  }

  static Cluster cluster = CouchbaseCluster.create();
  static Bucket bucket = cluster.openBucket("products");
  
  public static void disconnect() {
  	//cluster.disconnect().toBlocking().single();
	  cluster.disconnect();
  }

public static JsonDocument insertProduct(){
	
	AbstractApplicationContext context = new ClassPathXmlApplicationContext("/beans.xml");

    Product product = (Product) context.getBean("product");
    
    JsonObject productJson = JsonObject.empty().put("id", product.getId()).put("description", product.getDescription());
    
    //ObjectMapper mapper = new ObjectMapper();
    
    JsonDocument doc = JsonDocument.create(product.getId(), productJson);
    
    return bucket.upsert(doc);
    
}

public static JsonDocument getProduct(String id) {
	JsonDocument response = null;
	try {
		response = bucket.get(id);
	} catch (NoSuchElementException e) {
		System.out.println("ERROR: No element with message: "
				+ e.getMessage());
		e.printStackTrace();
	}
	return response;
}

public static void updateProduct(JsonDocument doc) {
	bucket.upsert(doc);
}


public static void deleteProduct(String id) {
	try {
		bucket.remove(id, PersistTo.MASTER);
	} catch (NoSuchElementException e){
		System.out.println("ERROR: No element with message: "
      + e.getMessage());
	}
}
  
}
