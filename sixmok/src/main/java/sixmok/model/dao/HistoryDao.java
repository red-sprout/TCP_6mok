package sixmok.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import sixmok.common.template.JDBCTemplate;
import sixmok.model.vo.History;

public class HistoryDao {
	private Properties prop = new Properties();
	
	public HistoryDao() {
		try {
			prop.loadFromXML(new FileInputStream(JDBCTemplate.QUERY_XML));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public History selectHistoryByUserId(Connection connection, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		History history = null;
		String sql = prop.getProperty("selectHistoryByUserId");
		
		try {
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				history = new History();
				history.setUserId(rset.getString("USERID"));
				history.setWin(rset.getInt("WIN"));
				history.setDraw(rset.getInt("DRAW"));
				history.setLose(rset.getInt("LOSE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return history;
	}
	
	public int updateHistory(Connection connection, History history) {
		int result = 0;

		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateHistory");
		
		try {
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setInt(1, history.getWin());
			pstmt.setInt(2, history.getDraw());
			pstmt.setInt(3, history.getLose());
			pstmt.setString(4, history.getUserId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
}
