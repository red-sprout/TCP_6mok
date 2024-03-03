package sixmok.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import sixmok.common.Board;
import sixmok.common.Dol;
import sixmok.model.vo.Nowuser;
import sixmok.service.GameService;

public class OnlineClient {
	private GameService game = new GameService();
	private Scanner sc = new Scanner(System.in);
	
	private Nowuser user;
	
	public OnlineClient(Nowuser user) {
		this.user = user;
	}

	public int[] play() {	
    	BufferedReader br = null;
    	PrintWriter pw = null;
    	
    	int port = 8000;
    	String serverIP = "localhost"; // 서버주소
    	Socket socket;
    	
    	try {
			socket = new Socket(serverIP, port);
			
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(socket.getOutputStream());

			game.init();
			while(true) {
				System.out.print("좌표 입력 : ");
				String send = sc.nextLine();
				pw.println(send);
				pw.flush();
				
				String[] position = send.split(" ");
				
				game.place(position[0].charAt(0) - 'A', position[0].charAt(1) - 'a', Dol.BLACK);
				if(position.length == 2) {
					game.place(position[1].charAt(0) - 'A', position[1].charAt(1) - 'a', Dol.BLACK);
				}
				
				BoardView.print(Board.getBoard(), System.out);
				
				if(game.isSixMok(Dol.BLACK)) {
					BoardView.print(game.getBoard(), System.out);
					System.out.println("승리!!");
					user.getHistory().setWin(user.getHistory().getWin() + 1);
					break;
				}
				
				String msg = br.readLine();
				position = msg.split(" ");
				
				game.place(position[0].charAt(0) - 'A', position[0].charAt(1) - 'a', Dol.WHITE);
				if(position.length == 2) {
					game.place(position[1].charAt(0) - 'A', position[1].charAt(1) - 'a', Dol.WHITE);
				}
				
				BoardView.print(Board.getBoard(), System.out);
				
				if(game.isSixMok(Dol.WHITE)) {
					BoardView.print(game.getBoard(), System.out);
					System.out.println("패배...");
					user.getHistory().setLose(user.getHistory().getLose() + 1);
					break;
				}
			}
		} catch (IOException e) {

		} finally {
			try {
				for(int i = 0; i < 2; i++) {
					pw.close();
					br.close();
				}
			} catch (IOException e) {

			}
		}
    	
    	return new int[] {user.getHistory().getWin(), user.getHistory().getDraw(), user.getHistory().getLose()};
	}
}
