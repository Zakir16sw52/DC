package com.geeks.dao;

import java.util.ArrayList;

import com.geeks.bean.CategoryBean;


public interface CategoryDao {
	public int addCategory(CategoryBean categoryBean);
	public ArrayList<CategoryBean> getAllCategory();
	public ArrayList<CategoryBean> getAllParents();
	public CategoryBean getCategoryByParentId(int parentId);
	public int deleteCategory(CategoryBean category);
	public int updateCategory(CategoryBean category);
}
