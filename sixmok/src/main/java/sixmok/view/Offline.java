package sixmok.view;

import java.util.Scanner;

import sixmok.common.Dol;
import sixmok.service.GameService;

public class Offline {
	private boolean exit = false;
	private Scanner sc = new Scanner(System.in);
	private GameService game = new GameService();
	
	private static final String INPUT_MESSAGE = "착수할 곳을 입력하세요(예시 : Aa) : ";
	
	public void play() {
		int row1 = -1;
		int col1 = -1;
		int row2 = -1;
		int col2 = -1;
		
		boolean isFirst = true;
		String position = null;
		Dol dol = Dol.BLACK;
		
		game.init();
		
		System.out.println("\n===오프라인 육목 플레이===");
		
		do {
			BoardView.print(game.getBoard(), System.out);
			System.out.printf("\n첫 수입니다. %s", INPUT_MESSAGE);
			position = sc.nextLine();
		
			row1 = position.charAt(0) - 'A';
			col1 = position.charAt(1) - 'a';
		
			if(row1 < 0 || row1 >= 19 || col1 < 0 || col1 >= 19) {
				MessageView.displayFail("잘못 입력하셨습니다. 다시 입력해주세요.");
				continue;
			}
			
			isFirst = false;
		} while (isFirst);
		
		game.place(row1, col1, dol);
		dol = BoardView.switchDol(dol);
		
		while(!exit) {
			BoardView.print(game.getBoard(), System.out);

			placeDol(row1, col1, dol, 1);
			if(exit) break;
			
			placeDol(row2, col2, dol, 2);
			if(exit) break;
			
			dol = BoardView.switchDol(dol);
		}
	}

	public void placeDol(int row1, int col1, Dol dol, int order) {
		String position;
		boolean flag = true;
		
		while(flag) {
			System.out.printf("\n%c 의 %d번째 차례입니다. %s", dol.getDol(), order, INPUT_MESSAGE);
			position = sc.nextLine();
			
			if(position.equals("exit")) {
				System.out.println(BoardView.switchDol(dol).getDol() + " 승리!!");
				exit = true;
				break;
			}
			
			row1 = position.charAt(0) - 'A';
			col1 = position.charAt(1) - 'a';
			
			if(row1 < 0 || row1 >= 19 || col1 < 0 || col1 >= 19) {
				MessageView.displayFail("잘못 입력하셨습니다. 다시 입력해주세요.");
				continue;
			}
			
			if(!game.place(row1, col1, dol)) {
				MessageView.displayFail("이미 착수된 곳입니다. 다시 입력해주세요.");
				continue;
			}
			
			flag = false;
		}
		
		if(game.isSixMok(dol)) {
			BoardView.print(game.getBoard(), System.out);
			System.out.println(dol.getDol() + " 승리!!");
			exit = true;
		}
	}
}
