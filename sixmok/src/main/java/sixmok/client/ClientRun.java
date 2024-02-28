package sixmok.client;

import sixmok.domain.Dol;
import sixmok.service.GameService;
import sixmok.service.InputService;

public class ClientRun {
	public static void main(String[] args) {
		GameService game = new GameService(new char[19][19]);
		int row, col;
		Dol dol = Dol.BLACK;
		
		game.init();
		while(true) {
			print(game.getBoard());
			
			String[] position = InputService.input.nextLine().split(" ");
			
			if(position[0].equals("exit")) {
				System.out.println(switchDol(dol).getDol() + " 승리!!");
				break;
			}
			
			row = Integer.parseInt(position[0]);
			col = Integer.parseInt(position[1]);
			
			game.place(row, col, dol);
			
			if(game.isSixMok(dol)) {
				print(game.getBoard());
				System.out.println(dol.getDol() + " 승리!!");
				break;
			}
			
			dol = switchDol(dol);
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
	
	public static void print(char[][] board) {
		for(char[] cArr : board) {
			for(char c : cArr) {
				System.out.print(c + " ");
			}
			System.out.println();
		} // View에서 처리
	}
}
