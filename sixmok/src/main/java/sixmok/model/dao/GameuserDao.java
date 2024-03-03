package sixmok.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sixmok.model.vo.Gameuser;
import sixmok.model.vo.History;

public class GameuserDao {
	public Gameuser loginGameuser(String userId, String userPwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Gameuser user = null;
		String sql = "SELECT * FROM GAMEUSER WHERE USERID = ? AND USERPWD = ?";
		
		try {
			conn = oracleConnection();
			pstmt = conn.prepareStatement(sql);
			
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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rset);
		}
		
		return user;
	}
	
	public History searchHistory(String userId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		History history = null;
		String sql = "SELECT * FROM HISTORY WHERE USERID = ?";
		
		try {
			conn = oracleConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				history = new History();
				history.setUserId(rset.getString("USERID"));
				history.setWin(rset.getInt("WIN"));
				history.setDraw(rset.getInt("DRAW"));
				history.setLose(rset.getInt("LOSE"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rset);
		}
		
		return history;
	}
	
	public int insertGameuser(Gameuser user) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO GAMEUSER VALUES(SEQ_USERNO.NEXTVAL, ?, ?, ?, ?, DEFAULT)";
		
		try {
			conn = oracleConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPwd());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getPhone());
			
			result = pstmt.executeUpdate();
			
			commit(result, conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		
		return result;
	}
	
	public int updateGameuser(Gameuser user) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE GAMEUSER SET "
				+ "USERPWD = ?, "
				+ "USERNAME = ?, "
				+ "PHONE = ? "
				+ "WHERE USERID = ?";
		
		try {
			conn = oracleConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user.getUserPwd());
			pstmt.setString(2, user.getUserName());
			pstmt.setString(3, user.getPhone());
			pstmt.setString(4, user.getUserId());
			
			result = pstmt.executeUpdate();
			
			commit(result, conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		
		return result;
	}
	
	public int deleteGameuser(String userId) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM HISTORY WHERE USERID = ?";
		
		try {
			conn = oracleConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			result = pstmt.executeUpdate();
			
			commit(result, conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		
		return result;
	}
	
	public int updateHistory(String userId, int win, int draw, int lose) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE HISTORY SET "
				+ "WIN = ?, "
				+ "DRAW = ?, "
				+ "LOSE = ? "
				+ "WHERE USERID = ?";
		
		try {
			conn = oracleConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, win);
			pstmt.setInt(2, draw);
			pstmt.setInt(3, lose);
			pstmt.setString(4, userId);
			
			result = pstmt.executeUpdate();
			
			commit(result, conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		
		return result;
	}

	public Connection oracleConnection() throws ClassNotFoundException, SQLException {
		Connection conn;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
		return conn;
	}
	
	public void commit(int result, Connection conn) throws SQLException {
		if (result > 0) {
			conn.commit();
		} else {
			conn.rollback();
		}
	}
	
	public void close(Connection conn, PreparedStatement pstmt) {
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close(Connection conn, PreparedStatement pstmt, ResultSet rset) {
		try {
			rset.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
