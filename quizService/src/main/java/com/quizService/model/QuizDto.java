package com.quizService.model;

public class QuizDto {

	String category;
	String title;
	Integer numQuestions;
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getNumQuestions() {
		return numQuestions;
	}
	public void setNumQuestions(Integer numQuestions) {
		this.numQuestions = numQuestions;
	}
	public QuizDto(String category, String title, Integer numQuestions) {
		super();
		this.category = category;
		this.title = title;
		this.numQuestions = numQuestions;
	}
	public QuizDto() {
	}
	
	
}
