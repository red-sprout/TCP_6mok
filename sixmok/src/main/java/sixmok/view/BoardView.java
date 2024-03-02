package sixmok.view;

import sixmok.common.Dol;

public class BoardView {
	public static void print(char[][] board) {
		System.out.println();
		for(int i = 0; i <= board[0].length; i++) {
			if(i == 0) {
				System.out.print("  ");
				continue;
			}
			System.out.print((char)('a' + i - 1) + " ");
		}
		
		System.out.println();
		for(int i = 0; i < board.length; i++) {
			System.out.print((char)('A' + i) + " ");
			for(int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static Dol switchDol(Dol dol) {
		if(dol == Dol.BLACK) {
			dol = Dol.WHITE;
		} else {
			dol = Dol.BLACK;
		}
		return dol;
	}
}
