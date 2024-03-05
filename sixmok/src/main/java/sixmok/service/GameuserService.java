package sixmok.service;

import java.sql.Connection;

import sixmok.common.template.JDBCTemplate;
import sixmok.model.dao.GameuserDao;
import sixmok.model.vo.Gameuser;

public class GameuserService {
	public Gameuser loginGameuser(String userId, String userPwd) {
		Connection connection = JDBCTemplate.getConnection();
		Gameuser user = new GameuserDao().loginGameuser(connection, userId, userPwd);
		
		JDBCTemplate.close(connection);
		
		return user;
	}
	
	public int insertGameuser(Gameuser user) {
		Connection connection = JDBCTemplate.getConnection();
		int result = new GameuserDao().insertGameuser(connection, user);
	
		if(result > 0) {
			JDBCTemplate.commit(connection);
		} else {
			JDBCTemplate.rollback(connection);
		}
		
		JDBCTemplate.close(connection);
		
		return result;
	}
	
	public int updateGameuser(Gameuser user) {
		Connection connection = JDBCTemplate.getConnection();
		int result = new GameuserDao().updateGameuser(connection, user);
	
		if(result > 0) {
			JDBCTemplate.commit(connection);
		} else {
			JDBCTemplate.rollback(connection);
		}
		
		JDBCTemplate.close(connection);
		
		return result;
	}
	public int deleteGameuser(String userId) {
		Connection connection = JDBCTemplate.getConnection();
		int result = new GameuserDao().deleteGameuser(connection, userId);
	
		if(result > 0) {
			JDBCTemplate.commit(connection);
		} else {
			JDBCTemplate.rollback(connection);
		}
		
		JDBCTemplate.close(connection);
		
		return result;
	}
}
