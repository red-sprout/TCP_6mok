package sixmok.run;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import sixmok.common.Board;

public class OnlineClient {
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		String id = "시험 ID";
		String name = "시험 이름";
		try {
			Socket socket = new Socket("127.0.0.1", 9999);
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			
			oos.writeObject(id);
			oos.flush();
			
			oos.writeObject(name);
			oos.flush();
			
			Thread thread = new Thread(new OnlineClientThread(ois));
			thread.start();
			
			char[][] board = Board.getBoard();
			while(true) {
				String position = sc.nextLine();
				
				int row1 = position.charAt(0) - 'A';
				int col1 = position.charAt(1) - 'a';
				
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
