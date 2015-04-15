package Stackoverflow.model;

public class User {

	private int userId;
	private int noOfQuestions;
	private int noOfAnswers;
	private int acceptRate;
	private String questionIds;
	private String answerIds;
	private String acceptedAnswerIds;
	private int reputation;

	public User(int userId, int noOfQuestions, int noOfAnswers, int acceptRate, String questionIds, String answerIds,
			int reputation) {
		super();
		this.userId = userId;
		this.noOfQuestions = noOfQuestions;
		this.noOfAnswers = noOfAnswers;
		this.acceptRate = acceptRate;
		this.questionIds = questionIds;
		this.answerIds = answerIds;
		this.acceptedAnswerIds = answerIds;
		this.reputation = reputation;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getAcceptedAnswerIds() {
		return acceptedAnswerIds;
	}

	public void setAcceptedAnswerIds(String acceptedAnswerIds) {
		this.acceptedAnswerIds = acceptedAnswerIds;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getNoOfQuestions() {
		return noOfQuestions;
	}

	public void setNoOfQuestions(int noOfQuestions) {
		this.noOfQuestions = noOfQuestions;
	}

	public int getNoOfAnswers() {
		return noOfAnswers;
	}

	public void setNoOfAnswers(int noOfAnswers) {
		this.noOfAnswers = noOfAnswers;
	}

	public int getAcceptRate() {
		return acceptRate;
	}

	public void setAcceptRate(int acceptRate) {
		this.acceptRate = acceptRate;
	}

	public String getQuestionIds() {
		return questionIds;
	}

	public void setQuestionIds(String questionIds) {
		this.questionIds = questionIds;
	}

	public String getAnswerIds() {
		return answerIds;
	}

	public void setAnswerIds(String answerIds) {
		this.answerIds = answerIds;
	}

	public int getReputation() {
		return reputation;
	}

	public void setReputation(int reputation) {
		this.reputation = reputation;
	}
}
