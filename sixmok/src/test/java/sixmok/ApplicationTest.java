package sixmok;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import sixmok.common.Dol;
import sixmok.service.SixmokGameService;
import sixmok.service.TCPService;

public class ApplicationTest {
	@Test
	void checkPort() {
		String ip = new TCPService().getIP();
		int port = new TCPService().getPort(ip);
		System.out.println(ip);
		System.out.println(port);
	}
	
	@Test
	void checkSixMok() {
		SixmokGameService game = new SixmokGameService();
		game.init();
		
		for(int i = 0; i < 7; i++) {
			game.place(0, i, Dol.BLACK);
		}
		
		for(int i = 0; i < 6; i++) {
			game.place(18, i, Dol.WHITE);
		}
		
		for(int i = 0; i < 19; i++) {
			for(int j = 0; j < 19; j++) {
				game.searchLength(0, i, j, Dol.BLACK);
			}
		}
		
		Assertions.assertThat(game.isSixMok(Dol.BLACK)).isEqualTo(false);
		
		for(int i = 0; i < 19; i++) {
			for(int j = 0; j < 19; j++) {
				game.searchLength(0, i, j, Dol.WHITE);
			}
		}
		
		Assertions.assertThat(game.isSixMok(Dol.WHITE)).isEqualTo(true);
	}

	/**
	 * xml 파일을 완전히 새로 만드는 메소드로 사용주의할 것!
	 */
	@Test
	void initQueryXML() {
		Properties prop1 = new Properties();
		Properties prop2 = new Properties();
		try {
			prop1.setProperty("test", "SUCCESS QUERY");
			prop1.storeToXML(new FileOutputStream("resources/query.xml"), "Query");
			
			prop2.loadFromXML(new FileInputStream("resources/query.xml"));
			System.out.println(prop2.getProperty("test"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * xml 파일을 완전히 새로 만드는 메소드로 사용주의할 것!
	 */
	@Test
	void initConnectionXML() {
		Properties prop1 = new Properties();
		Properties prop2 = new Properties();
		try {
			prop1.setProperty("test", "SUCCESS CONNECTION");
			prop1.storeToXML(new FileOutputStream("resources/connection.xml"), "Connection");
			
			prop2.loadFromXML(new FileInputStream("resources/connection.xml"));
			System.out.println(prop2.getProperty("test"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
