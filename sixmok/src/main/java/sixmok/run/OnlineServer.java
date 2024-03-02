package sixmok.run;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OnlineServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9999);

        List<OnlineServerThread> list = Collections.synchronizedList(new ArrayList<>());
        while (true) {
            Socket socket = serverSocket.accept();  // 클라이언트가 접속한다.
            Thread thread = new Thread(new OnlineServerThread(socket, list));
            thread.start();
        } // while
    } // main
}
