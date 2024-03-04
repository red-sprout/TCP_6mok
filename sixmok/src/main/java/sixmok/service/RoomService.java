package sixmok.service;

import java.sql.Connection;
import java.util.ArrayList;

import sixmok.common.template.JDBCTemplate;
import sixmok.model.dao.RoomDao;
import sixmok.model.vo.Room;

public class RoomService {	
	public ArrayList<Room> selectRoom() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Room> list = new RoomDao().selectRoom(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	public Room selectOneRoom(int row) {
		Connection conn = JDBCTemplate.getConnection();
		Room room = new RoomDao().selectOneRoom(conn, row);
		
		JDBCTemplate.close(conn);
		
		return room;
	}
	
	public int insertRoom(Room room) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new RoomDao().insertRoom(conn, room);
		
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		return result;
	}
	
	public int deleteRoom(String userId) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new RoomDao().deleteRoom(conn, userId);
		
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		return result;
	}
}
