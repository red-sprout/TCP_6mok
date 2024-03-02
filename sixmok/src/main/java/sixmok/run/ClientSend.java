package sixmok.run;

import java.net.Socket;

public class ClientSend implements Runnable {
	private Socket socket;
	private char[][] board;
	
	private static final int size = 19;
	
	public ClientSend(Socket socket) {
		this.socket = socket;
		this.board = new char[size][size];
	}

	@Override
	public void run() {
		
	}
}
