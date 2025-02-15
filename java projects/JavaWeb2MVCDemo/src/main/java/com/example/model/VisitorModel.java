package com.example.model;

public class VisitorModel {
	private String title;
	private String fullName;
	private String gender;
	
	
	
	
	public VisitorModel() {
		super();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "VisiterModel [title=" + title + ", fullName=" + fullName + ", gender=" + gender + "]";
	}
	

}
