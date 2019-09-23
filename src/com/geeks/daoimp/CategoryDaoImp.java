package com.geeks.daoimp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.geeks.bean.CategoryBean;
import com.geeks.dao.CategoryDao;
import com.geeks.util.DbConnection;

public class CategoryDaoImp implements CategoryDao {
	private Connection con;
	private PreparedStatement pst;
	private ResultSet result;
	private String sql;
	private int row;
	private CategoryBean category ;
	private ArrayList<CategoryBean> categoryList;
	@Override
	public int addCategory(CategoryBean category) {
		try {
		con=DbConnection.getConnection();
		 if( category.getParentId()!=0) {
			 sql="insert into category(parent_id,name ,created_by,modified_by) values(?,?,?,?)";
			 pst=con.prepareStatement(sql);
			 pst.setInt(1, category.getParentId());
			 pst.setString(2, category.getName());
			 pst.setString(3, category.getCreatedBy());
			 pst.setString(4, category.getModifiedBy());
		 }
		 else {
			 sql="insert into category (name ,created_by,modified_by) values(?,?,?)";
			 pst=con.prepareStatement(sql);
			 pst.setString(1, category.getName());
			 pst.setString(2, category.getCreatedBy());
			 pst.setString(3, category.getModifiedBy());
		 }
		 
		 return pst.executeUpdate();
		}catch (Exception e) {
			 e.printStackTrace();
		}
		return 0;
	}
	@Override
	public ArrayList<CategoryBean> getAllParents() {
		 try {
			 categoryList=new ArrayList<>();
			 con=DbConnection.getConnection();
			 sql="SELECT * FROM `category` WHERE `parent_id` IS NULL and active=1";
			 pst=con.prepareStatement(sql);
			 result=pst.executeQuery();
			 while(result.next()) {
				 CategoryBean category=new CategoryBean();
				 category.setCategoryId(result.getInt(1));
				 category.setParentId(result.getInt(2));
				 category.setName(result.getString(3));
				 category.setCreatedBy(result.getString(4));
				 category.setCreatedDate(result.getString(5));
				 category.setModifiedBy(result.getString(6));
				 category.setModifiedDate(result.getString(7));
				 categoryList.add(category);
			 }
			 return categoryList;
		 }catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
		}
		 return null;
	}

	@Override
	public ArrayList<CategoryBean> getAllCategory() {
		 try {
			 categoryList=new ArrayList<>();
			 con=DbConnection.getConnection();
			 sql="SELECT * FROM `category` WHERE  active=1";
			 pst=con.prepareStatement(sql);
			 result=pst.executeQuery();
			 while(result.next()) 
			 {
				 CategoryBean category=new CategoryBean();
				 category.setCategoryId(result.getInt(1));
				
				 category.setParentId(result.getInt(2));
				 System.out.println("id="+category.getParentId());
				 category.setName(result.getString(3));
				 category.setCreatedBy(result.getString(4));
				 category.setCreatedDate(result.getString(5));
				 category.setModifiedBy(result.getString(6));
				 category.setModifiedDate(result.getString(7));
				 categoryList.add(category);
			 }
			 return categoryList;
		 }catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
		}
		 return null;
	}
	@Override
	public CategoryBean getCategoryByParentId(int parentId) {
		 try {
			 category=new CategoryBean();
			 con=DbConnection.getConnection();
			 sql="SELECT * FROM `category` WHERE category_id=? and active=1";
			 pst=con.prepareStatement(sql);
			 pst.setInt(1, parentId);
			 result=pst.executeQuery();
			 while(result.next()) {
				 
				 category.setCategoryId(result.getInt(1));
				 category.setParentId(result.getInt(2));
				 category.setName(result.getString(3));
				 
				 category.setCreatedBy(result.getString(4));
				 category.setCreatedDate(result.getString(5));
				 category.setModifiedBy(result.getString(6));
				 category.setModifiedDate(result.getString(7));
				 
			 }
			 return category;
		 }catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
		}
		 return null;
	}
	@Override
	public int deleteCategory(CategoryBean category) {
		// TODO Auto-generated method stub
		try {
			con=DbConnection.getConnection();
			sql="update category set active=0, modified_by=?,modified_date=? where category_id=?";
			pst=con.prepareStatement(sql);
			pst.setString(1, category.getModifiedBy());
			pst.setString(2, category.getModifiedDate());
			pst.setInt(3, category.getCategoryId());
			return pst.executeUpdate();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public int updateCategory(CategoryBean category) {
		try {
			con=DbConnection.getConnection();
			if(category.getParentId()!=0) {
			sql="update category set parent_id=?,name=? ,modified_by=?,modified_date=? where category_id=?";
			pst=con.prepareStatement(sql);
			pst.setInt(1, category.getParentId());
			pst.setString(2, category.getName());
			pst.setString(3, category.getModifiedBy());
			pst.setString(4, category.getModifiedDate());
			pst.setInt(5, category.getCategoryId());
			 
			}
			else {
				sql="update category set name=?,created_by=?,created_date=?,modified_by=?,modified_date=? where category_id=?";
				pst=con.prepareStatement(sql);
				pst.setString(1, category.getName());
				pst.setString(2, category.getCreatedBy());
				pst.setString(3, category.getCreatedDate());
				pst.setString(4, category.getModifiedBy());
				pst.setString(5, category.getModifiedDate());
				pst.setInt(6, category.getCategoryId());
			}
			 
			row=pst.executeUpdate();
			System.out.println(pst.toString());
			return row;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return 0;
	}
}
