package sixmok.run;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import sixmok.view.BoardView;

public class OnlineServerThread implements Runnable {
	private String id;
	private String name;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private Socket socket;
	List<OnlineServerThread> list;
	
	public OnlineServerThread(Socket socket, List<OnlineServerThread> list) throws IOException, ClassNotFoundException {
		this.socket = socket;
		this.ois = new ObjectInputStream(socket.getInputStream());
		this.oos = new ObjectOutputStream(socket.getOutputStream());
		this.id = (String)ois.readObject();
		this.name = (String)ois.readObject();
		this.list = list;
		this.list.add(this);
	}
	
	public void send(Object obj) throws IOException {
		oos.writeObject(obj);
		oos.flush();
	}
	
	@Override
	public void run() {
		try {
			List<OnlineServerThread> onlineThreads = new ArrayList<>();
			StringBuilder sb = new StringBuilder();
			
			for(int i = 0; i < this.list.size(); i++) {
				onlineThreads.add(list.get(i));
			}
			
			char[][] board = null;
			
			while((board = (char[][])ois.readObject()) != null) {
				BoardView.print(board);
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			broadcast(name + "(" + id + ")" + "님의 연결이 끊어졌습니다.", false);
			this.list.remove(this);
			try {
				oos.close();
				ois.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void broadcast(String msg, boolean includeMe) {
		List<OnlineServerThread> onlineThreads = new ArrayList<>();
		for(int i = 0; i < this.list.size(); i++) {
			onlineThreads.add(list.get(i));
		}
		
		for(int i = 0; i < onlineThreads.size(); i++) {
			OnlineServerThread ot = onlineThreads.get(i);
			if (!includeMe || ot == this) {
				continue;
			}
			try {
				ot.send(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
