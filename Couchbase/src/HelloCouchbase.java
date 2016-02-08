import java.util.Iterator;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.view.ViewQuery;
import com.couchbase.client.java.view.ViewResult;
import com.couchbase.client.java.view.ViewRow;


public class HelloCouchbase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Cluster cluster = CouchbaseCluster.create();
		Bucket bucket = cluster.openBucket();
		JsonObject user = JsonObject.empty()
			    .put("firstname", "Roopa")
			    .put("lastname", "Kushtagi")
			    .put("job", "software professional")
			    .put("age", 50);
		JsonDocument doc = JsonDocument.create("roopa", user);
		JsonDocument response = bucket.upsert(doc);
		
		System.out.println("Inserted document successfully:"+response);
		
		JsonDocument walter = bucket.get("walter");
		
		System.out.println("Retrieved document from couchbase:"+walter);
		
		ViewResult result = bucket.query(ViewQuery.from("dev_default", "view1"));

		Iterator<ViewRow> rowIterator = result.rows();
		while (rowIterator.hasNext()) {
		    ViewRow row = rowIterator.next();
		    JsonDocument view = row.document();

		   /* if (brew.content().getString("type").equals("beer")) {
		        System.out.println(doc.content().getString("name"));
		    }*/
		    System.out.println("View details:"+view.content());
		}
		
		bucket.close();
		cluster.disconnect();

	}

}
