package sixmok.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import sixmok.common.Board;
import sixmok.common.Dol;
import sixmok.model.vo.Nowuser;
import sixmok.model.vo.Room;
import sixmok.service.GameService;

public class OnlineServer {
	public GameService game = new GameService();
	private Scanner sc = new Scanner(System.in);
	
	private static final String INPUT_MESSAGE = "착수할 곳을 입력하세요(예시 : Aa) : ";
	
    public int[] play(Nowuser user, Room room) {
    	BufferedReader br = null;
    	PrintWriter pw = null;
    	
    	int port = room.getUserPort();
    	
    	ServerSocket server;
    	Socket socket;
    	try {
    		server = new ServerSocket(port);
    		socket = server.accept();
			
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"));

			game.init();
			
			String opponent = br.readLine();
			System.out.println("\n" + opponent);
			pw.println("맞대결 상대 : " + user.toString());
			pw.flush();
			
			System.out.println("\n상대가 첫 수를 두는 중입니다.");
			
			String msgFirst = br.readLine();
			
			game.place(msgFirst.charAt(0) - 'A', msgFirst.charAt(1) - 'a', Dol.BLACK);
			BoardView.print(Board.getBoard(), System.out);
			
			String[] position = new String[2];
			while(true) {
				for(int i = 1; i <= 2; i++) {
					System.out.print("\n당신의 " + i + "번째 차례입니다. " + INPUT_MESSAGE);
					String send = sc.nextLine();
					pw.println(send);
					pw.flush();
					position[i] = send;
				}
				
				place(position, Dol.WHITE);
				
				BoardView.print(Board.getBoard(), System.out);
				
				if(game.isSixMok(Dol.WHITE)) {
					BoardView.print(game.getBoard(), System.out);
					System.out.println("\n승리!!");
					user.getHistory().setWin(user.getHistory().getWin() + 1);
					break;
				}
				
				System.out.println("\n==상대턴==");
				
				for(int i = 1; i <= 2; i++) {
					String msg = br.readLine();
					position[i] = msg;
				}
				
				place(position, Dol.BLACK);
				
				BoardView.print(Board.getBoard(), System.out);
				
				if(game.isSixMok(Dol.BLACK)) {
					BoardView.print(game.getBoard(), System.out);
					System.out.println("\n패배...");
					user.getHistory().setLose(user.getHistory().getLose() + 1);
					break;
				}
			}
		} catch (Exception e) {
//			e.printStackTrace();
		} finally {
			try {
				for(int i = 0; i < 2; i++) {
					pw.close();
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
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
