package sixmok.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import sixmok.common.template.JDBCTemplate;
import sixmok.model.vo.Room;

public class RoomDao {
	public ArrayList<Room> selectRoom(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		
		ArrayList<Room> list = new ArrayList<>();
		String sql = "SELECT * FROM ROOM";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			
			while (rset.next()) {
				Room room = new Room();
				
				room.setRoomName(rset.getString("ROOMNAME"));
				room.setUserName(rset.getString("USERNAME"));
				room.setUserId(rset.getString("USERID"));
				room.setUserIP(rset.getString("USERIP"));
				room.setUserPort(rset.getInt("USERPORT"));
				
				list.add(room);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		
		return list;
	}
	
	public Room selectOneRoom(Connection conn, int row) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Room room = null;
		String sql = "SELECT * "
				+ "FROM (SELECT ROWNUM R, ROOMNAME, USERNAME, USERID, USERIP, USERPORT "
				+ "FROM ROOM) "
				+ "WHERE R = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, row);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				room = new Room();
				
				room.setRoomName(rset.getString("ROOMNAME"));
				room.setUserName(rset.getString("USERNAME"));
				room.setUserId(rset.getString("USERID"));
				room.setUserIP(rset.getString("USERIP"));
				room.setUserPort(rset.getInt("USERPORT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return room;
	}
	
	public int insertRoom(Connection conn, Room room) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		String sql = "INSERT INTO ROOM VALUES(?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, room.getRoomName());
			pstmt.setString(2, room.getUserName());
			pstmt.setString(3, room.getUserId());
			pstmt.setString(4, room.getUserIP());
			pstmt.setInt(5, room.getUserPort());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	public int deleteRoom(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		String sql = "DELETE FROM ROOM WHERE USERID = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
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
