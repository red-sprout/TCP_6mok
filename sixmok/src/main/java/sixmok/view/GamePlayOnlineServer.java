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
import sixmok.common.Decoration;
import sixmok.common.Dol;
import sixmok.model.vo.Nowuser;
import sixmok.model.vo.Room;

public class GamePlayOnlineServer implements GamePlay {
	
	private Scanner sc = new Scanner(System.in);
	
    @Override
	public int[] play(Nowuser user, Room room) {
    	System.out.println(Decoration.title("온라인"));
    	
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

			initialTurn(user, br, pw);
			gameAfterInitialTurn(user, br, pw);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				pw.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    	
    	return new int[] {user.getHistory().getWin(), user.getHistory().getDraw(), user.getHistory().getLose()};
    }
    
    @Override
	public void initialTurn(Nowuser user, BufferedReader br, PrintWriter pw) throws IOException {
		game.init();
		
		String opponent = br.readLine();
		System.out.println("\n" + opponent);
		pw.println("맞대결 상대 : " + user.toString());
		pw.flush();
		
		System.out.println("\n상대가 첫 수를 두는 중입니다.");
		
		String msgFirst = br.readLine();
		
		game.place(msgFirst.charAt(0) - 'A', msgFirst.charAt(1) - 'a', Dol.BLACK);
		BoardView.print(Board.getBoard(), System.out);
	}

	@Override
	public void gameAfterInitialTurn(Nowuser user, BufferedReader br, PrintWriter pw) throws IOException {
		while(true) {
			myTurn(pw);
			
			if(game.isSixMok(Dol.WHITE)) {
				win(user);
				break;
			}
			
			opponentTurn(br);
			
			if(game.isSixMok(Dol.BLACK)) {
				lose(user);
				break;
			}
		}
	}
	
	@Override
	public void myTurn(PrintWriter pw) {
		String send;
		for(int i = 0; i < 2; i++) {
			send = checkPosition(i);
			pw.println(send);
			pw.flush();
		}
		
		BoardView.print(Board.getBoard(), System.out);
	}

	@Override
	public void opponentTurn(BufferedReader br) throws IOException {
		int row;
		int col;
		String msg;
		System.out.println("\n상대 턴입니다. 잠시 기다려주시기 바랍니다.");
		
		for(int i = 0; i < 2; i++) {
			msg = br.readLine();
			row = msg.charAt(0) - 'A';
			col = msg.charAt(1) - 'a';
			game.place(row, col, Dol.BLACK);
		}
		
		BoardView.print(Board.getBoard(), System.out);
	}
	
	@Override
	public void win(Nowuser user) {
		BoardView.print(game.getBoard(), System.out);
		System.out.println("\n" + Decoration.event("당신의 승리!!"));
		user.getHistory().setWin(user.getHistory().getWin() + 1);
	}

	@Override
	public void lose(Nowuser user) {
		BoardView.print(game.getBoard(), System.out);
		System.out.println("\n당신의 패배...");
		user.getHistory().setLose(user.getHistory().getLose() + 1);
	}
	
	@Override
	public String checkPosition(int i) {
		int row = 0;
		int col = 0;
		boolean isValid = false;
		String send = null;
		
		while(!isValid) {
			System.out.print("\n당신의 " + (i + 1) + "번째 차례입니다. " + INPUT_MESSAGE);
			send = sc.nextLine();
			
			if(send.length() != 2) {
				MessageView.displayFail("잘못 입력하셨습니다. 다시 입력해주세요.");
				continue;
			}
			
			row = send.charAt(0) - 'A';
			col = send.charAt(1) - 'a';
			
			if(row < 0 || row >= 19 || col < 0 || col >= 19) {
				MessageView.displayFail("잘못 입력하셨습니다. 다시 입력해주세요.");
				continue;
			}
			
			if(!game.place(row, col, Dol.WHITE)) {
				MessageView.displayFail("이미 착수된 곳입니다. 다시 입력해주세요.");
				continue;
			}
			
			isValid = true;
		}
		
		return send;
	}
}
