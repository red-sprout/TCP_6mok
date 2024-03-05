package sixmok;

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
}
