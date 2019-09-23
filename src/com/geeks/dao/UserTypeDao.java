package com.geeks.dao;

import java.util.ArrayList;

import com.geeks.bean.UserTypeBean;

public interface UserTypeDao {
	public int addUserType(UserTypeBean userTypeBean);
	public int updateUserType(UserTypeBean userTypeBean);
	public int deteteUserType(UserTypeBean UserTypeId);
	public ArrayList<UserTypeBean> getAllUserType();
	public UserTypeBean getUserTypeById(int userTypeId);
}
