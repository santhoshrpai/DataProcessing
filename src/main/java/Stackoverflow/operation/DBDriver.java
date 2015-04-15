package Stackoverflow.operation;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class DBDriver {

	public static DB db;

	public DB getDb() {
		return db;
	}

	public DBDriver(String dbName) {
		db = connectToDB(dbName);
	}

	public DBCollection getCollection(DB db, String collection) {
		return db.getCollection(collection);
	}

	public DB connectToDB(String dbName) {
		MongoClient mongoClient = null;
		try {
			mongoClient = new MongoClient("localhost", 27017);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		DB db = mongoClient.getDB(dbName);
		return db;
	}

	public boolean userExists(DBCollection collection, int userId) {
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("userId", userId);
		DBCursor cursor = collection.find(searchQuery);
		if (cursor.hasNext()) {
			return true;
		}
		return false;
	}

	public void insertToDB(DBCollection collection, BasicDBObject object) {
		collection.insert(object);
	}

	public void updateObject(DBCollection collection, String searchKey, int searchValue, String updateKey,
			String updateValue) {
		
		// BasicDBObject searchQuery = new BasicDBObject();
		// searchQuery.put("userId", 1707520);
		// BasicDBObject updateQuery = new BasicDBObject();
		// updateQuery.append("$set", new BasicDBObject("questionIds", "1,3,5"));
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put(searchKey, searchValue);
		BasicDBObject updateQuery = new BasicDBObject();
		updateQuery.append("$set", new BasicDBObject(updateKey, updateValue));
		collection.updateMulti(searchQuery, updateQuery);
	}

	public void updateObjects(DBCollection collection, BasicDBObject searchQuery, String key, String newValue) {
		BasicDBObject updateQuery = new BasicDBObject();
		updateQuery.append("$set", new BasicDBObject(key, newValue));
		collection.updateMulti(searchQuery, updateQuery);
	}

	public void printData(DBCollection collection) {
		DBCursor cursor = collection.find();
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}

	// public String getUpdatedAnswerIds(DBCollection collection, int userId,
	// int newIdToBeAppended) {
	// return getUpdatedValue(collection, userId, newIdToBeAppended,
	// "answerIds");
	// }
	//
	// public String getUpdatedQuestionIds(DBCollection collection, int userId,
	// int newIdToBeAppended) {
	// return getUpdatedValue(collection, userId, newIdToBeAppended,
	// "questionIds");
	// }

	public String getUpdatedValue(DBCollection collection, int userId, String key, int newValue) {
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("userId", userId);

		DBCursor cursor = collection.find(searchQuery);
		String newIds = "";

		if (cursor.hasNext()) {
			DBObject obj = cursor.next();
			newIds = (String) obj.get(key);
			if (newIds == null || newIds.equals("-1")) {
				return newValue + "";
			}
//			System.out.println("UpdatedValue:" + key + ": " + newIds + "," + newValue);
			return newIds + "," + newValue;
		} else {
			return newValue + "";
		}
	}

	public void insert(DBCollection collection, BasicDBObject object) {
		collection.insert(object);
	}

	public void incrementUserQuestions(DBCollection collection, int userId) {
		increment(collection, userId, "noOfQuestions");
	}

	public void incrementUserAnswers(DBCollection collection, int userId) {
		increment(collection, userId, "noOfAnswers");
	}

	public void increment(DBCollection collection, int userId, String key) {
		BasicDBObject newDocument = new BasicDBObject().append("$inc", new BasicDBObject().append(key, 1));
		collection.update(new BasicDBObject().append("userId", userId), newDocument);
	}

}
