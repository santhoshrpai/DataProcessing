package Stackoverflow.operation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Stackoverflow.model.Answer;
import Stackoverflow.model.Question;

public class ParseCSV {

	public static int questionId = -1;
	public static int answerId = -1;
	public static String NO_OF_QUESTIONS = "noOfQuestions";
	public static String NO_OF_ANSWERS = "noOfAnswers";
	public static String QUESTION_IDS = "questionIds";
	public static String ANSWER_IDS = "answerIds";
	public static String QUESTION = "question";
	public static String ANSWER = "answer";
	public static String ACCEPTED_ANSWER = "accepted-answer";

	public static void parseCSV(String csvFile, DBDriver driver) {
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		int i = 0;

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				if (i++ == 0) {
					continue;
				}

				String[] row = line.split(cvsSplitBy);
				String type = row[0];
				String typeToIncrement = null;
				String typeId = null;
				boolean isAcceptedAnswer = false;
				int qORANewIndex = -1;
				int userId = -1;
				if (!row[5].equals("")) {
					userId = Integer.parseInt(row[5]);
				}

				if (type.equals(ACCEPTED_ANSWER)) {
					isAcceptedAnswer = true;
				}

				if (type.equals(QUESTION)) {
					questionId++;
					Question question = Question.createQuestion(row, questionId);
					typeToIncrement = NO_OF_QUESTIONS;
					typeId = QUESTION_IDS;
					qORANewIndex = questionId;
					QAUtil.insert(driver, question);
				} else {
					answerId++;
					Answer answer = Answer.createAnswer(row, answerId, questionId, isAcceptedAnswer);
					typeToIncrement = NO_OF_ANSWERS;
					typeId = ANSWER_IDS;
					qORANewIndex = answerId;
					QAUtil.insert(driver, answer);
				}

				// update or create the user
				UserUtil.updateUser(driver, row, userId, typeToIncrement, typeId, qORANewIndex, isAcceptedAnswer);

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
