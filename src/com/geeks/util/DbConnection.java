package com.geeks.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	private DbConnection() {}
	static private Connection con;
	
	public static Connection getConnection() {
		try {
			if(con==null) {
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/foodiee_soft_jsp", "root", "");
				System.out.println("Connection Created");
			}
			return con;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String args[]) {
		getConnection();
	}
}
