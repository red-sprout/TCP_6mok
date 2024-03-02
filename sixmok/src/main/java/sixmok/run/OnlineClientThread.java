package sixmok.run;

import java.io.IOException;
import java.io.ObjectInputStream;

import sixmok.view.BoardView;

public class OnlineClientThread implements Runnable {
	ObjectInputStream ois;
	
	public OnlineClientThread(ObjectInputStream ois) {
		this.ois = ois;
	}

	@Override
	public void run() {
		try {
			char[][] board = null;
			while((board = (char[][])ois.readObject()) != null){
				BoardView.print(board);
			}
		} catch (IOException | ClassNotFoundException e) {
			
		}
	}

}
