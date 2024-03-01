package sixmok.view;

import java.util.Scanner;

import sixmok.common.Dol;
import sixmok.service.GameService;

public class Offline {
	private Scanner sc = new Scanner(System.in);
	
	public void play() {
		GameService game = new GameService(new char[19][19]);
		int row, col;
		boolean isFirst = true;
		Dol dol = Dol.BLACK;
		
		game.init();
		while(true) {
			Board.print(game.getBoard());
			
			String[] position = sc.nextLine().split(" ");
			
			if(position[0].equals("exit")) {
				System.out.println(switchDol(dol).getDol() + " 승리!!");
				break;
			}
			
			row = Integer.parseInt(position[0]);
			col = Integer.parseInt(position[1]);
			
			game.place(row, col, dol);
			
			if(game.isSixMok(dol)) {
				Board.print(game.getBoard());
				System.out.println(dol.getDol() + " 승리!!");
				break;
			}
			
			dol = switchDol(dol);
		}
	}
	
	public Dol switchDol(Dol dol) {
		if(dol == Dol.BLACK) {
			dol = Dol.WHITE;
		} else {
			dol = Dol.BLACK;
		}
		return dol;
	}
}
