package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.entites.User;

public class UserDao {
	private Connection conn;

	public UserDao(Connection conn) {
		super();
		this.conn = conn;
	}

	public boolean UserRegister(User us) {
		boolean f = false;
		try {
			String sql = "insert into user(name, email, password) Values(?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, us.getName());
			pstmt.setString(2, us.getEmail());
			pstmt.setString(3, us.getPassword());
			pstmt.executeUpdate();
			f = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	public User getLogin(String email, String password) {
		User us = null;
		try {
			
			String query = "select * from user where email = ? and password = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				us = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return us;
	}
}
