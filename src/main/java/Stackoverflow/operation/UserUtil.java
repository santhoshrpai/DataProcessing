package Stackoverflow.operation;

import Stackoverflow.model.User;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.util.JSON;

public class UserUtil {

	private static String USER_COLLECTION = "users";
	private static String ACCEPTED_ANSWERS_ID = "acceptedAnswerIds";

	public static User createUser(String[] row, int questions, int answers, int questionId, int answerId,
			boolean isAccepted) {
		User user = new User();
		int userId = -1;
		if (!row[5].equals("")) {
			userId = Integer.parseInt(row[5]);
		}
		user.setUserId(userId);
		user.setReputation(Integer.parseInt(row[8]));
		user.setAcceptRate(Integer.parseInt(row[9]));
		user.setNoOfQuestions(questions);
		user.setNoOfAnswers(answers);
		// if (questionId != -1)
		user.setQuestionIds(questionId + "");
		// if (answerId != -1) {
		user.setAnswerIds(answerId + "");
		// if (isAccepted)
		user.setAcceptedAnswerIds(answerId + "");
		// }
		return user;
	}

	public static void updateUser(DBDriver driver, String[] row, int userId, String typeToIncrement, String type,
			int newTypeValue, boolean isAcceptedAnswer) {
		DBCollection collection = DBDriver.db.getCollection(USER_COLLECTION);
		boolean userExists = driver.userExists(collection, userId);
		if (userExists) {
			UserUtil.updateUser(driver, collection, userId, typeToIncrement, type, newTypeValue);
			if (isAcceptedAnswer) {
				updateUserObject(driver, collection, userId, ACCEPTED_ANSWERS_ID, newTypeValue);
			}
		} else {
			int questions = 0;
			int answers = 0;
			int questionId = -1;
			int answerId = -1;
			if (typeToIncrement.equals(ParseCSV.NO_OF_QUESTIONS)) {
				questions++;
				questionId = ParseCSV.questionId;
			} else {
				answers++;
				answerId = ParseCSV.answerId;
			}
			User user = UserUtil.createUser(row, questions, answers, questionId, answerId, isAcceptedAnswer);
			Gson gson = new Gson();
			BasicDBObject obj = (BasicDBObject) JSON.parse(gson.toJson(user));
			driver.insert(collection, obj);
		}
	}

	public static void updateUser(DBDriver driver, DBCollection collection, int userId, String typeToIncrement,
			String type, int newTypeValue) {
		// String questionIds = driver.getUpdatedQuestionIds(collection, userId,
		// ParseCSV.questionId);
		// String answerIds = driver.getUpdatedAnswerIds(collection, userId,
		// ParseCSV.answerId);
		updateUserObject(driver, collection, userId, type, newTypeValue);
		// String newValues = driver.getUpdatedValue(collection, userId, type,
		// newTypeValue);
		//
		// driver.updateObject(collection, "userId", userId + "", type,
		// newValues);
		// driver.updateObject(collection, "userId", userId, "", questionIds);
		driver.increment(collection, userId, typeToIncrement);
	}

	public static void updateUserObject(DBDriver driver, DBCollection collection, int userId, String type,
			int newTypeValue) {
		String newValues = driver.getUpdatedValue(collection, userId, type, newTypeValue);
		driver.updateObject(collection, "userId", userId, type, newValues);
	}

	public boolean userExists(DBDriver driver, DBCollection collection, int userId) {
		return driver.userExists(collection, userId);
	}
}
