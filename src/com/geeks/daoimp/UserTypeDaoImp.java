package com.geeks.daoimp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.geeks.bean.QuestionBean;
import com.geeks.bean.UserBean;
import com.geeks.bean.UserTypeBean;
import com.geeks.dao.UserTypeDao;
import com.geeks.util.DbConnection;

public class UserTypeDaoImp implements UserTypeDao {
	private Connection con;
	private PreparedStatement pst;
	private ResultSet result;
	private String sql;
	private int row;
	private ArrayList<UserTypeBean> listUserType;
	@Override
	public int addUserType(UserTypeBean userTypeBean) {
		try {
			 con=DbConnection.getConnection();
			 sql="insert into user_type(name ,created_by,modified_by) values(?,?,?)";
			 pst=con.prepareStatement(sql);
			 pst.setString(1, userTypeBean.getName());
			 pst.setString(2, userTypeBean.getCreatedBy());
			 pst.setString(3, userTypeBean.getModifiedBy());
			 return pst.executeUpdate();
			 
			 
		 }catch (Exception e) {
			 e.printStackTrace();
		}
		 return 0;
	}
	@Override
	public int updateUserType(UserTypeBean userTypeBean) {
		try {
			 con=DbConnection.getConnection();
			 sql="update user_type set name=?,modified_by=?,modified_date=? where user_type_id=?";
			 pst=con.prepareStatement(sql);
			 pst.setString(1, userTypeBean.getName());
			 pst.setString(2, userTypeBean.getModifiedBy());
			 pst.setString(3, userTypeBean.getModifiedDate());
			 pst.setInt(4, userTypeBean.getUserTypeId());
			 return pst.executeUpdate();
			 
			 
		 }catch (Exception e) {
			 e.printStackTrace();
		}
		 return 0;
	}
	@Override
	public int deteteUserType(UserTypeBean userType) {
		try {
			System.out.println("inside="+userType.getModifiedBy());
			 con=DbConnection.getConnection();
			 sql="update user_type set active=0,modified_by=?,modified_date=? where user_type_id=?";
			 pst=con.prepareStatement(sql);
			 pst.setString(1,userType.getModifiedBy());
			 pst.setString(2,userType.getModifiedDate());
			 pst.setInt(3, userType.getUserTypeId());
			 return pst.executeUpdate();
			}catch (Exception e) {
				 e.printStackTrace();
			}
			return 0;
	}
	@Override
	public ArrayList<UserTypeBean> getAllUserType() {
		 try {
			 listUserType=new ArrayList<>();
			 con=DbConnection.getConnection();
			 sql="select * from  user_type where active=1";
			 pst=con.prepareStatement(sql);
			 result=pst.executeQuery();
			 while(result.next()) {
				 UserTypeBean userTypeBean=new UserTypeBean();
				 userTypeBean.setUserTypeId(result.getInt(1));
				 userTypeBean.setName(result.getString(2));
				 userTypeBean.setCreatedBy(result.getString(4));
				 userTypeBean.setCreatedDate(result.getString(5));
				 userTypeBean.setModifiedBy(result.getString(6));
				 userTypeBean.setModifiedDate(result.getString(7));
				 listUserType.add(userTypeBean);
			 }
			 return listUserType;
		 }catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
		}
		 return null;
	}
	@Override
	public UserTypeBean getUserTypeById(int userTypeId) {
		try {
			 
			 UserTypeBean userTypeBean=null;
			 con=DbConnection.getConnection();
			 sql="select * from  user_type where active=1 and user_type_id=?";
			 pst=con.prepareStatement(sql);
			 pst.setInt(1,userTypeId);
			 result=pst.executeQuery();
			 while(result.next()) {
				 userTypeBean=new UserTypeBean();
				 userTypeBean.setUserTypeId(result.getInt(1));
				 userTypeBean.setName(result.getString(2));
				 userTypeBean.setCreatedBy(result.getString(4));
				 userTypeBean.setCreatedDate(result.getString(5));
				 userTypeBean.setModifiedBy(result.getString(6));
				 userTypeBean.setModifiedDate(result.getString(7));  
			 }
			 return userTypeBean;
		 }catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
		}
		 return null;
	}
}
