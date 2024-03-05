package sixmok.service;

import java.sql.Connection;

import sixmok.common.template.JDBCTemplate;
import sixmok.model.dao.HistoryDao;
import sixmok.model.vo.History;

public class HistoryService {
	public History selectHistoryByUserId(String userId) {
		Connection connection = JDBCTemplate.getConnection();
		History history = new HistoryDao().selectHistoryByUserId(connection, userId);
		
		JDBCTemplate.close(connection);
		
		return history;
	}
	
	public int updateHistory(History history) {
		Connection connection = JDBCTemplate.getConnection();
		int result = new HistoryDao().updateHistory(connection, history);
		
		if(result > 0) {
			JDBCTemplate.commit(connection);
		} else {
			JDBCTemplate.rollback(connection);
		}
		
		JDBCTemplate.close(connection);
		
		return result;
	}
}
