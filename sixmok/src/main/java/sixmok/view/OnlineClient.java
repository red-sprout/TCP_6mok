package sixmok.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import sixmok.common.Board;
import sixmok.common.Dol;
import sixmok.model.vo.Nowuser;
import sixmok.model.vo.Room;
import sixmok.service.GameService;

public class OnlineClient {
	private GameService game = new GameService();
	private Scanner sc = new Scanner(System.in);
	
	private static final String INPUT_MESSAGE = "착수할 곳을 입력하세요(예시 : Aa) : ";

	public int[] play(Nowuser user, Room room) {	
    	BufferedReader br = null;
    	PrintWriter pw = null;
    	
    	int port = room.getUserPort();
    	String serverIP = room.getUserIP();
    	Socket socket;
    	
    	try {
			socket = new Socket(serverIP, port);
			
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"));

			game.init();
			
			pw.println("맞대결 상대 : " + user.toString());
			pw.flush();
			String opponent = br.readLine();
			System.out.println("\n" + opponent);
			
			BoardView.print(Board.getBoard(), System.out);
			
			System.out.print("\n첫수입니다. " + INPUT_MESSAGE);
			String sendFirst = sc.nextLine();
			pw.println(sendFirst);
			pw.flush();
			
			game.place(sendFirst.charAt(0) - 'A', sendFirst.charAt(1) - 'a', Dol.BLACK);
			BoardView.print(Board.getBoard(), System.out);
			
			String[] position = new String[2];
			while(true) {
				System.out.println("\n==상대턴==");
				
				for(int i = 1; i <= 2; i++) {
					String msg = br.readLine();
					position[i] = msg;
				}
				
				place(position, Dol.WHITE);
				
				BoardView.print(Board.getBoard(), System.out);
				
				if(game.isSixMok(Dol.WHITE)) {
					BoardView.print(game.getBoard(), System.out);
					System.out.println("\n패배...");
					user.getHistory().setLose(user.getHistory().getLose() + 1);
					break;
				}
				
				for(int i = 1; i <= 2; i++) {
					System.out.print("\n당신의 " + i + "번째 차례입니다. " + INPUT_MESSAGE);
					String send = sc.nextLine();
					pw.println(send);
					pw.flush();
					position[i] = send;
				}
				
				place(position, Dol.BLACK);
				
				BoardView.print(Board.getBoard(), System.out);
				
				if(game.isSixMok(Dol.BLACK)) {
					BoardView.print(game.getBoard(), System.out);
					System.out.println("\n승리!!");
					user.getHistory().setWin(user.getHistory().getWin() + 1);
					break;
				}
			}
		} catch (Exception e) {
//			e.printStackTrace();
		} finally {
			try {
				pw.close();
				br.close();
			} catch (IOException e) {

			}
		}
    	
    	return new int[] {user.getHistory().getWin(), user.getHistory().getDraw(), user.getHistory().getLose()};
	}
	
	public void place(String[] position, Dol dol) {
		for(int j = 0; j < 2; j++) {
			game.place(position[0].charAt(0) - 'A', position[0].charAt(1) - 'a', dol);
			game.place(position[1].charAt(0) - 'A', position[1].charAt(1) - 'a', dol);
		}
	}
}
