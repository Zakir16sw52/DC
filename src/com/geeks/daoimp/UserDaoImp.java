package com.geeks.daoimp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.geeks.bean.QuestionBean;
import com.geeks.bean.UserBean;
import com.geeks.bean.UserTypeBean;
import com.geeks.dao.UserDao;
import com.geeks.util.DbConnection;


public class UserDaoImp implements UserDao{
	private Connection con;
	private PreparedStatement pst;
	private ResultSet result;
	private String sql;
	private int row;
	private ArrayList<UserBean> userList;
	
	@Override
	public int addUser(UserBean userBean) {
		 try {
			 con=DbConnection.getConnection();
			 sql="insert into user(user_name,password,user_type_id,image,name,contact,address,question_id,answer,created_by,modified_by) values(?,?,?,?,?,?,?,?,?,?,?)";
			 pst=con.prepareStatement(sql);
			 pst.setString(1, userBean.getUserName());
			 pst.setString(2, userBean.getPassword());
			 pst.setInt(3, userBean.getUserType().getUserTypeId());
			 pst.setString(4, userBean.getImage());
			 pst.setString(5, userBean.getName());
			 pst.setString(6, userBean.getContact());
			 pst.setString(7, userBean.getAddress());
			 pst.setInt(8, userBean.getQuestion().getQuestionId());
			 pst.setString(9, userBean.getAnswer());
			 pst.setString(10, userBean.getCreatedBy());
			 pst.setString(11, userBean.getModifiedBy());
			 return pst.executeUpdate();
			 
			 
		 }catch (Exception e) {
			 e.printStackTrace();
		}
		 return 0;
	}

	@Override
	public int updateUser(UserBean userBean) {
		 try {
			 con=DbConnection.getConnection();
			 sql="update user set user_name=?,password=?,user_type_id=?,image=?,name=?,contact=?,address=?,question_id=?,answer=?,modified_by=?,modified_date=? where user_id=? ";
			 pst=con.prepareStatement(sql);
			 pst.setString(1, userBean.getUserName());
			 pst.setString(2, userBean.getPassword());
			 pst.setInt(3, userBean.getUserType().getUserTypeId());
			 pst.setString(4, userBean.getImage());
			 pst.setString(5, userBean.getName());
			 pst.setString(6, userBean.getContact());
			 pst.setString(7, userBean.getAddress());
			 pst.setInt(8, userBean.getQuestion().getQuestionId());
			 pst.setString(9, userBean.getAnswer());
			 pst.setString(10, userBean.getModifiedBy());
			 pst.setString(11, userBean.getModifiedDate());
			 pst.setInt(12, userBean.getUserId());
			 System.out.println("Name:"+userBean.getName());
			 System.out.println("pst="+pst.toString());
			 return pst.executeUpdate();
			 
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteUser(UserBean userId) {
		try {
		 con=DbConnection.getConnection();
		 sql="update user set active=0,modified_by=?,modified_date=? where user_id=?";
		 pst=con.prepareStatement(sql);
		 pst.setString(1, userId.getModifiedBy());
		 pst.setString(2, userId.getModifiedDate());
		 pst.setInt(3, userId.getUserId());
		
		 return pst.executeUpdate();
		}catch (Exception e) {
			 e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ArrayList<UserBean> getAllUser() {
		try {
			 userList=new ArrayList<>();
			 con=DbConnection.getConnection();
			 sql="select u.*,ut.name,q.name from  user u  inner join user_type ut on u.user_type_id=ut.user_type_id inner join question q on q.question_id=u.question_id where u.active=1";
			 pst=con.prepareStatement(sql);
			 result=pst.executeQuery();
			 while(result.next()) {
				 UserBean userBean=new UserBean();
				 userBean.setUserId(result.getInt(1));
				 userBean.setUserName(result.getString(2));
				 userBean.setPassword(result.getString(3));
				 UserTypeBean userType=new UserTypeBean();
				 userType.setUserTypeId(result.getInt(4));
				 userBean.setImage(result.getString(5));
				 userBean.setName(result.getString(6));
				 userBean.setAddress(result.getString(7));
				 userBean.setContact(result.getString(8));
				 QuestionBean questionBean=new QuestionBean();
				 questionBean.setQuestionId(result.getInt(9));
				 userBean.setAnswer(result.getString(10));
				 userBean.setCreatedBy(result.getString(12));
				 userBean.setCreatedDate(result.getString(13));
				 userBean.setModifiedBy(result.getString(14));
				 userBean.setModifiedDate(result.getString(15));
				 userType.setName(result.getString(16));
				 questionBean.setName(result.getString(17));
				 userBean.setUserType(userType);
				 userBean.setQuestion(questionBean);
				  userList.add(userBean);
			 }
			 return userList;
		 }catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
		}
		 return null;
	}

	@Override
	public UserBean getUserById(int userId) {
		try {
			 userList=new ArrayList<>();
			 UserBean userBean=null;
			 con=DbConnection.getConnection();
			 sql="select u.*,ut.name,q.name from  user u  inner join user_type ut on u.user_type_id=ut.user_type_id inner join question q on q.question_id=u.question_id where u.active=1 and user_id=?";
			 pst=con.prepareStatement(sql);
			 pst.setInt(1,userId);
			 result=pst.executeQuery();
			 while(result.next()) {
				 userBean=new UserBean();
				 userBean.setUserId(result.getInt(1));
				 userBean.setUserName(result.getString(2));
				 userBean.setPassword(result.getString(3));
				 UserTypeBean userType=new UserTypeBean();
				 userType.setUserTypeId(result.getInt(4));
				 userBean.setImage(result.getString(5));
				 userBean.setName(result.getString(6));
				 userBean.setAddress(result.getString(7));
				 userBean.setContact(result.getString(8));
				 QuestionBean questionBean=new QuestionBean();
				 questionBean.setQuestionId(result.getInt(9));
				 userBean.setAnswer(result.getString(10));
				 userBean.setCreatedBy(result.getString(12));
				 userBean.setCreatedDate(result.getString(13));
				 userBean.setModifiedBy(result.getString(14));
				 userBean.setModifiedDate(result.getString(15));
				 userType.setName(result.getString(16));
				 questionBean.setName(result.getString(17));
				 userBean.setUserType(userType);
				 userBean.setQuestion(questionBean);
				  
			 }
			 return userBean;
		 }catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
		}
		 return null;
	}

	@Override
	public UserBean checkUser(UserBean bean) {
		try {
			con=DbConnection.getConnection();
			sql="select * from user where user_name=? and password=?";
			pst=con.prepareStatement(sql);
			pst.setString(1, bean.getUserName());
			pst.setString(2, bean.getPassword());
			result=pst.executeQuery();
			while(result.next()) {
				bean.setImage(result.getString(5));
				bean.setName(result.getString(6));
				return bean;
			}
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}

}
