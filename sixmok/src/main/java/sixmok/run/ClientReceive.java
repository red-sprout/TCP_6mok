package sixmok.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientReceive implements Runnable{
	private Socket socket;
	
	public ClientReceive(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
			while(true) {
				
			}
		} catch (IOException e) {
		 	
	 	}
	}
}
