package sixmok.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import sixmok.common.Board;
import sixmok.common.Dol;
import sixmok.service.GameService;

public class OnlineServer {
	public GameService game = new GameService();
	private Scanner sc = new Scanner(System.in);
	
    public void play() {
    	BufferedReader br = null;
    	PrintWriter pw = null;
    	
    	int port = 8000;
    	
    	ServerSocket server;
    	Socket socket;
    	try {
    		server = new ServerSocket(port);
    		socket = server.accept();
			
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(socket.getOutputStream());

			game.init();
			while(true) {
				String msg = br.readLine();
				String[] position = msg.split(" ");
				
				game.place(position[0].charAt(0) - 'A', position[0].charAt(1) - 'a', Dol.BLACK);
				if(position.length == 2) {
					game.place(position[1].charAt(0) - 'A', position[1].charAt(1) - 'a', Dol.BLACK);
				}
				
				BoardView.print(Board.getBoard(), System.out);
				
				System.out.print("좌표 입력 : ");
				String send = sc.nextLine();
				pw.println(send);
				pw.flush();
				
				position = send.split(" ");
				game.place(position[0].charAt(0) - 'A', position[0].charAt(1) - 'a', Dol.WHITE);
				if(position.length == 2) {
					game.place(position[1].charAt(0) - 'A', position[1].charAt(1) - 'a', Dol.WHITE);
				}
				
				BoardView.print(Board.getBoard(), System.out);
			}
		} catch (IOException e) {
			
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
    }
}
