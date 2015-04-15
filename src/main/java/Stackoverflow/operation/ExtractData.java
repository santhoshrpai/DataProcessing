package Stackoverflow.operation;

import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class ExtractData {

	public static void main(String[] args) throws UnknownHostException {
		DBDriver dbDriver = new DBDriver("Stackoverflow");
		// int i=1;
		// String fileName = null;

		// Calendar cal = Calendar.getInstance();
		// cal.getTime();
		// SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		// System.out.println("Start time: --- " + sdf.format(cal.getTime()));
		//
		// for (int i = 1; i <= 216; i++) {
		// String fileName =
		// "C:\\Users\\Santhosh\\Documents\\Spring 2015\\DV\\Project\\dataset\\stackexchange2014-java-master\\01_01_2014-12_31_2014["
		// + i + "].csv";
		// ParseCSV.parseCSV(fileName, dbDriver);
		// System.out.println("01_01_2014-12_31_2014[" + i +
		// "].csv -- completed");
		// }
		//
		// cal = Calendar.getInstance();
		// cal.getTime();
		// sdf = new SimpleDateFormat("HH:mm:ss");
		// System.out.println("End Time:" + sdf.format(cal.getTime()));

		MongoClient mongoClient = new MongoClient("localhost", 27017);
		DB db = mongoClient.getDB("Stackoverflow");
		DBCollection useCollection = db.getCollection("user");
		DBCollection userCollection = db.getCollection("users");
		DBCollection qCollection = db.getCollection("questions");
		DBCollection aCollection = db.getCollection("answers");
		// userCollection.drop();
		// qCollection.drop();
		// aCollection.drop();
		// useCollection.drop();
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("userId", 4403274);
		DBCursor cursor = userCollection.find(searchQuery);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}

		// BasicDBObject searchQuery = new BasicDBObject();
		// searchQuery.put("userId", 1707520);
		// BasicDBObject updateQuery = new BasicDBObject();
		// updateQuery.append("$set", new BasicDBObject("questionIds",
		// "1,3,5"));
		//
		// userCollection.update(searchQuery, updateQuery);

		// cursor = qCollection.find();
		// while (cursor.hasNext()) {
		// System.out.println(cursor.next());
		// }
		// cursor = aCollection.find();
		// while (cursor.hasNext()) {
		// System.out.println(cursor.next());
		// }
	}
}
