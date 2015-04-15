package Stackoverflow.operation;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import Stackoverflow.model.User;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws UnknownHostException {
		// System.out.println("Hello World!");
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		DB db = mongoClient.getDB("Stackoverflow");
		// boolean auth = db.authenticate("mongo", "password".toCharArray());
		// MongoClient mongoClient = new MongoClient();

		DBCollection userCollection = db.getCollection("user");
		// BasicDBObject document = new BasicDBObject();
		// document.put("name", "santhosh");
		// document.put("reputation", "3291");
		// document.put("questions", "45");
		// collection.insert(document);

		User user = new User();
		user.setUserId(12345);
		user.setReputation(12345);
		user.setAcceptRate(100);
		user.setNoOfAnswers(2);
		user.setNoOfQuestions(3);
		user.setQuestionIds("1,2,3");
		user.setAnswerIds("1,2,3");

		Gson gson = new Gson();
		BasicDBObject userObj = (BasicDBObject) JSON.parse(gson.toJson(user));

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("userId", 12345);

		// userCollection.insert(userObj);

		// List<String> dbs = mongoClient.getDatabaseNames();
		// for(String dbase:dbs) {
		// System.out.println(dbase);
		// }

		// BasicDBObject newDocument = new BasicDBObject();
		// searchQuery.append("name", "santhosh-updated");

		// BasicDBObject updateQuery = new BasicDBObject();
		// updateQuery.append("$set", new BasicDBObject("name","updated2"));
		//
		// userCollection.updateMulti(searchQuery, updateQuery);
		//
		// BasicDBObject newSearchQuery = new BasicDBObject();
		// newSearchQuery.put("name", "santhosh-updated");
		//

		BasicDBObject newDocument = new BasicDBObject().append("$inc", new BasicDBObject().append("noOfQuestions", 1));
		userCollection.update(new BasicDBObject().append("userId", 12345), newDocument);
		DBCursor cursor = userCollection.find(searchQuery);

		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			String question = (String) obj.get("questionIds");
			System.out.println(question);
		}

	}
}
