package Stackoverflow.operation;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.util.JSON;

import Stackoverflow.model.Answer;
import Stackoverflow.model.Question;

public class QAUtil {

	private static String Q_COLLECTION = "questions";
	private static String A_COLLECTION = "answers";

	public static void insert(DBDriver driver, Question question) {
		Gson gson = new Gson();
		DBCollection questionCollection = DBDriver.db.getCollection(Q_COLLECTION);
		BasicDBObject obj = (BasicDBObject) JSON.parse(gson.toJson(question));
		driver.insert(questionCollection, obj);
	}

	public static void insert(DBDriver driver, Answer answer) {
		Gson gson = new Gson();
		DBCollection answerCollection = DBDriver.db.getCollection(A_COLLECTION);
		BasicDBObject obj = (BasicDBObject) JSON.parse(gson.toJson(answer));
		driver.insert(answerCollection, obj);
	}

}
