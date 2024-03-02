package sixmok.common;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Board implements Serializable{
	private static int order = 0;
	private static char[][] board = new char[19][19];
	private static ArrayList<Integer> rowList = new ArrayList<>();
	private static ArrayList<Integer> colList = new ArrayList<>();

	public static char[][] getBoard() {
		return board;
	}

	public static void setBoard(int row, int col, Dol dol) {
		board[row][col] = dol.getDol();
	}

	public static ArrayList<Integer> getRowList() {
		return rowList;
	}

	public static void setRowList(int row) {
		rowList.add(row);
	}

	public static ArrayList<Integer> getColList() {
		return colList;
	}

	public static void setColList(int col) {
		colList.add(col);
	}
	
	public static int getOrder() {
		return order++;
	}
}
