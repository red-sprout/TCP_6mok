package sixmok.view;

import java.io.OutputStream;
import java.io.PrintWriter;

import sixmok.common.Dol;

public class BoardView {
	public static void print(char[][] board, OutputStream out) {
		PrintWriter pw = new PrintWriter(out);
		pw.write("\n");
		for(int i = 0; i <= board[0].length; i++) {
			if(i == 0) {
				pw.write("  ");
				continue;
			}
			pw.write((char)('a' + i - 1) + " ");
		}
		
		pw.write("\n");
		for(int i = 0; i < board.length; i++) {
			pw.write((char)('A' + i) + " ");
			for(int j = 0; j < board[i].length; j++) {
				pw.write(board[i][j] + " ");
			}
			pw.write("\n");
		}
		
		pw.flush();
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
