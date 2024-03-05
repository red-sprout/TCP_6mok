package sixmok.service;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPService {
	public String getIP() {
		String serverIP = null;
		try {
			serverIP = InetAddress.getLocalHost().getHostAddress(); // 본인의 IP로 설정
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return serverIP;
	}

	public int getPort(String serverIP) {
		int port = 1000; // 포트는 1000으로 우선 설정
		for(int i = 1000; i <= 65535; i++) {
			if(availablePort(serverIP, i)) {
				port = i;
				break;
			}
		}
		return port;
	}
	
	public boolean availablePort(String host, int port) {
		try {
		    (new Socket(host, port)).close();
		    return false;
		} catch(Exception e) {
		    return true;
		}
	}
}
