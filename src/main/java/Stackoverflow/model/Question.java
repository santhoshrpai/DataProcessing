package Stackoverflow.model;

import java.util.Date;

import Stackoverflow.operation.CommonUtil;

public class Question {

	private int id;
	private String title;
	private String content;
	private String code;
	private String text;
	private int userId;
	private int vote;
	private Date date;
	private String tags;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public static Question createQuestion(String[] row, int id) {
		Question question = new Question();
		question.setId(id);
		question.setTitle(row[1]);
		question.setContent(row[2]);
		question.setText(row[3]);
		question.setCode(row[4]);
		int userId = -1;
		if (!row[5].equals("")) {
			userId = Integer.parseInt(row[5]);
		}
		question.setUserId(userId);
		question.setDate(CommonUtil.getDateFromUnixTime(row[6]));
		question.setVote(Integer.parseInt(row[7]));
		question.setTags(row[10]);
		return question;
	}

}
