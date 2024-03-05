package sixmok;

import org.junit.jupiter.api.Test;

import sixmok.service.TCPService;

public class ApplicationTest {
	@Test
	void checkPort() {
		String ip = new TCPService().getIP();
		int port = new TCPService().getPort(ip);
		System.out.println(ip);
		System.out.println(port);
	}
}
