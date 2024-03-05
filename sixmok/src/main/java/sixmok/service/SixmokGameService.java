package sixmok.service;

import sixmok.common.Board;
import sixmok.common.Dol;

public class SixmokGameService {
	private int dolLength = 0;
	
	private int dRow = 0;
	private int dCol = 0;
	
	private static final int MAX_VALUE = 19;
	
	public void init() {
		for(int i = 0; i < MAX_VALUE; i++) {
			for(int j = 0; j < MAX_VALUE; j++) {
				Board.setBoard(i, j, Dol.BLANK);
			}
		}
	}
	
	public void searchLength(int nowLength, int row, int col, Dol dol) {
		if(row < 0 || col < 0 || row >= MAX_VALUE || col >= MAX_VALUE) {
			updateLength(nowLength);
			return;
		}
		
		if(Board.getBoard()[row][col] != dol.getDol()) {
			updateLength(nowLength);
			return;
		}
		
		int nextRow = row;
		int nextCol = col;
		int next = nowLength + 1;
		
		if(nowLength == 0) {
			initialSearch(nextRow, nextCol, next, dol);
			return;
		}
		
		nextRow += dRow;
		nextCol += dCol;
		
		searchLength(next, nextRow, nextCol, dol);
	}

	private void initialSearch(int nextRow, int nextCol, int next, Dol dol) {
		int[] dRowArr = {1, 0, -1, -1, -1, 0, 1, 1};
		int[] dColArr = {1, 1, 1, 0, -1, -1, -1, 0};
		
		for(int i = 0; i < 8; i++) {
			dRow = dRowArr[i];
			dCol = dColArr[i];
			
			nextRow += dRow;
			nextCol += dCol;
			
			searchLength(next, nextRow, nextCol, dol);
			
			nextRow -= dRow;
			nextCol -= dCol;
		}
	}
	
	public boolean isSixMok(Dol dol) {
		for(int i = 0; i < MAX_VALUE; i++) {
			for(int j = 0; j < MAX_VALUE; j++) {
				searchLength(0, i, j, dol);
			}
		}
		
		boolean result = (dolLength == 6);
		dolLength = 0;
		return result;
	}
	
	public boolean place(int row, int col, Dol dol) {
		if(Board.getBoard()[row][col] != Dol.BLANK.getDol()) {
			return false;
		}
		Board.setBoard(row, col, dol);
		return true;
	}

	public char[][] getBoard() {
		return Board.getBoard();
	}
	
	public void updateLength(int nowLength) {
		if(nowLength == 6) {
			dolLength = nowLength;
		} else {
			dolLength = Math.max(dolLength, nowLength);
		}
	}
}
