package com.geeks.bean;

public class UserBean extends Security{
	private int userId;
	private String userName;
	private String password;
	private String name;
	private String contact;
	private String address;
	private UserTypeBean userType;
	private String answer;
	private QuestionBean question;
	private String image;
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public UserTypeBean getUserType() {
		return userType;
	}
	public void setUserType(UserTypeBean userType) {
		this.userType = userType;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public QuestionBean getQuestion() {
		return question;
	}
	public void setQuestion(QuestionBean question) {
		this.question = question;
	}
	
}
