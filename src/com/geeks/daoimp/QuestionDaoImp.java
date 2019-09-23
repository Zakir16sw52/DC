package com.geeks.daoimp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.geeks.bean.QuestionBean;
import com.geeks.bean.UserTypeBean;
import com.geeks.dao.QuestionDao;
import com.geeks.util.DbConnection;

public class QuestionDaoImp implements QuestionDao {
	private Connection con;
	private PreparedStatement pst;
	private ResultSet result;
	private String sql;
	private int row;
	private ArrayList<QuestionBean> questionList;
	@Override
	public int addQuestion(QuestionBean questionBean) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int updateQuestion(QuestionBean questionBean) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int deleteQuestion(int questionId) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public ArrayList<QuestionBean> getAllQuestion() {
		try {
			 questionList=new ArrayList<>();
			 con=DbConnection.getConnection();
			 sql="select * from question where active=1";
			 pst=con.prepareStatement(sql);
			 result=pst.executeQuery();
			 while(result.next()) {
				 QuestionBean questionBean=new QuestionBean();
				 questionBean.setQuestionId(result.getInt(1));
				 questionBean.setName(result.getString(2));
				 questionList.add(questionBean);
			 }
			 return questionList;
		 }catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
		}
		 return null;
	}
	@Override
	public QuestionBean getQuestionById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
