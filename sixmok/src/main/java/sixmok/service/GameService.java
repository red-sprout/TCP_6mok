package sixmok.service;

import sixmok.domain.Dol;

public class GameService {
	private char[][] board;
	private int maxDolLength;
	
	private int dr = 0;
	private int dc = 0;
	
	private static final int MAX_VALUE = 19;
	
	public GameService(char[][] board) {
		this.board = board;
		this.maxDolLength = 0;
	}
	
	public void dfs(int now, int row, int col, Dol dol) {
		if(row < 0 || col < 0 || row >= MAX_VALUE || col >= MAX_VALUE) {
			maxDolLength = Math.max(now, maxDolLength);
			return;
		}
		
		if(board[row][col] != dol.getDol()) {
			maxDolLength = Math.max(now, maxDolLength);
			return;
		}
		
		int nextRow = row;
		int nextCol = col;
		
		if(now == 0) {
			int[] drArr = {1, 0, -1, -1, -1, 0, 1, 1};
			int[] dcArr = {1, 1, 1, 0, -1, -1, -1, 0};
			
			for(int i = 0; i < 8; i++) {
				dr = drArr[i];
				dc = dcArr[i];
				
				nextRow += dr;
				nextCol += dc;
				
				dfs(1, nextRow, nextCol, dol);
				
				nextRow -= dr;
				nextCol -= dc;
			}
		}
		
		nextRow += dr;
		nextCol += dc;
		
		int next = now + 1;
		dfs(next, nextRow, nextCol, dol);
	}
	
	public boolean isSixMok(Dol dol) {
		for(int i = 0; i < MAX_VALUE; i++) {
			for(int j = 0; j < MAX_VALUE; j++) {
				dfs(0, i, j, dol);
			}
		}
		
		boolean result = (maxDolLength == 6);
		maxDolLength = 0;
		return result;
	}
}
