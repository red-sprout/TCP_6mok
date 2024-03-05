package sixmok.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sixmok.common.template.JDBCTemplate;
import sixmok.model.vo.Gameuser;

public class GameuserDao {
	public Gameuser loginGameuser(Connection connection, String userId, String userPwd) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Gameuser user = null;
		String sql = "SELECT * FROM GAMEUSER WHERE USERID = ? AND USERPWD = ?";
		
		try {
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				user = new Gameuser();
				user.setUserNo(rset.getInt("USERNO"));
				user.setUserId(rset.getString("USERID"));
				user.setUserPwd(rset.getString("USERPWD"));
				user.setUserName(rset.getString("USERNAME"));
				user.setPhone(rset.getString("PHONE"));
				user.setEnrollDate(rset.getDate("ENROLLDATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return user;
	}
	
	public int insertGameuser(Connection connection, Gameuser user) {
		int result = 0;

		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO GAMEUSER VALUES(SEQ_USERNO.NEXTVAL, ?, ?, ?, ?, DEFAULT)";
		
		try {
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPwd());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getPhone());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	public int updateGameuser(Connection connection, Gameuser user) {
		int result = 0;

		PreparedStatement pstmt = null;
		
		String sql = "UPDATE GAMEUSER SET "
				+ "USERPWD = ?, "
				+ "USERNAME = ?, "
				+ "PHONE = ? "
				+ "WHERE USERID = ?";
		
		try {
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, user.getUserPwd());
			pstmt.setString(2, user.getUserName());
			pstmt.setString(3, user.getPhone());
			pstmt.setString(4, user.getUserId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	public int deleteGameuser(Connection connection, String userId) {
		int result = 0;

		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM HISTORY WHERE USERID = ?";
		
		try {
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
}
