package com.geeks.dao;

import java.util.ArrayList;

import com.geeks.bean.QuestionBean;

public interface QuestionDao {
	public int addQuestion(QuestionBean questionBean);
	public int updateQuestion(QuestionBean questionBean);
	public int deleteQuestion(int questionId);
	public ArrayList<QuestionBean> getAllQuestion();
	public QuestionBean getQuestionById(int id);
}
