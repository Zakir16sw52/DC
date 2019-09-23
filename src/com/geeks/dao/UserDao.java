package com.geeks.dao;

import java.util.ArrayList;

import com.geeks.bean.UserBean;

public interface UserDao {
	public int addUser(UserBean userBean);
	public int updateUser(UserBean userBean);
	public int deleteUser(UserBean userId);
	public ArrayList<UserBean> getAllUser();
	public UserBean getUserById(int userId);
	
	public UserBean checkUser(UserBean bean);
	
}
