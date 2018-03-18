
package db.mongodb;

import java.text.ParseException;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;



public class MongoDBTableCreation {
  // Run as Java application to create MongoDB collections with index.
  public static void main(String[] args) {
		// Step 1: Connection to MongoDB
		MongoClient mongoclient = new MongoClient();
		MongoDatabase db = mongoclient.getDatabase(MongoDBUtil.DB_NAME);

		// Step 2: remove old collections.
		db.getCollection("users").drop();
		db.getCollection("items").drop();

		// Step 3: create new collections.
		IndexOptions indexOptions = new IndexOptions().unique(true);
		db.getCollection("users").createIndex(new Document("user_id", 1), indexOptions);
		db.getCollection("items").createIndex(new Document("item_id", 1), indexOptions);

		// Step 4: insert fake user data
		db.getCollection("users").insertOne(new Document().append("first_name", "John").append("last_name", "Smith")
				.append("user_id", "1111").append("password", "3229c1097c00d497a0fd282d586be050"));

		mongoclient.close();
		System.out.println("Import is done successfully.");
  }
}
////Create tables for MongoDB (all pipelines).
//public class MongoDBTableCreation {
//// Run as Java application to create MongoDB tables with index.
//public static void main(String[] args) throws ParseException {
// MongoClient mongoClient = new MongoClient();
// MongoDatabase db = mongoClient.getDatabase(MongoDBUtil.DB_NAME);
//
// // Step 1: remove old collections.
// db.getCollection("users").drop();
// db.getCollection("items").drop();
//
// // Step 2: populate data and create index.
// db.getCollection("users")
//     .insertOne(new Document().append("first_name", "John").append("last_name", "Smith")
//         .append("password", "3229c1097c00d497a0fd282d586be050").append("user_id", "1111"));
// // make sure user_id is unique.
// IndexOptions indexOptions = new IndexOptions().unique(true);
//
// // use 1 for ascending index , -1 for descending index
// // Different to MySQL, users table in MongoDB also has history info.
// db.getCollection("users").createIndex(new Document("user_id", 1), indexOptions);
//
// // make sure item_id is unique.
// // Different to MySQL, items table in MongoDB also has categories info.
// db.getCollection("items").createIndex(new Document("item_id", 1), indexOptions);
//
// mongoClient.close();
// System.out.println("Import is done successfully.");
//}
//}