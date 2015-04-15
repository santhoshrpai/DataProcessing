package Stackoverflow.model;

import java.util.Date;

import Stackoverflow.operation.CommonUtil;

public class Answer {

	private int id;
	private String content;
	private String code;
	private String text;
	private int userId;
	private int vote;
	private Date date;
	private int questionId;
	private boolean isAccepted = false;
	private String tags;

	public boolean isAccepted() {
		return isAccepted;
	}

	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getUserIds() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getVote() {
		return vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public static Answer createAnswer(String[] row, int id, int questionId, boolean isAccepted) {
		Answer answer = new Answer();
		answer.setId(id);
		answer.setContent(row[2]);
		answer.setText(row[3]);
		answer.setCode(row[4]);
		int userId = -1;
		if (!row[5].equals("")) {
			userId = Integer.parseInt(row[5]);
		}
		answer.setUserId(userId);
		answer.setQuestionId(questionId);
		answer.setAccepted(isAccepted);
		answer.setDate(CommonUtil.getDateFromUnixTime(row[6]));
		answer.setVote(Integer.parseInt(row[7]));
		answer.setTags(row[10]);
		return answer;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getUserId() {
		return userId;
	}

}
